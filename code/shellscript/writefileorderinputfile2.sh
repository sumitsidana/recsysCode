cd ../java
javac postProcess/Operations.java postProcess/InputOutput.java
for algo in pop pi fm ffm ffmextended mf random
do
    for (( i=1; i <= 4; i++ ))
    do
	filename1="/data/sidana/recsysBaselines/experiments/micromap/dat.testratiousers"
	filename2="/data/sidana/recsysBaselines/experiments/micromap/${algo}countryfiles/len${i}/dat.map${algo}len${i}"
	filename3="/data/sidana/recsysBaselines/experiments/micromap/${algo}countryfiles/len${i}/dat.micromap${algo}len${i}"
	java -cp . postProcess.Operations "$filename1" "$filename2" "$filename3"
    done
done
