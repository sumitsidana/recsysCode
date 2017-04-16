cd ../java/src
mkdir -p /data/sidana/purch/len1/rv
javac preProcess/ConvertIntoRelVecGeneralized.java
for countryCode in "at" "be" "br" "ch" "cz" "de" "dk" "es" "fi" "ie" "nb" "nl" "no" "pl" "pt" "ru" "se" "uk" "it" "fr"; do
    start=$(date +%s.%N)
	filename1="/data/sidana/purch/vectors/gt_$countryCode"
	filename2="/data/sidana/purch/vectors/pr_$countryCode"
	filename3="/data/sidana/purch/len1/rv/relevanceVector_$countryCode"
	java -cp . preProcess.ConvertIntoRelVecGeneralized "$filename1" "$filename2" "$filename3" 5
    dur=$(echo "$(date +%s.%N) - $start" | bc)
    echo "$countryCode:$dur">>/data/sidana/purch/len1/rv/convertintorelvecgeneralized.txt
done
cd -
