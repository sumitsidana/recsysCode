package postProcess;
import java.io.*;

public class Operations {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		postProcess.InputOutput.writeEvaluationFile(args[0], args[1], args[2]);
		//		postProcess.InputOutput.mergeFilesOrderInputFile2(args[0], args[1], args[2]);
		//		postProcess.InputOutput.extractNumLines(args[0], args[1]);
		//		postProcess.InputOutput.findMinimumMaximum(args[0]);
		//		postProcess.InputOutput.computeProduct(args[0], args[1]);
		//		postProcess.InputOutput.computeSum(args[0], args[1]);
	}
}
