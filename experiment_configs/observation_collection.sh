#!/bin/bash
#
# Jeremiah M. Via <jxv911@cs.bham.ac.uk>
#
# A script which runs an observation collection session.

# Get the arguments passed into the script
AUCOM=""
FILE=""
CAST_CONFIG=""


help_obs()
{
    echo "Usage: $0 -a -o -c"
    echo "Options: These are the required arguments"
    echo " -a the path to the aucom JAR file"
    echo " -o the output file"
    echo " -c the CAST config file"
    exit 1
}

# Make arguments all exists
if [ $# -lt 4 ]; then
    help_obs
fi
while getopts a:c:o:x opt
do
    case "$opt" in
        a)  AUCOM="$OPTARG";;
        c)  CAST_CONFIG="$OPTARG";;
        o)  FILE="$OPTARG";;
        \?) help_obs;;
    esac
done

# Local variables
PIDS=""
VM="-Djava.util.logging.config.file=data/logging.properties -Xmx2g"

echo "Starting CAST server"
xterm -e bash -c "cast-server" &
PIDS="$PIDS $!"
sleep 1

echo "Starting aucom ($AUCOM)"
xterm -e bash -c \
    "java $VM -jar $AUCOM \
    --collection \
    -o $FILE" &
AUCOMPID="$!"
PIDS="$PIDS $AUCOMPID"
sleep 1

echo "Starting CAST client ($CAST_CONFIG)"
xterm -e bash -c "cast-client $CAST_CONFIG" &
PIDS="$PIDS $!"

wait $AUCOMPID
kill $PIDS >/dev/null 2>&1
echo "Done"
