for filename in default/*.csv.anon main/*.csv.anon userclicks/*.csv.anon userofferclicks/*.csv.anon;
do
	head -10 "${filename}" > "sample/${filename}" ;
done
