#!/bin/bash
#
# Jeremiah M. Via (jxv911@cs.bhamac.uk>
#
# This file runs a set if experiments against the crruent code base.
# It handles all step, from observation collection to graph creation.
PIDS=""
AUCOM=../target/aucom-0.0.1-jar-with-dependencies.jar
VM="-Djava.util.logging.config.file=data/logging.properties -Xmx2g"

trap 'kill $PIDS' INT TERM PIPE QUIT ABRT HUP

echo "######################################################################"
echo "#                       Observation Collection                       #"
echo "######################################################################"

echo "Starting aucom"
xterm -e bash -c \
    "java $VM -jar $AUCOM \
    --collection  \
    -o /home/jxv911/Dropbox/aucom/3chain.obs" &
AUCOMPID="$!"
PIDS="$PIDS $AUCOMPID"

echo "Starting CAST server"
xterm -e bash -c "cast-server" &
PIDS="$PIDS $!"

echo "Starting CAST client"
xterm -e bash -c "cast-client ../cast_configs/three_chain/three_chain.cast" &
PIDS="$PIDS $!"

wait $AUCOMPID
kill $PIDS >/dev/null 2>&1
echo "Done"

echo "######################################################################"
echo "#                              Normal run                            #"
echo "######################################################################"
PIDS=""
xterm -e bash -c \
    "java $VM -jar $AUCOM \
    --experiment  \
    -i /home/jxv911/Dropbox/aucom/3chain.obs \
    -o /home/jxv911/Dropbox/aucom/3chain_normal.cl" &
AUCOMPID="$!"
PIDS="$PIDS $AUCOMPID"
sleep 20
xterm -e bash -c "cast-server" &
PIDS="$PIDS $!"
xterm -e bash -c "cast-client ../cast_configs/three_chain/three_chain.cast" &
PIDS="$PIDS $!"

wait $AUCOMPID
echo "Killing $PIDS"
kill $PIDS >/dev/null 2>&1


echo "######################################################################"
echo "#                              Faulty run                            #"
echo "######################################################################"
PIDS=""
xterm -e bash -c \
    "java $VM -jar $AUCOM \
    --experiment --error 2500 \
    -i /home/jxv911/Dropbox/aucom/3chain.obs \
    -o /home/jxv911/Dropbox/aucom/3chain_fault.cl | tee 3chain_error.txt" &
AUCOMPID="$!"
PIDS="$PIDS $AUCOMPID"
sleep 20
xterm -e bash -c "cast-server" &
PIDS="$PIDS $!"
xterm -e bash -c "cast-client ../cast_configs/three_chain/three_chain_fault.cast" &
PIDS="$PIDS $!"

wait $AUCOMPID
kill $PIDS >/dev/null 2>&1
echo "done\n\n"

echo "######################################################################"
echo "#                       Capture error timestamp                      #"
echo "#                                  &                                 #"
echo "#                          converting to csv                         #"
echo "######################################################################"
ERROR=`grep ms 3chain_error.txt | sed 's/ms//'`

java $VM -jar $AUCOM \
    --classification-to-dat \
    -i /home/jxv911/Dropbox/aucom/3chain_normal.cl \
    -o /home/jxv911/Dropbox/aucom/3chain_normal.csv
java $VM -jar $AUCOM \
    --classification-to-dat \
    -i /home/jxv911/Dropbox/aucom/3chain_fault.cl \
    -o /home/jxv911/Dropbox/aucom/3chain_fault.csv

echo "######################################################################"
echo "#                              Create graphs                         #"
echo "######################################################################"
echo \
    "reset
     # Print to postscript
     set term postscript eps enhanced color
     set output '3chain.eps'

     set key below
     set xrange[0:200000]
     set yrange[0:1]
     set trange [0:1]
     set multiplot
     set grid
     set parametric

     # Line styles
     set style line 1 lt 1 lw 1 pt 3 linecolor rgb 'blue'
     set style line 2 lt 1 lw 1 pt 3 linecolor rgb 'grey'


     ### Reduced: normal
     set size   1.0, 0.5
     set origin 0.0, 0.5
     set xrange[0:100000]
     set title 'Reduced: Normal'
     plot '3chain_normal.csv' using 1:2 with dots  ls 1          notitle, \\
          '3chain_normal.csv' using 1:2 with lines smooth bezier title 'Score',\\
          '3chain_normal.csv' using 1:3 with lines ls 2          title 'Threshold'

     # ### Reduced: fault
     reduce_fault=$ERROR
     set size   1.0, 0.5
     set origin 0.0, 0.0
     set title 'Reduced: Fault'
     set xrange[0:100000]
     plot '3chain_fault.csv' using 1:2 with dots  ls 1          notitle,\\
          '3chain_fault.csv' using 1:2 with lines smooth bezier title 'Score', \\
          '3chain_fault.csv' using 1:3 with lines ls 2          title 'Threshold',\\
           reduce_fault, t lt 1 lw 2 title 'Fault'" > /home/jxv911/Dropbox/aucom/3chain.plt

(cd /home/jxv911/Dropbox/aucom && gnuplot 3chain.plt)

echo "######################################################################"
echo "#                                 Cleanup                            #"
echo "######################################################################"
rm -f *.obs *.dot
