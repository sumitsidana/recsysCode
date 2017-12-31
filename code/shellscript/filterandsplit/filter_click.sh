#!/usr/bin/env bash
start=$(date +%s.%N)
awk  -v countryCode=4  'BEGIN{OFS=FS=","} {print >> "countrywise/Click.anon/Click_" $countryCode ".anon"}' Click.anon
dur=$(echo "$(date +%s.%N) - $start" | bc)
echo "$countryCode:$dur">>countrywise/Click.anon/time.txt
