cd ../java/
javac  preProcess/WriteUsersOffers.java preProcess/InputOutput.java
for countryCode in "at" "be" "br" "ch" "cz" "de" "dk" "es" "fi" "ie" "nb" "nl" "no" "pl" "pt" "ru" "se" "uk" "it" "fr"; do
filename1="/data/sidana/recsysBaselines/experiments/tabseparatedinput/default/test_$countryCode.csv"
filename2="/data/sidana/recsysBaselines/experiments/stats/testusers/$countryCode"
filename3="/data/sidana/recsysBaselines/experiments/stats/testoffers/$countryCode"
java -cp . preProcess.WriteUsersOffers "$filename1" "$filename2" "$filename3"
done
