cd ../java/
for (( i=1; i <= 4; i++ ))
do
java -cp . postProcess.Operations /data/sidana/recsysBaselines/experiments/ffmcountryfiles/output/main/len$i/em/evalMetrics /data/sidana/recsysBaselines/experiments/ffmcountryfiles/output/main/len$i/em/dat.mapffmlen$i Mean\ Average\ Precision
done
