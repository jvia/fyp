#!/usr/bin/env python
import argparse
import io
import warnings
import math

parser = argparse.ArgumentParser(description='Calculate experiment statistics.')
parser.add_argument('--file', action='store', dest='file')
parser.add_argument('--timestamp', action='store', dest='timestamp')

file = parser.parse_args().file
timestamp = int(parser.parse_args().timestamp)

# Variables to calculate stats
first_fault = False
first_fault_timestamp = -1
total = 0
tp = 0.0 # True positive
fp = 0.0 # False positive
tn = 0.0 # True negative
fn = 0.0 # False negative
for raw_line in open(file, 'r'):
    line = raw_line.split()
    if line[0].isdigit():
        total += 1
        ts = int(line[0])
        calc  = float(line[3])
        real = 1 if ts >= timestamp else 0
        
        if first_fault and real == 1:
            first_fault_timestamp = ts

        if calc == 0 and real == 0:
            tn += 1.0
        elif calc == 0 and real == 1:
            fn += 1.0
        elif calc == 1 and real == 0:
            fp += 1.0
        elif calc == 1 and real == 1:
            tp += 1.0
        else:
            warnings.warn("Non-zero or one value")

#print 'True fault: {}\nFalse fault: {}\nTrue normal: {}\nFalse normal: {}'.format(fp, tp, tn, fn)
sensitivity = tp / (tp + fn)
specificity = tn / (fp + tn)
mcc = ((tp * tn) - (fp * fn)) / math.sqrt((tp+fp)*(tp+fn)*(tn+fp)*(tn+fn))

print 'Sensitivity: {}\nSpecificity: {}\nMCC: {}'.format(sensitivity, specificity, mcc)
print 'Total Elements: {}'.format(total)
