set terminal postscript eps enhanced color solid font 'Helvetica,17'
set title "Mean Average Precision of popularity based prediction by country"
set size 1,1
set xlabel "Country Code"
set output "distributionMAPPastInteractionsbyCountry.eps";
set xtic rotate by -35 scale 0
set ylabel "#map"
set datafile separator " "
set style data histogram
plot "dat.mapPastInteractions" using ($2):xticlabels(1) title 'Mean Average Precision'
