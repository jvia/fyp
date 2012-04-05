reset
set term postscript eps enhanced color
set output 'original_4x4.eps'
set multiplot
set parametric
unset key
set yrange [0:1]
set xrange [0:150000]
set size 1.0, 0.5

norm = "../data/original_4x4_normal.csv"
fault = "../data/original_4x4_fault.csv"

set title 'Normal'
set origin 0.0, 0.5
plot norm using 1:2 with dots notitle,\
     norm using 1:2 with lines smooth bezier title 'Score (smoothed)',\
     norm using 1:3 with lines title 'Threshold'
set title 'Fault'
set origin 0.0, 0.0
induced_fault = 64221
plot fault using 1:2 with dots notitle,\
     fault using 1:2 with lines smooth bezier title 'Score (smoothed)',\
     fault using 1:3 with lines title 'Threshold',\
     induced_fault, t title 'Induced Fault'
     