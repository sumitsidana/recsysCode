cd ../cplusplus/libfm-1.42.src/bin/
for countryCode in "at" "be" "br" "ch" "cz" "de" "dk" "es" "fi" "ie" "nb" "nl" "no" "pl" "pt" "ru" "se" "uk" "it" "fr"; do
     echo $countryCode
     start=$(date +%s.%N)
     ./libFM -task c -train /data/sidana/recsysBaselines/experiments/fmcountryfiles/input/train_$countryCode.csv.libfm -test /data/sidana/recsysBaselines/experiments/fmcountryfiles/input/test_$countryCode.csv.libfm -dim '1,1,10' -iter 10 -method sgd -learn_rate 0.001 -regular '0,0,0.01' -init_stdev 0.1 -out /data/sidana/recsysBaselines/experiments/fmcountryfiles/output/libfmresult_$countryCode -rlog /data/sidana/recsysBaselines/experiments/fmcountryfiles/output/log_$countryCode
     dur=$(echo "$(date +%s.%N) - $start" | bc)
     echo "$countryCode:$dur">>../../../../data/output/libfm.txt
     done
cd -
