for countryCode in "at" "be" "br" "ch" "cz" "de" "dk" "es" "fi" "ie" "nb" "nl" "no" "pl" "pt" "ru" "se" "uk" "it" "fr"; do
filename1="/data/sidana/recsysBaselines/experiments/stats/testusers/$countryCode"
filename2="/data/sidana/recsysBaselines/experiments/popcountryfiles/input/mostpopularitems_$countryCode.txt"
filename3="/data/sidana/recsysBaselines/experiments/popcountryfiles/output/vectors/pr_$countryCode"
paste -d ' ' "$filename1" "$filename2" > "$filename3"
done
