#!/usr/bin/env bash
start=$(date +%s.%N)
awk  -v countryCode=3  'BEGIN{OFS=FS=","} {print >> "countrywise/Search.anon/Search_" $countryCode ".anon"}' Search.anon
dur=$(echo "$(date +%s.%N) - $start" | bc)
echo "$countryCode:$dur">>countrywise/Search.anon/time.txt
