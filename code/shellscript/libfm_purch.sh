cd ~/recsysBaselines/code/cplusplus/libfm-1.42.src/bin/
for countryCode in "at" "be" "br" "ch" "cz" "de" "dk" "es" "fi" "ie" "nb" "nl" "no" "pl" "pt" "ru" "se" "uk" "it" "fr"; do
     echo $countryCode
     start=$(date +%s.%N)
     ./libFM -task c -train /data/sidana/purch/train.csv.libfm -test /data/sidana/purch/test.csv.libfm -dim '1,1,10' -iter 10 -method sgd -learn_rate 0.001 -regular '0,0,0.01' -init_stdev 0.1 -out /data/sidana/purch/libfmresult -rlog /data/sidana/purch/log
     dur=$(echo "$(date +%s.%N) - $start" | bc)
     echo "$countryCode:$dur">>/data/sidana/purch/libfm.txt
     done
cd -
