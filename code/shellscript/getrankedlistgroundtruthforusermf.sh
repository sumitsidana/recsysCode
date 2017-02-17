cd ../java/
mkdir -p /data/sidana/recsysBaselines/experiments/mfcountryfiles/implicit/output/vectors
javac preProcess/InputOutput.java preProcess/GetRankedListGroundTruthMF.java 
for countryCode in "at" "be" "br" "ch" "cz" "de" "dk" "es" "fi" "ie" "nb" "nl" "no" "pl" "pt" "ru" "se" "uk" "it" "fr"; do
start=$(date +%s.%N)
filename1="/data/sidana/recsysBaselines/experiments/mfcountryfiles/implicit/output/ratingsTest_$countryCode.csv"
filename2="/data/sidana/recsysBaselines/experiments/mfcountryfiles/implicit/output/predictions_$countryCode.csv"
filename3="/data/sidana/recsysBaselines/experiments/mfcountryfiles/implicit/output/vectors/gt_$countryCode"
filename4="/data/sidana/recsysBaselines/experiments/mfcountryfiles/implicit/output/vectors/pr_$countryCode"

java -cp . preProcess.GetRankedListGroundTruthMF "$filename1" "$filename2" "$filename3" "$filename4"
dur=$(echo "$(date +%s.%N) - $start" | bc)
echo "$countryCode:$dur">>/data/sidana/recsysBaselines/experiments/mfcountryfiles/implicit/output/getrankedlistgroundtruthforuser.txt

done
cd -
