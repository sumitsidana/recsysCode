cd ../java/
javac preProcess/WriteInputFM.java
for countryCode in "at" "be" "br" "ch" "cz" "de" "dk" "es" "fi" "ie" "nb" "nl" "no" "pl" "pt" "ru" "se" "uk" "it" "fr"; do
start=$(date +%s.%N)
filename1="../../data/input/fmcountryfilesv3/cOOuterJoin_$countryCode.csv"
filename2="../../data/input/fmcountryfilesv3/fminput_$countryCode"
java -cp . preProcess.WriteInputFM "$filename1" "$filename2"
dur=$(echo "$(date +%s.%N) - $start" | bc)
echo "$countryCode:$dur">>../../data/output/writeinputfm.txt
done
cd -
