cd ../java/src/
mkdir -p /data/sidana/recsysBaselines/bug_december/ffmcountryfiles/output/userofferclicks/len4/rv/
javac preProcess/ConvertIntoRelVecGeneralized.java
#"at" "be" "br" "ch" "cz" "de" "dk" "es" 
for countryCode in "fi" "ie" "nb" "nl" "no" "pl" "pt" "ru" "se" "uk" "it" "fr"; do
    start=$(date +%s.%N)
	filename1="/data/sidana/recsysBaselines/bug_december/ffmcountryfiles/output/userofferclicks/vectors/gt_$countryCode"
	filename2="/data/sidana/recsysBaselines/bug_december/ffmcountryfiles/output/userofferclicks/vectors/pr_$countryCode"
	filename3="/data/sidana/recsysBaselines/bug_december/ffmcountryfiles/output/userofferclicks/len4/rv/relevanceVector_$countryCode"
	java -cp . preProcess.ConvertIntoRelVecGeneralized "$filename1" "$filename2" "$filename3" 100
    dur=$(echo "$(date +%s.%N) - $start" | bc)
    echo "$countryCode:$dur">>/data/sidana/recsysBaselines/bug_december/ffmcountryfiles/output/userofferclicks/len4/convertintorelvecgeneralized.txt
done
cd -
