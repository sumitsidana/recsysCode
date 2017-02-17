cd ../java/src/
javac preProcess/RemoveBrackets.java preProcess/InputOutput.java
for countryCode in "at" "be" "br" "ch" "cz" "de" "dk" "fi" "ie" "nb" "nl" "no" "pl" "pt" "ru" "se" "uk" "it" "fr"; do
    start=$(date +%s.%N)
    filename1=$1
    filename2=$2
    java -cp . preProcess.RemoveBrackets $filename1 $filename2
done
cd -
