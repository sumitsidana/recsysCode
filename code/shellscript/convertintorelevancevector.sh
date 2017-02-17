cd ../java
javac preProcess/ConvertIntoRelevanceVector.java
for countryCode in "at" "be" "br" "ch" "cz" "de" "dk" "es" "fi" "ie" "nb" "nl" "no" "pl" "pt" "ru" "se" "uk" "it" "fr"; do
filename1="../../data/output/countryfiles/dat.clicksTest_$countryCode"
filename2="../../data/output/popularity/countryfiles/dat.predictedPopularity_$countryCode"
filename3="../../data/output/popularity/countryfiles/relevanceVector_$countryCode"
java -cp . preProcess.ConvertIntoRelevanceVector "$filename1" "$filename2" "$filename3" 31
done
