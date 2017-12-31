#!/usr/bin/env bash
start=$(date +%s.%N)
awk  -v countryCode=2  'BEGIN{OFS=FS=","} {print >> "countrywise/Page_View.anon/Page_View_" $countryCode ".anon"}' Page_View.anon
dur=$(echo "$(date +%s.%N) - $start" | bc)
echo "$countryCode:$dur">>countrywise/Page_View.anon/time.txt
