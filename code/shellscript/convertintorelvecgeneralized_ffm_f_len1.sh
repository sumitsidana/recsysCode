cd ../java/src
mkdir -p /data/sidana/recsysBaselines/bug_december/ffmcountryfiles/output/userofferclicks/len1/rv/
javac preProcess/ConvertIntoRelVecGeneralized.java
#"at" "be" "br" "ch" "cz" "de" "dk" "es" "fi" "ie" "nb" "nl" "no" "pl" "pt" "ru" "se" "uk" "it" "fr"
for countryCode in "ie"; do
    start=$(date +%s.%N)
	filename1="/data/sidana/recsysBaselines/bug_december/ffmcountryfiles/output/userofferclicks/vectors/gt_$countryCode"
	filename2="/data/sidana/recsysBaselines/bug_december/ffmcountryfiles/output/userofferclicks/vectors/pr_$countryCode"
	filename3="/data/sidana/recsysBaselines/bug_december/ffmcountryfiles/output/userofferclicks/len1/rv/relevanceVector_$countryCode"
	java -cp . preProcess.ConvertIntoRelVecGeneralized "$filename1" "$filename2" "$filename3" 5
    dur=$(echo "$(date +%s.%N) - $start" | bc)
    echo "$countryCode:$dur">>/data/sidana/recsysBaselines/bug_december/ffmcountryfiles/output/userofferclicks/len1/convertintorelvecgeneralized.txt
done
cd -
