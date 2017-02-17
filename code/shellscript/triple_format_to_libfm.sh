cd ../cplusplus/libfm-1.42.src/scripts
for countryCode in "at" "be" "br" "ch" "cz" "de" "dk" "es" "fi" "ie" "nb" "nl" "no" "pl" "pt" "ru" "se" "uk" "it" "fr"; do
     start=$(date +%s.%N) 
     echo $countryCode
     ./triple_format_to_libfm.pl -in /data/sidana/recsysBaselines/experiments/fmcountryfiles/input/train_$countryCode.csv,/data/sidana/recsysBaselines/experiments/fmcountryfiles/input/test_$countryCode.csv -target 2 -separator "\t"
    dur=$(echo "$(date +%s.%N) - $start" | bc)
    echo "$countryCode:$dur">>/data/sidana/recsysBaselines/experiments/fmcountryfiles/input/triple_format_to_libfm.txt
done
cd -
