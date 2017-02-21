#cd ~/recsys_baselines/cpluscplus/libffm/
#./ffm-train -p /data/sidana/recsysBaselines/experiments/ffmcountryfiles/input/userofferclicks/fr.ffm.test -s 16 -l 0.001 -k 8 -r 0.2 --auto-stop /data/sidana/recsysBaselines/experiments/ffmcountryfiles/input/userofferclicks/fr.ffm.train /data/sidana/recsysBaselines/experiments/debug/fr.model

#./ffm-predict /data/sidana/recsysBaselines/experiments/ffmcountryfiles/input/userofferclicks/fr.ffm.test /data/sidana/recsysBaselines/experiments/debug/fr.model /data/sidana/recsysBaselines/experiments/debug/fr.output


cd ~/recsysCode/code/java/src/


#javac preProcess/InputOutput.java preProcess/GetRankedListGroundTruthForUserFFM.java

#for countryCode in "fr"; do
#start=$(date +%s.%N)
#filename1="/data/sidana/recsysBaselines/experiments/tabseparatedinput/userofferclicks/test_$countryCode.csv"
#filename2="/data/sidana/recsysBaselines/experiments/debug/$countryCode.output"
#filename3="/data/sidana/recsysBaselines/experiments/debug/gt_$countryCode"
#filename4="/data/sidana/recsysBaselines/experiments/debug/pr_$countryCode"

#java -cp . preProcess.GetRankedListGroundTruthForUserFFM "$filename1" "$filename2" "$filename3" "$filename4"

#done

mkdir -p /data/sidana/recsysBaselines/experiments/debug/rv/
javac preProcess/ConvertIntoRelVecGeneralized.java
for countryCode in "fr"; do
    start=$(date +%s.%N)
    	filename1="/data/sidana/recsysBaselines/experiments/debug/gt_$countryCode"
	filename2="/data/sidana/recsysBaselines/experiments/debug/pr_$countryCode"
	filename3="/data/sidana/recsysBaselines/experiments/debug/rv//relevanceVector_$countryCode"
	java -cp . preProcess.ConvertIntoRelVecGeneralized "$filename1" "$filename2" "$filename3" 5
done
cd -

mkdir -p /data/sidana/recsysBaselines/experiments/debug/em/

cd ~/recsysCode/code/python

for countryCode in "fr"; do
	python3 compOfflineEvalMetrics_len1.py /data/sidana/recsysBaselines/experiments/debug fr
done
