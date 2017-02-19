# cd /data/sidana/recsysBaselines/bug_december/rawfiles/
# sed -i.bak 1i"[useridclicks","offeridclicks","useridoffers","offeridoffers","countrycode","category","merchant","utcdate]" *.csv

# cd /home/ama/sidana/repoRecsysBaselines/recsysBaselines/code/shellscript

# for countryCode in "fi" "ie" "nb" "nl" "no" "pl" "pt" "ru" "se" "uk" "it" "fr"; do
#     filename1="/data/sidana/recsysBaselines/bug_december/rawfiles/totalData_$countryCode.csv"
#     filename2="/data/sidana/recsysBaselines/bug_december/rawfiles/rawFile_$countryCode.csv"
#     ./remBrk.sh $filename1 $filename2
# done

# for countryCode in "nl"; do
#     filename1="/data/sidana/recsysBaselines/bug_december/rawfiles/totalData_$countryCode.csv"
#     filename2="/data/sidana/recsysBaselines/bug_december/rawfiles/rawFile_$countryCode.csv"
#     ./remBrk.sh $filename1 $filename2
# done

# cd ~/../spark/spark/bin/

# echo 'writing train and test files'

# ./spark-submit --class "main.scala.WriteTrainTestFiles"  --packages com.databricks:spark-csv_2.11:1.4.0 --total-executor-cores 50 --executor-memory 20G --driver-memory 20G --conf spark.driver.maxResultSize=5G  ~/recsysBaselines/code/scala/target/scala-2.11/simple-project_2.11-1.0.jar

# cd /data/sidana/recsysBaselines/bug_december/inputfile/

# mkdir -p /data/sidana/recsysBaselines/bug_december/trtesplitfiles

# echo 'renaming files'

# for d in */ ; do
#     echo "$d"
#     fname=`basename $d`
#     echo $fname
#     mv "$d/part-00000" "/data/sidana/recsysBaselines/bug_december/trtesplitfiles/$fname"
# done

# cd /data/sidana/recsysBaselines/bug_december/trtesplitfiles

# echo 'adding headers'



# sed -i.bak 1i"[useridclicks","offeridclicks","useridoffers","offeridoffers","countrycode","category","merchant","utcdate]" *.csv

# cd /home/ama/sidana/repoRecsysBaselines/recsysBaselines/code/shellscript

# mkdir -p /data/sidana/recsysBaselines/bug_december/tsplitfiles

# echo 'removing brackets'

# for countryCode in "fi" "ie" "nb" "nl" "no" "pl" "pt" "ru" "se" "uk" "it" "fr"; do
#     filename1="/data/sidana/recsysBaselines/bug_december/trtesplitfiles/train_$countryCode.csv"
#     filename2="/data/sidana/recsysBaselines/bug_december/tsplitfiles/train_$countryCode.csv"
#     ./remBrk.sh $filename1 $filename2
# done

# for countryCode in "fi" "ie" "nb" "nl" "no" "pl" "pt" "ru" "se" "uk" "it" "fr"; do
#     filename1="/data/sidana/recsysBaselines/bug_december/trtesplitfiles/test_$countryCode.csv"
#     filename2="/data/sidana/recsysBaselines/bug_december/tsplitfiles/test_$countryCode.csv"
#     ./remBrk.sh $filename1 $filename2
# done

# cd ~/../spark/spark/bin/

# mkdir -p /data/sidana/recsysBaselines/bug_december/stats/temp/temp

# echo 'spark: stats train'

# ./spark-submit --class "main.scala.WriteStatsTrain"  --packages com.databricks:spark-csv_2.11:1.4.0 --total-executor-cores 50 --executor-memory 20G --driver-memory 20G --conf spark.driver.maxResultSize=5G  ~/recsysBaselines/code/scala/target/scala-2.11/simple-project_2.11-1.0.jar

# echo 'spark: stats test'

# ./spark-submit --class "main.scala.WriteStatsTest"  --packages com.databricks:spark-csv_2.11:1.4.0 --total-executor-cores 70 --executor-memory 30G --driver-memory 30G --conf spark.driver.maxResultSize=5G  ~/recsysBaselines/code/scala/target/scala-2.11/simple-project_2.11-1.0.jar

# cd /data/sidana/recsysBaselines/bug_december/stats/temp/temp

# echo 'renaming files'

# for d in */ ; do
#     echo "$d"
#     fname=`basename $d`
#     echo $fname
#     mv "$d/part-00000" "/data/sidana/recsysBaselines/bug_december/stats/temp/$fname"
# done




# cd /data/sidana/recsysBaselines/bug_december/stats/temp

# sed -i.bak 1i"[usererid","count]" userClicks_*
# sed -i.bak 1i"[offerid","count]" offerClicks_*

