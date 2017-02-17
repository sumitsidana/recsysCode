set terminal postscript eps enhanced monochrome solid font 'Helvetica,14'
set size 1.2,1.2
set output "comparison.eps";
set title "Empirical comparison of Various evaluation metrics." font 'Helvetica, 17'
set auto x
set style data histogram 
set style histogram cluster gap 2
set style fill solid border -1 
set key top right
#set boxwidth 2
set datafile separator " "
set xtic rotate by -35 scale 0
set xlabel "Region" font "Helvetica,17" offset 0,0.6
set ylabel "Metric" font "Helvetica,17" offset 2,0.2
#set logscale y
set bmargin 6
set key spacing 1.7
#set grid
plot 'dat.evalMetrics' using ($2):xticlabels(1) title 'Past Interactions' fs pattern 1, \
        '' using ($3):xticlabels(1) title 'Popularity' fs pattern 2
