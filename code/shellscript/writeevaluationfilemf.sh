cd ../java/
javac postProcess/Operations.java postProcess/InputOutput.java
for (( i=1 ; i <= 4 ; i++ ))
do
java -cp . postProcess.Operations /data/sidana/recsysBaselines/experiments/mfcountryfiles/implicit/output/len$i/em/evalMetrics /data/sidana/recsysBaselines/experiments/mfcountryfiles/implicit/output/len$i/em/dat.mapmflen$i Mean\ Average\ Precision
done
