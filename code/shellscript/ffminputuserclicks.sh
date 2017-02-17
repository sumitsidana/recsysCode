cd ../java
javac preProcess/FFMInputOperate.java preProcess/WriteFFMInput.java

for countryCode in "at" "be" "br" "ch" "cz" "de" "dk" "es" "fi" "ie" "nb" "nl" "no" "pl" "pt" "ru" "se" "uk" "it" "fr"; do
    start=$(date +%s.%N)
    filename1="/data/sidana/recsysBaselines/experiments/tabseparatedinput/userclicks/train_$countryCode.csv"
    filename2="/data/sidana/recsysBaselines/experiments/tabseparatedinput/userclicks/test_$countryCode.csv"
    filename3="/data/sidana/recsysBaselines/experiments/ffmcountryfiles/input/userclicks/$countryCode.ffm.train"
    filename4="/data/sidana/recsysBaselines/experiments/ffmcountryfiles/input/userclicks/$countryCode.ffm.test"
	java -Xmx100g -cp . preProcess.FFMInputOperate "$filename1" "$filename2" "$filename3" "$filename4"
    dur=$(echo "$(date +%s.%N) - $start" | bc)
    echo "$countryCode:$dur">>/home/ama/sidana/ffminput/ffminput/ffminputoperatetrain.txt
done
cd -
