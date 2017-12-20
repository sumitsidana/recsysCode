#!/usr/bin/env bash
start=$(date +%s.%N)
awk  -v countryCode=8  'BEGIN{OFS=FS=","} {print >> "countrywise/Offers.opt.anon/Offers_" $countryCode ".anon"}' Offers.anon
dur=$(echo "$(date +%s.%N) - $start" | bc)
echo "$countryCode:$dur">>countrywise/Offers.opt.anon/time.txt