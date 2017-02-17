cd ../java/src
javac preProcess/FFMInputOperate.java preProcess/WriteFFMInput.java

for countryCode in "at" "be" "br" "ch" "cz" "de" "dk" "fi" "nb" "nl" "no" "pl" "pt" "ru" "se" "uk" "it" "fr"; do
    start=$(date +%s.%N)
    filename1="/data/sidana/recsysBaselines/december/tabseparatedinput/userofferclicks/train_$countryCode.csv"
    filename2="/data/sidana/recsysBaselines/december/tabseparatedinput/userofferclicks/test_$countryCode.csv"
    filename3="/data/sidana/recsysBaselines/december/ffmcountryfiles/input/userofferclicks/$countryCode.ffm.train"
    filename4="/data/sidana/recsysBaselines/december/ffmcountryfiles/input/userofferclicks/$countryCode.ffm.test"
	java -Xmx120g -cp . preProcess.FFMInputOperate "$filename1" "$filename2" "$filename3" "$filename4"
    dur=$(echo "$(date +%s.%N) - $start" | bc)
    echo "$countryCode:$dur">>/home/ama/sidana/ffminput/ffminput/ffminputoperatetrain.txt
done
cd -
