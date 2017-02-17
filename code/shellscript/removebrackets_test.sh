cd ../java/src/
javac preProcess/RemoveBrackets.java preProcess/InputOutput.java
for countryCode in "at" "be" "br" "ch" "cz" "de" "dk" "fi" "ie" "nb" "nl" "no" "pl" "pt" "ru" "se" "uk" "it" "fr"; do
    start=$(date +%s.%N)
    filename1="/data/sidana/recsysBaselines/december/stats/userClicks_$countryCode.test"
    filename2="/data/sidana/recsysBaselines/december/stats/temp/userClicks_$countryCode.test"
    #filename1="/data/sidana/recsysBaselines/december/inputffm/test/userofferclicks/ffminput_$countryCode.csv"
    #filename2="/data/sidana/recsysBaselines/december/inputffm/test/userofferclicks/temp/ffminput_$countryCode.csv"
    java -cp . preProcess.RemoveBrackets $filename1 $filename2
    dur=$(echo "$(date +%s.%N) - $start" | bc)
    echo "$countryCode:$dur">>/data/sidana/recsysBaselines/experiments/inputffm/test/userofferclicks/removebrackets_test.txt
done
cd -
