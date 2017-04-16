cd ~/recsysBaselines/code/cplusplus/libfm-1.42.src/scripts
start=$(date +%s.%N) 
echo $countryCode
./triple_format_to_libfm.pl -in /data/sidana/purch/train.csv,/data/sidana/purch/test.csv -target 2 -separator "\t"
dur=$(echo "$(date +%s.%N) - $start" | bc)
echo "$countryCode:$dur">>/data/sidana/purch/triple_format_to_libfm.txt
cd -
