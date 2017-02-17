cd ../java
javac postProcess/Operations.java postProcess/InputOutput.java 
for (( i=1; i <= 4; i++ ))
do
java -cp . postProcess.Operations /data/sidana/recsysBaselines/bug_december/ffmcountryfiles/output/userofferclicks/len$i/em/evalMetrics /data/sidana/recsysBaselines/bug_december/ffmcountryfiles/output/userofferclicks/len$i/em/dat.mapffmlen$i Mean\ Average\ Precision
done
