cd ../java/src/
javac preProcess/CreateDelimiterSeperatedInput.java preProcess/InputOutput.java
header=`echo -e "userid\tofferid\tcountrycode\tcategory\tmerchant\tutcdate\tuserclickcount\tofferclickcount\trating"`
#"at" "be" "br" "ch" "cz" "de" "dk" 
for countryCode in "fi" "nb" "nl" "no" "pl" "pt" "ru" "se" "uk" "it" "fr"; do
    filename1="/data/sidana/recsysBaselines/bug_december/inputffm/train/userofferclicks/ffminput_$countryCode.csv"
    filename2="/data/sidana/recsysBaselines/bug_december/tabseparatedinput/userofferclicks/train_$countryCode.csv"
    java -cp . preProcess.CreateDelimiterSeperatedInput "$filename1" "$filename2" "$header"
done
cd -
