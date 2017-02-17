cd ../java/
javac preProcess/InputOutput.java preProcess/ClassifyOutput.java
for countryCode in "at" "be" "br" "ch" "cz" "de" "dk" "es" "fi" "ie" "nb" "nl" "no" "pl" "pt" "ru" "se" "uk" "it" "fr"; do
start=$(date +%s.%N)
for a in 1 2 3 4 5 6 7 8 9; do
filename1="../../data/output/fmcountryfilesv3/libfmresult_$countryCode"
filename2="../../data/output/fmcountryfilesv3/threshold/libfmc_$countryCode$a"
java -cp . preProcess.ClassifyOutput "0.$a" "$filename1" "$filename2"
done
dur=$(echo "$(date +%s.%N) - $start" | bc)
echo "$countryCode:$dur">>../../data/output/classifyoutput.txt

done
cd -
