cd ../java/
for (( i=1 ; i <= 4 ; i++ ))
do
java -cp . postProcess.Operations /data/sidana/recsysBaselines/experiments/fmcountryfiles/output/len$i/em/evalMetrics /data/sidana/recsysBaselines/experiments/fmcountryfiles/output/len$i/em/dat.mapfmlen$i Mean\ Average\ Precision
done
