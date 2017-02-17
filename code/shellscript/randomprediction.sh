cd ../java/
mkdir -p /data/sidana/recsysBaselines/experiments/randomcountryfiles/output/vectors
javac preProcess/WriteRandomOffers.java preProcess/PredictRandomOffers.java 
for countryCode in "at" "be" "br" "ch" "cz" "de" "dk" "es" "fi" "ie" "nb" "nl" "no" "pl" "pt" "ru" "se" "uk" "it" "fr"; do
start=$(date +%s.%N)

filename1="/data/sidana/recsysBaselines/experiments/randomcountryfiles/alloffers/$countryCode"
filename2="/data/sidana/recsysBaselines/experiments/stats/testusers/$countryCode"
filename3="/data/sidana/recsysBaselines/experiments/randomcountryfiles/output/vectors/pr_$countryCode"
java -cp . preProcess.PredictRandomOffers "$filename1" "$filename2" "$filename3"
dur=$(echo "$(date +%s.%N) - $start" | bc)
echo "$countryCode:$dur">>/data/sidana/recsysBaselines/experiments/randomcountryfiles/predictrandomoffers.txt

done
cd -
