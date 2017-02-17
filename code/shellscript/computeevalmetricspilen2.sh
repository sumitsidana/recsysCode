cd ../python/
mkdir -p /data/sidana/recsysBaselines/experiments/picountryfiles/output/len2/em/
for countryCode in "at" "be" "br" "ch" "cz" "de" "dk" "es" "fi" "ie" "nb" "nl" "no" "pl" "pt" "ru" "se" "uk" "it" "fr"; do
    start=$(date +%s.%N)	
	python3 compOfflineEvalMetrics_len2.py /data/sidana/recsysBaselines/experiments/picountryfiles/output/len2 "$countryCode"
    dur=$(echo "$(date +%s.%N) - $start" | bc)
    echo "$countryCode:$dur">>/data/sidana/recsysBaselines/experiments/picountryfiles/output/len2/computeevalmetrics.txt
done
