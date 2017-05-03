cd ../java/src/
mkdir -p /data/sidana/purch/fm/len3/rv
javac preProcess/ConvertIntoRelVecGeneralized.java

    start=$(date +%s.%N)
	filename1="/data/sidana/purch/fm/vectors/gt_$countryCode"
	filename2="/data/sidana/purch/fm/vectors/pr_$countryCode"
	filename3="/data/sidana/purch/fm/len3/rv/relevanceVector_$countryCode"
	java -cp . preProcess.ConvertIntoRelVecGeneralized "$filename1" "$filename2" "$filename3" 30
    dur=$(echo "$(date +%s.%N) - $start" | bc)
    echo "$countryCode:$dur">>/data/sidana/purch/fm/len3/rv/convertintorelvecgeneralized.txt

cd -
