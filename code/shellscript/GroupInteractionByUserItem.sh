cd ../java/src
javac preProcess/GroupInteractionByUserItem.java
for countryCode in "at" "be" "br" "ch" "cz" "de" "dk" "es" "fi" "ie" "nb" "nl" "no" "pl" "pt" "ru" "se" "uk" "it" "fr"; do
start=$(date +%s.%N)
filename1="/data/recsysBaselines/picountryfiles/user_past_preference_$countryCode.csv"
filename2="/data/recsysBaselines/picountryfiles/userInteractions_$countryCode.csv"
java -cp . preProcess.GroupInteractionByUserItem "$filename1" "$filename2"
    dur=$(echo "$(date +%s.%N) - $start" | bc)
    echo "$countryCode:$dur">> /data/recsysBaselines/picountryfiles/groupinteractionbyuseritem.txt
done