# echo 'removing brackets'

# cd /home/ama/sidana/recsysCode/code/shellscript

# for countryCode in "fi" "ie" "nb" "nl" "no" "pl" "pt" "ru" "se" "uk" "it" "fr"; do
#     filename1="/data/sidana/recsysBaselines/bug_december/stats/temp/userClicks_$countryCode.train"
#     filename2="/data/sidana/recsysBaselines/bug_december/stats/userClicks_$countryCode.train"
#     ./remBrk.sh $filename1 $filename2
# done

# for countryCode in "fi" "ie" "nb" "nl" "no" "pl" "pt" "ru" "se" "uk" "it" "fr"; do
#     filename1="/data/sidana/recsysBaselines/bug_december/stats/temp/userClicks_$countryCode.test"
#     filename2="/data/sidana/recsysBaselines/bug_december/stats/userClicks_$countryCode.test"
#     ./remBrk.sh $filename1 $filename2
# done

# for countryCode in "fi" "ie" "nb" "nl" "no" "pl" "pt" "ru" "se" "uk" "it" "fr"; do
#     filename1="/data/sidana/recsysBaselines/bug_december/stats/temp/offerClicks_$countryCode.train"
#     filename2="/data/sidana/recsysBaselines/bug_december/stats/offerClicks_$countryCode.train"
#     ./remBrk.sh $filename1 $filename2
# done

# for countryCode in "fi" "ie" "nb" "nl" "no" "pl" "pt" "ru" "se" "uk" "it" "fr"; do
#     filename1="/data/sidana/recsysBaselines/bug_december/stats/temp/offerClicks_$countryCode.test"
#     filename2="/data/sidana/recsysBaselines/bug_december/stats/offerClicks_$countryCode.test"
#     ./remBrk.sh $filename1 $filename2
# done

# cd ~/../spark/spark/bin/

# echo 'spark: writing feature - number of clicks by user in training data'

# ./spark-submit --class "main.scala.UserClicksTrain"  --packages com.databricks:spark-csv_2.11:1.4.0 --total-executor-cores 50 --executor-memory 20G --driver-memory 20G --conf spark.driver.maxResultSize=5G  ~/recsysBaselines/code/scala/target/scala-2.11/simple-project_2.11-1.0.jar

# cd /data/sidana/recsysBaselines/bug_december/inputffm/train/userclicks/temp/temp

# echo 'rename files'

# for d in */ ; do
#     echo "$d"
#     fname=`basename $d`
#     echo $fname
#     mv "$d/part-00000" "/data/sidana/recsysBaselines/bug_december/inputffm/train/userclicks/temp/$fname"
# done

# cd /data/sidana/recsysBaselines/bug_december/inputffm/train/userclicks/temp

# echo 'add: headers'

# sed -i.bak 1i"[useridclicks","offeridclicks","useridoffers","offeridoffers","countrycode","category","merchant","utcdate","userid","clickcount]" ffminput_*.csv


# echo 'remove brackets'

# cd /home/ama/sidana/recsysCode/code/shellscript
# for countryCode in "fi" "ie" "nb" "nl" "no" "pl" "pt" "ru" "se" "uk" "it" "fr"; do
#     filename1="/data/sidana/recsysBaselines/bug_december/inputffm/train/userclicks/temp/ffminput_$countryCode.csv"
#     filename2="/data/sidana/recsysBaselines/bug_december/inputffm/train/userclicks/ffminput_$countryCode.csv"
#     ./remBrk.sh $filename1 $filename2
# done

# cd ~/../spark/spark/bin/

# echo 'spark: writing feature - number of clicks on train offers'

# ./spark-submit --class "main.scala.UserOfferClicksTrain"  --packages com.databricks:spark-csv_2.11:1.4.0 --total-executor-cores 50 --executor-memory 20G --driver-memory 20G --conf spark.driver.maxResultSize=5G  ~/recsysBaselines/code/scala/target/scala-2.11/simple-project_2.11-1.0.jar

# cd /data/sidana/recsysBaselines/bug_december/inputffm/train/userofferclicks/temp/temp

# for d in */ ; do
#     echo "$d"
#     fname=`basename $d`
#     echo $fname
#     mv "$d/part-00000" "/data/sidana/recsysBaselines/bug_december/inputffm/train/userofferclicks/temp/$fname"
# done

# cd /data/sidana/recsysBaselines/bug_december/inputffm/train/userofferclicks/temp

# echo 'add headers'


# sed -i.bak 1i"[useridclicks","offeridclicks","useridoffers","offeridoffers","countrycode","category","merchant","utcdate","userid","userclickcount","offerid","offerclickcount]" ffminput_*.csv

# echo 'remove brackets'

