#!/usr/bin/env bash
for countryCode in "at" "be" "br" "ch" "cz" "de" "dk" "es" "fi" "ie" "nb" "nl" "no" "pl" "pt" "ru" "se" "uk" "it" "fr"; do
start=$(date +%s.%N)
awk -F',' '{if ($8=="'${countryCode}'") print }'  Offers.anon > countrywise/Offers.anon/Offers_${countryCode}.anon
dur=$(echo "$(date +%s.%N) - $start" | bc)
echo "$countryCode:$dur">>countrywise/Offers.anon/time.txt
done
