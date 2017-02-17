cd ../python/
mkdir -p /data/sidana/recsysBaselines/bug_december/ffmcountryfiles/output/userofferclicks/len1/em/
#"at" "be" "br" "ch" "cz" "de" "dk" "es" 
for countryCode in "fi" "ie" "nb" "nl" "no" "pl" "pt" "ru" "se" "uk" "it" "fr"; do
    start=$(date +%s.%N)	
	python3 compOfflineEvalMetrics_len1.py /data/sidana/recsysBaselines/bug_december/ffmcountryfiles/output/userofferclicks/len1 "$countryCode"
    dur=$(echo "$(date +%s.%N) - $start" | bc)
    echo "$countryCode:$dur">>/data/sidana/recsysBaselines/bug_december/ffmcountryfiles/output/userofferclicks/len1/computeevalmetrics.txt
done
