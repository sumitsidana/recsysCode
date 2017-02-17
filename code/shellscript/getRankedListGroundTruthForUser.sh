cd ../java/
mkdir -p /data/sidana/recsysBaselines/experiments/fmcountryfiles/output/vectors
javac preProcess/InputOutput.java preProcess/GetRankedListGroundTruthForUser.java
for countryCode in "at" "be" "br" "ch" "cz" "de" "dk" "es" "fi" "ie" "nb" "nl" "no" "pl" "pt" "ru" "se" "uk" "it" "fr"; do
start=$(date +%s.%N)
filename1="/data/sidana/recsysBaselines/experiments/fmcountryfiles/input/test_$countryCode.csv"
filename2="/data/sidana/recsysBaselines/experiments/fmcountryfiles/output/libfmresult_$countryCode"
filename3="/data/sidana/recsysBaselines/experiments/fmcountryfiles/output/vectors/gt_$countryCode"
filename4="/data/sidana/recsysBaselines/experiments/fmcountryfiles/output/vectors/pr_$countryCode"

java -cp . preProcess.GetRankedListGroundTruthForUser "$filename1" "$filename2" "$filename3" "$filename4"
dur=$(echo "$(date +%s.%N) - $start" | bc)
echo "$countryCode:$dur">>/data/sidana/recsysBaselines/experiments/fmcountryfiles/output/vectors/getrankedlistgroundtruthforuser.txt

done
cd -
