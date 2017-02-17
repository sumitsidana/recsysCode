cd ../java/
mkdir -p /data/sidana/recsysBaselines/experiments/picountryfiles/output/vectors/
javac preProcess/TargetUsersPastInteractions.java
for countryCode in "at" "be" "br" "ch" "cz" "de" "dk" "es" "fi" "ie" "nb" "nl" "no" "pl" "pt" "ru" "se" "uk" "it" "fr"; do
start=$(date +%s.%N)
filename1="/data/sidana/recsysBaselines/experiments/picountryfiles/output/userInteractions_$countryCode.csv"
filename2="/data/sidana/recsysBaselines/experiments/stats/testusers/$countryCode"
filename3="/data/sidana/recsysBaselines/experiments/picountryfiles/output/vectors/pr_$countryCode"
    dur=$(echo "$(date +%s.%N) - $start" | bc)
    echo "$countryCode:$dur">> /data/sidana/recsysBaselines/experiments/picountryfiles/output/vectors/targetuserpastinteractions.txt
java -cp . preProcess.TargetUsersPastInteractions "$filename1" "$filename2" "$filename3"
done
