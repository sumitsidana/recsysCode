for d in */ ; do
    echo "$d"
    fname=`basename $d`
    echo $fname
    cp "$d/part-00000" "/home/ama/sidana/recsysBaselines/data/input/countryfiles/$fname"
done
