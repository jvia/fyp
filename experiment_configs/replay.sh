#!/bin/bash
#
# Jeremiah M. Via <jxv911@cs.bham.ac.uk>
#
# This file runs a set if experiments against the crruent code base.
# It handles all step, from observation collection to graph creation.

AUCOM=""
TRAIN=""
IN=""
OUT=""

help_replay()
{
    echo "Usage: $0 -a -t -i -o"
    echo " -a  Path to the aucom JAR file"
    echo " -t  Training file"
    echo " -i  Input file"
    echo " -o  Output file"
    exit 1
}

# Make arguments all exists
if [ $# -lt 8 ]; then
    help_replay
fi
while getopts a:t:i:o:x opt
do
    case "$opt" in
        a)  AUCOM="$OPTARG";;
        t)  TRAIN="$OPTARG";;
        i)  IN="$OPTARG";;
        o)  OUT="$OPTARG";;
        \?) help_replay;;
    esac
done

# Local variables
PIDS=""
VM="-Djava.util.logging.config.file=data/logging.properties -Xmx2g"

# Start aucom with a an output file if necessary
echo "Aucom:    $AUCOM"
echo "Training: $TRAIN"
echo "Input:    $IN"
echo "Output:   $OUT"
bash -c \
    "java $VM -jar $AUCOM \
     --replay  \
     -t $TRAIN \
     -i $IN \
     -o $OUT" &
AUCOMPID="$!"
PIDS="$PIDS $AUCOMPID"
sleep 60

# Wait until replay has finished
wait $AUCOMPID
echo "Killing $PIDS"
kill $PIDS >/dev/null 2>&1
