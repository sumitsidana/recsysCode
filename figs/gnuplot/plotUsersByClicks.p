set terminal postscript eps enhanced color solid font 'Helvetica,17'
set title "Kelkoo: #Users versus #Clicks"
set size 1,1
set format y "%.2t*10^%+03T";
set xlabel "#Clicks/Impressions"
set ylabel "#Users who clicked or #Users who were shown an impression"
set output "usersByClickCount.eps";
set datafile separator ","
set key spacing 1.0
set logscale x;
set logscale y;
set xtics 1,10,1e4
set yrange [1:1e8]
plot  'dat.metricbyK' using ($2) smooth csplines  title 'Number of Users versus Number of Clicks', \
'' using ($4) smooth csplines title 'Number of Users versus Number of Impressions'
