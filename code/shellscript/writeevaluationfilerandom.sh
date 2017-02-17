cd ../java/
javac postProcess/Operations.java postProcess/InputOutput.java
for (( i=1 ; i <= 3 ; i++ ))
do
java -cp . postProcess.Operations /data/sidana/recsysBaselines/experiments/randomcountryfiles/output/len$i/em/evalMetrics /data/sidana/recsysBaselines/experiments/randomcountryfiles/output/len$i/em/dat.maprandomlen$i Mean\ Average\ Precision
done
