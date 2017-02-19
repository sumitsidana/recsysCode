cd ../java/src
javac preProcess/FFMInputOperate.java preProcess/WriteFFMInput.java
# for countryCode in "fi" "ie" "nb" "nl" "no" "pl" "pt" "ru" "se" "uk" "it" "fr"; do
for countryCode in "ie"; do
    start=$(date +%s.%N)
    filename1="/data/sidana/recsysBaselines/bug_december/tabseparatedinput/userofferclicks/train_$countryCode.csv"
    filename2="/data/sidana/recsysBaselines/bug_december/tabseparatedinput/userofferclicks/test_$countryCode.csv"
    filename3="/data/sidana/recsysBaselines/bug_december/ffmcountryfiles/input/userofferclicks/$countryCode.ffm.train"
    filename4="/data/sidana/recsysBaselines/bug_december/ffmcountryfiles/input/userofferclicks/$countryCode.ffm.test"
    java -Xmx120g -cp . preProcess.FFMInputOperate "$filename1" "$filename2" "$filename3" "$filename4"
done
cd -
