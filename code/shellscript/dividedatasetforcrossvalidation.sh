cd ../java/
for countryCode in "at" "be" "br" "ch" "cz" "de" "dk" "es" "fi" "ie" "nb" "nl" "no" "pl" "pt" "ru" "se" "uk" "it" "fr"; do
     echo $countryCode
     start=$(date +%s.%N)
     javac preProcess/DivideDatasetForCrossvalidation.java preProcess/InputOutput.java
     java -cp . preProcess.DivideDatasetForCrossvalidation 0.8 "../../data/input/fmcountryfilesv3/fminput_$countryCode" "../../data/output/fmcountryfilesv3/fminput_$countryCode.train" "../../data/output/fmcountryfilesv3/fminput_$countryCode.test"
     dur=$(echo "$(date +%s.%N) - $start" | bc)
     echo "$countryCode:$dur">>../../data/output/dividedatasetforcrossvalidation.txt
done
cd -
