for countryCode in "at" "be" "br" "ch" "cz" "de" "dk" "es" "fi" "ie" "nb" "nl" "no" "pl" "pt" "ru" "se" "uk" "it" "fr"; do
    start=$(date +%s.%N)	
	cat "/data/sidana/recsysBaselines/experiments/mfcountryfiles/input/train_$countryCode.csv" "/data/sidana/recsysBaselines/experiments/mfcountryfiles/input/test_$countryCode.csv">"/data/sidana/recsysBaselines/experiments/mfcountryfiles/input/mfinput_$countryCode.csv"
    dur=$(echo "$(date +%s.%N) - $start" | bc)
    echo "$countryCode:$dur">>/data/sidana/recsysBaselines/experiments/mfcountryfiles/input/cat.txt
done
