cd ../java/
javac  preProcess/WriteUsersOffers.java preProcess/InputOutput.java
for countryCode in "at" "be" "br" "ch" "cz" "de" "dk" "es" "fi" "ie" "nb" "nl" "no" "pl" "pt" "ru" "se" "uk" "it" "fr"; do
filename1="/data/sidana/recsysBaselines/experiments/mfcountryfiles/input/mfinput_$countryCode.csv"
filename2="/data/sidana/recsysBaselines/experiments/randomcountryfiles/alloffers/$countryCode"
java -cp . preProcess.WriteUsersOffers "$filename1" "$filename2"
done
