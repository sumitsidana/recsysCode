cd ../python/
mkdir -p /data/sidana/purch/fm/len2/em/

    start=$(date +%s.%N)	
	python3 compOfflineEvalMetrics_len2.py /data/sidana/purch/fm/len2 "$countryCode"
    dur=$(echo "$(date +%s.%N) - $start" | bc)
    echo "$countryCode:$dur">>/data/sidana/purch/fm/len2/computeevalmetrics.txt

