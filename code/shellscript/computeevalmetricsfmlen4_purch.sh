cd ../python/
mkdir -p /data/sidana/purch/fm/len4/em/

    start=$(date +%s.%N)	
	python3 compOfflineEvalMetrics_len4.py /data/sidana/purch/fm/len4 "$countryCode"
    dur=$(echo "$(date +%s.%N) - $start" | bc)
    echo "$countryCode:$dur">>/data/sidana/purch/fm/len4/computeevalmetrics.txt

