reset

# Output settings
set term postscript eps enhanced color
set output 'metronome_10x1.eps'

# Global settings
unset key
set multiplot
set size 1.0, 0.5
set parametric
set yrange [0:1]
set xrange [0:300000]

#### Line styles ####

# Score line

# Threshold line

# Score dots

# Fault line

# # 4x4 normal
# set origin 0.0, 0.5
# set title '4x4 normal'
# plot 'metronome_4x4_normal.csv' using 1:2 with dots notitle,\
#      'metronome_4x4_normal.csv' using 1:2 with lines smooth bezier title 'Score (smoothed)',\
#      'metronome_4x4_normal.csv' using 1:3 with lines title 'Threshold'

# # 4x4 fault
# set origin 0.0, 0.0
# set title '4x4 fault'
# fault = 150082
# plot 'metronome_4x4_fault.csv' using 1:2 with dots notitle,\
#      'metronome_4x4_fault.csv' using 1:2 with lines smooth bezier title 'Score (smoothed)',\
#      'metronome_4x4_fault.csv' using 1:3 with lines title 'Threshold',\
#      fault, t title 'Fault'


# 10x1 normal
set origin 0.0, 0.5
set title '10x1 normal'
plot 'metronome_10x1_normal.csv' using 1:2 with dots notitle,\
     'metronome_10x1_normal.csv' using 1:2 with lines smooth bezier title 'Score (smoothed)',\
     'metronome_10x1_normal.csv' using 1:3 with lines title 'Threshold'

# 10x1 fault
set origin 0.0, 0.0
set title '10x1 fault'
fault = 230599
plot 'metronome_10x1_fault.csv' using 1:2 with dots notitle,\
     'metronome_10x1_fault.csv' using 1:2 with lines smooth bezier title 'Score (smoothed)',\
     'metronome_10x1_fault.csv' using 1:3 with lines title 'Threshold',\
     fault, t title 'Fault'