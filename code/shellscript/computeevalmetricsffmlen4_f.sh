cd ../python/
mkdir -p /data/sidana/recsysBaselines/bug_december/ffmcountryfiles/output/userofferclicks/len4/em/
#"at" "be" "br" "ch" "cz" "de" "dk" "es" 
for countryCode in "fi" "ie" "nb" "nl" "no" "pl" "pt" "ru" "se" "uk" "it" "fr"; do
    start=$(date +%s.%N)	
	python3 compOfflineEvalMetrics_len4.py /data/sidana/recsysBaselines/bug_december/ffmcountryfiles/output/userofferclicks/len4 "$countryCode"
    dur=$(echo "$(date +%s.%N) - $start" | bc)
    echo "$countryCode:$dur">>/data/sidana/recsysBaselines/bug_december/ffmcountryfiles/output/userofferclicks/len4/computeevalmetrics.txt
done
