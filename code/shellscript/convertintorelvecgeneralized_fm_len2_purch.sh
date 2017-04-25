cd ../java/src/
mkdir -p /data/sidana/purch/len2/rv
javac preProcess/ConvertIntoRelVecGeneralized.java
    start=$(date +%s.%N)
	filename1="/data/sidana/purch/vectors/gt_$countryCode"
	filename2="/data/sidana/purch/vectors/pr_$countryCode"
	filename3="/data/sidana/purch/len2/rv/relevanceVector_$countryCode"
	java -cp . preProcess.ConvertIntoRelVecGeneralized "$filename1" "$filename2" "$filename3" 15
    dur=$(echo "$(date +%s.%N) - $start" | bc)
    echo "$countryCode:$dur">>/data/sidana/purch/len2/rv/convertintorelvecgeneralized.txt

cd -
