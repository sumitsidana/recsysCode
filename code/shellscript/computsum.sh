cd ../java
javac postProcess/Operations.java postProcess/InputOutput.java
for algo in pop pi fm ffm ffmextended
do
    for (( i=1; i <= 4; i++ ))
    do
	filename1="/data/sidana/recsysBaselines/experiments/micromap/results/dat.micromap${algo}len${i}"
	filename2="/data/sidana/recsysBaselines/experiments/micromap/results/dat.micromaptotal${algo}len${i}"
	java -cp . postProcess.Operations "$filename1" "$filename2"
    done
done
