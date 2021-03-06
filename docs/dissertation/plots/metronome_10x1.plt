reset
#set term postscript eps enhanced color
#set output '../img/metronome_10x1.eps'
set multiplot
set parametric
unset key
set yrange [0:1]
set xrange [0:350000]
set xlabel 'milliseconds'
set size 1.0, 0.5

norm = "../data/metronome_10x1_normal.csv"
fault = "../data/metronome_10x1_fault.csv"

set title 'Normal'
set origin 0.0, 0.5
plot norm using 1:2 with dots title 'Score',\
     norm using 1:2 with lines smooth bezier title 'Score (smoothed)',\
     norm using 1:3 with lines title 'Threshold'
set title 'Fault'
set origin 0.0, 0.0
induced_fault = 230599
plot fault using 1:2 with dots title 'Score',\
     fault using 1:2 with lines smooth bezier title 'Score (smoothed)',\
     fault using 1:3 with lines title 'Threshold',\
     induced_fault, t title 'Induced Fault'
    