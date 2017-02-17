cd ../java/src
mkdir -p /data/sidana/recsysBaselines/bug_december/ffmcountryfiles/output/userofferclicks/vectors
javac preProcess/InputOutput.java preProcess/GetRankedListGroundTruthForUserFFM.java
#"at" "be" "br" "ch" "cz" "de" "dk" "es"
for countryCode in "fi" "ie" "nb" "nl" "no" "pl" "pt" "ru" "se" "uk" "it" "fr"; do
start=$(date +%s.%N)
filename1="/data/sidana/recsysBaselines/bug_december/tabseparatedinput/userofferclicks/test_$countryCode.csv"
filename2="/data/sidana/recsysBaselines/bug_december/ffmcountryfiles/input/userofferclicks/output/$countryCode.output"
filename3="/data/sidana/recsysBaselines/bug_december/ffmcountryfiles/output/userofferclicks/vectors/gt_$countryCode"
filename4="/data/sidana/recsysBaselines/bug_december/ffmcountryfiles/output/userofferclicks/vectors/pr_$countryCode"

java -cp . preProcess.GetRankedListGroundTruthForUserFFM "$filename1" "$filename2" "$filename3" "$filename4"
dur=$(echo "$(date +%s.%N) - $start" | bc)
echo "$countryCode:$dur">>/data/sidana/recsysBaselines/bug_december/ffmcountryfiles/output/userofferclicks/vectors/getrankedlistgroundtruthforuser.txt

done
cd -
