#!/bin/bash
#
# Jeremiah M. Via <jxv911@cs.bham.ac.uk>
#
# This file runs a set if experiments against the crruent code base.
# It handles all step, from observation collection to graph creation.

AUCOM=""
IN=""
OUT=""
CAST=""
LOG=""

help_experiment()
{
    echo "Usage: $0 -a -i -o -c [-l]"
    echo " -a  Path to the aucom JAR file"
    echo " -i  Input file"
    echo " -o  Output file"
    echo " -c  The CAST config file"
    echo " -l  A log file"
    exit 1
}

# Make arguments all exists
if [ $# -lt 5 ]; then
    help_experiment
fi
while getopts a:c:i:o:l:x opt
do
    case "$opt" in
        a)  AUCOM="$OPTARG";;
        c)  CAST="$OPTARG";;
        i)  IN="$OPTARG";;
        o)  OUT="$OPTARG";;
        l)  LOG="$OPTARG";;        
        \?) help_experiment;;
    esac
done

# Local variables
PIDS=""
VM="-Djava.util.logging.config.file=data/logging.properties -Xmx2g"

# Start aucom with a an output file if necessary
if [ -z "$LOG" ]; then
    xterm -e bash -c \
        "java $VM -jar $AUCOM \
        --experiment  \
        -i $IN \
        -o $OUT" &
else
    xterm -e bash -c \
        "java $VM -jar $AUCOM \
        --experiment --error 2500 \
        -i $IN \
        -o $OUT | tee $LOG" &
fi
AUCOMPID="$!"
PIDS="$PIDS $AUCOMPID"
sleep 60

# Start cast-server
xterm -e bash -c "cast-server" &
PIDS="$PIDS $!"

# Start cast-client
xterm -e bash -c "cast-client $CAST" &
PIDS="$PIDS $!"

# Wait until experiment has finished
wait $AUCOMPID
echo "Killing $PIDS"
kill $PIDS >/dev/null 2>&1
