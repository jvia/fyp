#!/bin/bash
#
# Jeremiah M. Via <jxv911@cs.bham.ac.uk>
#
# A script which converts the classification XML file to a more easily
# readable CSV file.

AUCOM=""
IN=""
OUT=""


help_conv()
{
    echo "Usage: $0 -a -i -o"
    echo " -a  Path to the aucom JAR file"
    echo " -i  Input file"
    echo " -o  Output file"
    exit 1
}

# Make arguments all exists
if [ $# -lt 4 ]; then
    help_conv
fi
while getopts a:i:o:x opt
do
    case "$opt" in
        a)  AUCOM="$OPTARG";;
        i)  IN="$OPTARG";;
        o)  OUT="$OPTARG";;
        \?) help_conv;;
    esac
done

# Local variables
PIDS=""
VM="-Djava.util.logging.config.file=data/logging.properties -Xmx2g"

java $VM -jar $AUCOM \
    --classification-to-dat \
    -i "$IN" \
    -o "$OUT"
