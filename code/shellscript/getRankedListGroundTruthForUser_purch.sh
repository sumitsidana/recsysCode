cd ../java/src
mkdir -p /data/sidana/purch/vectors
javac preProcess/InputOutput.java preProcess/GetRankedListGroundTruthForUser.java
start=$(date +%s.%N)
filename1="/data/sidana/purch/test.csv"
filename2="/data/sidana/purch/libfmresult"
filename3="/data/sidana/purch/vectors/gt_$countryCode"
filename4="/data/sidana/purch/vectors/pr_$countryCode"

java -cp . preProcess.GetRankedListGroundTruthForUser "$filename1" "$filename2" "$filename3" "$filename4"
dur=$(echo "$(date +%s.%N) - $start" | bc)
echo "$countryCode:$dur">>/data/sidana/purch/vectors/getrankedlistgroundtruthforuser.txt
cd -
