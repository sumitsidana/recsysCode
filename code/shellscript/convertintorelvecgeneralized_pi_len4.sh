cd ../java
mkdir -p /data/sidana/recsysBaselines/experiments/picountryfiles/output/len4/rv/
javac preProcess/ConvertIntoRelVecGeneralized.java
for countryCode in "at" "be" "br" "ch" "cz" "de" "dk" "es" "fi" "ie" "nb" "nl" "no" "pl" "pt" "ru" "se" "uk" "it" "fr"; do
    start=$(date +%s.%N)
	filename1="/data/sidana/recsysBaselines/experiments/picountryfiles/output/vectors/gt_$countryCode"
	filename2="/data/sidana/recsysBaselines/experiments/picountryfiles/output/vectors/pr_$countryCode"
	filename3="/data/sidana/recsysBaselines/experiments/picountryfiles/output/len4/rv/relevanceVector_$countryCode"
	java -cp . preProcess.ConvertIntoRelVecGeneralized "$filename1" "$filename2" "$filename3" 100
    dur=$(echo "$(date +%s.%N) - $start" | bc)
    echo "$countryCode:$dur">>/data/sidana/recsysBaselines/experiments/picountryfiles/output/len4/convertintorelvecgeneralized.txt
done
cd -
