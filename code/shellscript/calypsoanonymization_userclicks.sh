cd /home/ama/sidana/recsysBaselines/code/scala/CalypsoAnonymizationVer2/CalypsoAnonymization
for countryCode in "at" "be" "br" "ch" "cz" "de" "dk" "es" "fi" "ie" "nb" "nl" "no" "pl" "pt" "ru" "se" "uk" "it" "fr"; do
	filename1="/data/sidana/recsysBaselines/calypsodata/files/anonymized/userclicks/train_$countryCode.csv"
	filename2="/data/sidana/recsysBaselines/calypsodata/files/anonymized/userclicks/test_$countryCode.csv"
	java -cp ../../scala-library-2.11.7.jar:./target/scala-2.11/dataanonymiser_2.11-1.0-SNAPSHOT.jar Anonymizer salt=<hidden> "$filename1"
	java -cp ../../scala-library-2.11.7.jar:./target/scala-2.11/dataanonymiser_2.11-1.0-SNAPSHOT.jar Anonymizer salt=<hidden> "$filename2"
done
cd -
