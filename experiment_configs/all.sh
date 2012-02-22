#!/bin/bash 
## A script to run all experiments

WD=/home/jxv911/Dropbox/aucom



./3chain.sh

# Move result to special folders
mkdir $WD/3chain
mkdir $WD/standard
mkdir $WD/reduced

# Copy over the common files
cp $WD/3chain.obs $WD/standard
cp $WD/3chain.obs $WD/reduced


mv  $WD/standard $WD/3chain
mv  $WD/reduced $WD/3chain
