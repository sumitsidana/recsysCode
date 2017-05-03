cd ../java/src
mkdir -p /data/sidana/purch/fm/vectors
javac preProcess/InputOutput.java preProcess/GetRankedListGroundTruthForUser.java
start=$(date +%s.%N)
filename1="/data/sidana/purch/fm/test"
filename2="/data/sidana/purch/fm/libfmresult"
filename3="/data/sidana/purch/fm/vectors/gt_$countryCode"
filename4="/data/sidana/purch/fm/vectors/pr_$countryCode"

java -cp . preProcess.GetRankedListGroundTruthForUser "$filename1" "$filename2" "$filename3" "$filename4"
dur=$(echo "$(date +%s.%N) - $start" | bc)
echo "$countryCode:$dur">>/data/sidana/purch/fm/vectors/getrankedlistgroundtruthforuser.txt
cd -
