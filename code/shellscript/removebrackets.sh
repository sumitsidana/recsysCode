javac ~/recsysBaselines/code/java/preProcess/RemoveBrackets.java ~/recsysBaselines/code/java/preProcess/InputOutput.java
for countryCode in "at" "be" "br" "ch" "cz" "de" "dk" "fi" "ie" "nb" "nl" "no" "pl" "pt" "ru" "se" "uk" "it" "fr"; do
    start=$(date +%s.%N)
    filename1="/data/sidana/recsysBaselines/december/rawfiles/totalData_$countryCode.csv"
    filename2="/data/sidana/recsysBaselines/december/rawfiles/rawFile_$countryCode.csv"
    java -cp ~/recsysBaselines/code/java/ preProcess.RemoveBrackets $filename1 $filename2
    dur=$(echo "$(date +%s.%N) - $start" | bc)
    echo "$countryCode:$dur">>/data/sidana/recsysBaselines/experiments/rawfiles/removebrackets.txt
done
