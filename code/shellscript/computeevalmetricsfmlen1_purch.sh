cd ../python/
mkdir -p /data/sidana/purch/len1/em/

    start=$(date +%s.%N)	
	python3 compOfflineEvalMetrics_len1.py /data/sidana/purch/len1 "$countryCode"
    dur=$(echo "$(date +%s.%N) - $start" | bc)
    echo "$countryCode:$dur">>/data/sidana/purch/len1/computeevalmetrics.txt

