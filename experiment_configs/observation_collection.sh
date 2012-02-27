#!/bin/bash
#
# Jeremiah M. Via <jxv911@cs.bham.ac.uk>
#
# A script which runs an observation collection session.



# Get the arguments passed into the script
AUCOM=$1
FILE=$2
CAST_CONFIG=$3

# Local variables
PIDS=""
VM="-Djava.util.logging.config.file=data/logging.properties -Xmx2g"

echo "Starting CAST server"
xterm -e bash -c "cast-server" &
PIDS="$PIDS $!"
sleep 1

echo "Starting aucom ($1)"
xterm -e bash -c \
    "java $VM -jar $AUCOM \
    --collection \
    -o $FILE" &
AUCOMPID="$!"
PIDS="$PIDS $AUCOMPID"
sleep 1

echo "Starting CAST client"
xterm -e bash -c "cast-client $CAST_CONFIG" &
PIDS="$PIDS $!"

wait $AUCOMPID
kill $PIDS >/dev/null 2>&1
echo "Done"
 
