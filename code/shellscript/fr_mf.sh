cd ../java
javac preProcess/InputOutput.java preProcess/GetRankedListGroundTruthMF.java
java -cp . preProcess.GetRankedListGroundTruthMF /data/sidana/recsysBaselines/experiments/mfcountryfiles/implicit/output/ratingsTest_fr.csv /data/sidana/recsysBaselines/experiments/mfcountryfiles/implicit/output/predictions_fr.csv /data/sidana/recsysBaselines/experiments/mfcountryfiles/implicit/output/vectors/gt_fr /data/sidana/recsysBaselines/experiments/mfcountryfiles/implicit/output/vectors/pr_fr
javac preProcess/ConvertIntoRelVecGeneralized.java
java -cp . preProcess.ConvertIntoRelVecGeneralized /data/sidana/recsysBaselines/experiments/mfcountryfiles/implicit/output/vectors/gt_fr /data/sidana/recsysBaselines/experiments/mfcountryfiles/implicit/output/vectors/pr_fr /data/sidana/recsysBaselines/experiments/mfcountryfiles/implicit/output/len1/rv/relevanceVector_fr 5
java -cp . preProcess.ConvertIntoRelVecGeneralized /data/sidana/recsysBaselines/experiments/mfcountryfiles/implicit/output/vectors/gt_fr /data/sidana/recsysBaselines/experiments/mfcountryfiles/implicit/output/vectors/pr_fr /data/sidana/recsysBaselines/experiments/mfcountryfiles/implicit/output/len2/rv/relevanceVector_fr 15
java -cp . preProcess.ConvertIntoRelVecGeneralized /data/sidana/recsysBaselines/experiments/mfcountryfiles/implicit/output/vectors/gt_fr /data/sidana/recsysBaselines/experiments/mfcountryfiles/implicit/output/vectors/pr_fr /data/sidana/recsysBaselines/experiments/mfcountryfiles/implicit/output/len3/rv/relevanceVector_fr 30
java -cp . preProcess.ConvertIntoRelVecGeneralized /data/sidana/recsysBaselines/experiments/mfcountryfiles/implicit/output/vectors/gt_fr /data/sidana/recsysBaselines/experiments/mfcountryfiles/implicit/output/vectors/pr_fr /data/sidana/recsysBaselines/experiments/mfcountryfiles/implicit/output/len4/rv/relevanceVector_fr 100
cd ../python
python3 compOfflineEvalMetrics_len1.py /data/sidana/recsysBaselines/experiments/mfcountryfiles/implicit/output/len1 fr
python3 compOfflineEvalMetrics_len2.py /data/sidana/recsysBaselines/experiments/mfcountryfiles/implicit/output/len2 fr
python3 compOfflineEvalMetrics_len3.py /data/sidana/recsysBaselines/experiments/mfcountryfiles/implicit/output/len3 fr
python3 compOfflineEvalMetrics_len4.py /data/sidana/recsysBaselines/experiments/mfcountryfiles/implicit/output/len4 fr
