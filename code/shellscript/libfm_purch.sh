cd ~/recsysBaselines/code/cplusplus/libfm-1.42.src/bin/
start=$(date +%s.%N)
./libFM -task c -train /data/sidana/purch/fm/train.libfm -test /data/sidana/purch/fm/test.libfm -dim '1,1,10' -iter 10 -method sgd -learn_rate 0.001 -regular '0,0,0.01' -init_stdev 0.1 -out /data/sidana/purch/fm/libfmresult -rlog /data/sidana/purch/fm/log
dur=$(echo "$(date +%s.%N) - $start" | bc)
echo "$countryCode:$dur">>/data/sidana/purch/fm/libfm.txt
cd -
