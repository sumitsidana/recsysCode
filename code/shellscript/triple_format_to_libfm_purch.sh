cd ~/recsysBaselines/code/cplusplus/libfm-1.42.src/scripts
start=$(date +%s.%N) 
echo $countryCode
./triple_format_to_libfm.pl -in /data/sidana/purch/fm/train,/data/sidana/purch/fm/test -target 2  -delete_column 3 -separator ","
dur=$(echo "$(date +%s.%N) - $start" | bc)
echo "$countryCode:$dur">>/data/sidana/purch/triple_format_to_libfm.txt
cd -
