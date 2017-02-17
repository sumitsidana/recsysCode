cd ../java/
javac preProcess/InputOutput.java preProcess/AggregateonUsers.java
for countryCode in "at" "be" "br" "ch" "cz" "de" "dk" "es" "fi" "ie" "nb" "nl" "no" "pl" "pt" "ru" "se" "uk" "it" "fr"; do
start=$(date +%s.%N)
filename1="../../data/output/fmcountryfilesv3/fminput_$countryCode.test"
filename2="../../data/output/fmcountryfilesv3/rankedlistatk/libfmc_$countryCode"
filename3="../../data/output/fmcountryfilesv3/vectors/gt_$countryCode"
filename4="../../data/output/fmcountryfilesv3/vectors/pr_$countryCode"
java -cp . preProcess.AggregateonUsers "$filename1" "$filename2" "$filename3" "$filename4"
dur=$(echo "$(date +%s.%N) - $start" | bc)
echo "$countryCode:$dur">>../../data/output/aggregateonusers.txt
done
cd -
