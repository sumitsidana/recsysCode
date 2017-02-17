cd ../java/
mkdir -p "../../data/output/mfcountryfiles/test"
javac preProcess/InputOutput.java preProcess/InputNegativeFeedbackToNeutral.java 
for countryCode in "at" "be" "br" "ch" "cz" "de" "dk" "es" "fi" "ie" "nb" "nl" "no" "pl" "pt" "ru" "se" "uk" "it" "fr"; do
start=$(date +%s.%N)
filename1="../../data/input/test/fminput_$countryCode.test"
filename2="../../data/output/mfcountryfiles/test/test_$countryCode.csv"

java -cp . preProcess.InputNegativeFeedbackToNeutral "$filename1" "$filename2"
dur=$(echo "$(date +%s.%N) - $start" | bc)
echo "$countryCode:$dur">>../../data/output/mfcountryfiles/inputnegativefeedbacktoneutral.txt

done
cd -