# cd /home/ama/sidana/recsysCode/code/shellscript
# for countryCode in "fi" "ie" "nb" "nl" "no" "pl" "pt" "ru" "se" "uk" "it" "fr"; do
#     filename1="/data/sidana/recsysBaselines/bug_december/inputffm/train/userofferclicks/temp/ffminput_$countryCode.csv"
#     filename2="/data/sidana/recsysBaselines/bug_december/inputffm/train/userofferclicks/ffminput_$countryCode.csv"
#     ./remBrk.sh $filename1 $filename2
# done



# cd ~/../spark/spark/bin


# echo 'spark: writing feature - number of clicks by user in test data'

# ./spark-submit --class "main.scala.UserClicksTest"  --packages com.databricks:spark-csv_2.11:1.4.0 --total-executor-cores 50 --executor-memory 20G --driver-memory 20G --conf spark.driver.maxResultSize=5G  ~/recsysBaselines/code/scala/target/scala-2.11/simple-project_2.11-1.0.jar

# cd /data/sidana/recsysBaselines/bug_december/inputffm/test/userclicks/temp/temp

# for d in */ ; do
#     echo "$d"
#     fname=`basename $d`
#     echo $fname
#     mv "$d/part-00000" "/data/sidana/recsysBaselines/bug_december/inputffm/test/userclicks/temp/$fname"
# done

# cd /data/sidana/recsysBaselines/bug_december/inputffm/test/userclicks/temp

# sed -i.bak 1i"[useridclicks","offeridclicks","useridoffers","offeridoffers","countrycode","category","merchant","utcdate","userid","clickcount]" ffminput_*.csv

# cd /home/ama/sidana/recsysCode/code/shellscript
# for countryCode in "fi" "ie" "nb" "nl" "no" "pl" "pt" "ru" "se" "uk" "it" "fr"; do
#     filename1="/data/sidana/recsysBaselines/bug_december/inputffm/test/userclicks/temp/ffminput_$countryCode.csv"
#     filename2="/data/sidana/recsysBaselines/bug_december/inputffm/test/userclicks/ffminput_$countryCode.csv"
#     ./remBrk.sh $filename1 $filename2
# done

# cd ~/../spark/spark/bin/

# echo 'spark: writing feature - number of clicks on test offers'

# ./spark-submit --class "main.scala.UserOfferClicksTest"  --packages com.databricks:spark-csv_2.11:1.4.0 --total-executor-cores 70 --executor-memory 30G --driver-memory 30G --conf spark.driver.maxResultSize=5G  ~/recsysBaselines/code/scala/target/scala-2.11/simple-project_2.11-1.0.jar


# cd /data/sidana/recsysBaselines/bug_december/inputffm/test/userofferclicks/temp/temp

# for d in */ ; do
#     echo "$d"
#     fname=`basename $d`
#     echo $fname
#     mv "$d/part-00000" "/data/sidana/recsysBaselines/bug_december/inputffm/test/userofferclicks/temp/$fname"
# done

# cd /data/sidana/recsysBaselines/bug_december/inputffm/test/userofferclicks/temp



# sed -i.bak 1i"[useridclicks","offeridclicks","useridoffers","offeridoffers","countrycode","category","merchant","utcdate","userid","userclickcount","offerid","offerclickcount]" ffminput_*.csv


# cd /home/ama/sidana/recsysCode/code/shellscript
# for countryCode in "fi" "ie" "nb" "nl" "no" "pl" "pt" "ru" "se" "uk" "it" "fr"; do
#     filename1="/data/sidana/recsysBaselines/bug_december/inputffm/test/userofferclicks/temp/ffminput_$countryCode.csv"
#     filename2="/data/sidana/recsysBaselines/bug_december/inputffm/test/userofferclicks/ffminput_$countryCode.csv"
#     ./remBrk.sh $filename1 $filename2
# done

# mkdir -p /data/sidana/recsysBaselines/bug_december/tabseparatedinput/userofferclicks



# ./writetabseparatedfile_train.sh
# ./writetabseparatedfile_test.sh

# mkdir -p /data/sidana/recsysBaselines/bug_december/ffmcountryfiles/input/userofferclicks
# mkdir -p /data/sidana/recsysBaselines/bug_december/ffmcountryfiles/input/userofferclicks/model
# mkdir -p /data/sidana/recsysBaselines/bug_december/ffmcountryfiles/input/userofferclicks/traintime
# mkdir -p /data/sidana/recsysBaselines/bug_december/ffmcountryfiles/input/userofferclicks/output
# mkdir -p /data/sidana/recsysBaselines/bug_december/ffmcountryfiles/input/userofferclicks/predicttime

./ffminputuserofferclicks.sh

./ffmtemp.sh
