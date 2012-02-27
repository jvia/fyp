#!/bin/bash
## A script to run all experiments

ORIGINAL=../target/original.jar
REDUCED=../target/reduced.jar
METRONOME=../target/metronome.jar
DIR=5chain
ITERS=10
# Cast configs
NORMAL=../cast_configs/five_chain/five_chain.cast
FAULTY=../cast_configs/five_chain/five_chain_fault.cast

mkdir $DIR ; mkdir $DIR/orig ; mkdir $DIR/reduced ; mkdir $DIR/metronome
./observation_collection.sh -a $ORIGINAL -o obs -c $NORMAL

#
# Original
#
for i in `seq 1 $ITERS`;
do
    echo "Original: normal run $i"
    ./experiment.sh -a $ORIGINAL -i obs -o orig.cl -c $NORMAL
    ./convert.sh -a $ORIGINAL -i orig.cl -o orig.csv
    mv orig.csv "$DIR/orig/normal_$i.csv"
done

for i in `seq 1 $ITERS`;
do
    echo "Original: faulty run $i"
    ./experiment.sh -a $ORIGINAL -i obs -o orig.cl -c $FAULTY
    ./convert.sh -a $ORIGINAL -i orig.cl -o orig.csv
    mv orig.csv "$DIR/orig/faulty_$i.csv"
done

#
# Reduced
#
for i in `seq 1 $ITERS`;
do
    echo "Reduced: normal run $i"
    ./experiment.sh -a $REDUCED -i obs -o reduced.cl -c $NORMAL
    ./convert.sh    -a $REDUCED -i reduced.cl  -o reduced.csv
    mv reduced.csv "$DIR/reduced/normal_$i.csv"
done

for i in `seq 1 $ITERS`;
do
    echo "Reduced: faulty run $i"
    ./experiment.sh -a $REDUCED -i obs -o reduced.cl -c $FAULTY
    ./convert.sh    -a $REDUCED -i reduced.cl  -o reduced.csv
    mv reduced.csv "$DIR/reduced/faulty_$i.csv"
done

#
# Metronome
#
for i in `seq 1 $ITERS`;
do
    echo "Metronome: normal run $i"
    ./experiment.sh -a $METRONOME -i obs -o metronome.cl -c $NORMAL
    ./convert.sh    -a $METRONOME -i metronome.cl  -o metronome.csv
    mv metronome.csv "$DIR/metronome/normal_$i.csv"
done

for i in `seq 1 $ITERS`;
do
    echo "Metronome: faulty run $i"
    ./experiment.sh -a $METRONOME -i obs -o metronome.cl -c $FAULTY
    ./convert.sh    -a $METRONOME -i metronome.cl  -o metronome.csv
    mv metronome.csv "$DIR/metronome/faulty_$i.csv"
done
