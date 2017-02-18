cd ../java/src/
javac preProcess/RemoveBrackets.java preProcess/InputOutput.java
filename1=$1
filename2=$2
java -cp . preProcess.RemoveBrackets $filename1 $filename2

