cd ../python/
mkdir -p /data/sidana/purch/len3/em/
    start=$(date +%s.%N)	
	python3 compOfflineEvalMetrics_len3.py /data/sidana/purch/len3 "$countryCode"
    dur=$(echo "$(date +%s.%N) - $start" | bc)
    echo "$countryCode:$dur">>/data/sidana/purch/len3/computeevalmetrics.txt