for countryCode in "at" "be" "br" "ch" "cz" "de" "dk" "es" "fi" "ie" "nb" "nl" "no" "pl" "pt" "ru" "se" "uk" "it" "fr"; do
     echo $countryCode
     javac preProcess/DivideDatasetForCrossvalidation.java preProcess/InputOutput.java
     java -cp . preProcess.DivideDatasetForCrossvalidation 0.8 "../../data/input/countryfiles/orderedClicks_$countryCode.csv" "../../data/output/countryfiles/clicksTrain_$countryCode" "../../data/output/countryfiles/clicksTest_$countryCode" "../../data/input/countryfiles/orderedClicksUsersOffers_$countryCode.csv" "../../data/output/countryfiles/clicksTrainPartial_$countryCode" "../../data/output/countryfiles/clicksTestPartial_$countryCode" "../../data/output/countryfiles/clicksTestPartial_$countryCode" "../../data/output/countryfiles/dat.clicksTestCounts_$countryCode" "../../data/output/countryfiles/dat.clicksTest_$countryCode" "../../data/output/countryfiles/dat.clicksTest_$countryCode" "../../data/output/countryfiles/testUsers_$countryCode"
done
