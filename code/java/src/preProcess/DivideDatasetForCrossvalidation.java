package preProcess;

import java.io.IOException;

public class DivideDatasetForCrossvalidation {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		/*
		 * Basic purpose: Divide data into train and test
		 */
		InputOutput io = new InputOutput();
		long startTime = System.currentTimeMillis();

		double divisionParameter = Double.parseDouble(args[0]);
		long numberLines = io.numberofLines(args[1]);
		System.out.println("number of lines in the file: "+ numberLines);
		long trainFileLength =  (long) (divisionParameter*numberLines);
		System.out.println("number of lines in the train file: "+trainFileLength);
		System.out.println("number of lines in the test file: "+ (numberLines - trainFileLength));
		io.writeTrainTestFiles(trainFileLength, args[1], args[2], args[3]);
		io.writeTrainTestFiles(trainFileLength, args[4], args[5], args[6]);

		long stopTime = System.currentTimeMillis();
		long elapsedTime = stopTime - startTime;
		System.out.println(elapsedTime);

		io.writeTestFileProperFormat(args[7], args[8], args[9]);
		io.writeTestUsers(args[10], args[11]);
	}
}