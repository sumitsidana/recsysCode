DIRECTORY=/data/sidana/recsysBaselines/rawfiles/
header="$1"
for i in $DIRECTORY/clicksTrainPartial_*; do
    echo -e "$header" | cat - "$i" > /tmp/out && mv /tmp/out $i
done
