package postProcess;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class InputOutput {
	public static <K, V extends Comparable<? super V>> Map<K, V> 
	sortByValue( Map<K, V> map )
	{
		List<Map.Entry<K, V>> list =
				new LinkedList<Map.Entry<K, V>>( map.entrySet() );
		Collections.sort( list, new Comparator<Map.Entry<K, V>>()
		{
			public int compare( Map.Entry<K, V> o1, Map.Entry<K, V> o2 )
			{
				return (o2.getValue()).compareTo( o1.getValue() );
			}
		} );

		Map<K, V> result = new LinkedHashMap<K, V>();
		for (Map.Entry<K, V> entry : list)
		{
			result.put( entry.getKey(), entry.getValue() );
		}
		return result;
	}

	public static void writeEvaluationFile(String inputFile,String outputFile, String evalMetric) throws IOException{

		/*
		 * java -cp . postProcess.Operations 
		 * ../../data/output/fmcountryfilesv3/vectors/len1/em/evalMetrics 
		 * ../../data/output/fmcountryfilesv3/vectors/len1/em/dat.meanaverageprecision Mean\ Average\ Precision
		 */
		//"at","be","br","ch","cz","de","dk",
		List<String> countryCodes = Arrays.asList("fi","nb","nl","no","pl","pt","ru","se","uk","it","fr");
		PrintWriter printWriter = new PrintWriter (outputFile);
		Map<String,Double>countryMetrics = new LinkedHashMap<String,Double>();
		for(int i = 0 ; i < countryCodes.size() ; i++){
			try (BufferedReader br = new BufferedReader(new FileReader(new File(inputFile+"_"+countryCodes.get(i))))) {
				String line;
				while ((line = br.readLine()) != null) {
					if(line.contains(evalMetric)){
						countryMetrics.put(countryCodes.get(i), Double.parseDouble(line.replace(evalMetric+": ", "")));
						//						printWriter.println(countryCodes.get(i)+" "+line.replace(evalMetric+": ", ""));
					}
				}
			}
		}
		Map<String,Double>reverseSortedMap = sortByValue(countryMetrics);
		for(Map.Entry<String,Double>entry:reverseSortedMap.entrySet()){
			printWriter.println(entry.getKey()+" "+entry.getValue());
		}
		printWriter.close();
	}

	public static void mergeFilesOrderInputFile2(String inputFile1,String inputFile2,String outputFile) throws NumberFormatException, IOException{
		/*
		 * Use Case:
		 * java -cp . postProcess.Operations ~/thesis_project/trunk/figs/gnuplot/dat.meanaverageprecisionfm 
		 * ~/thesis_project/trunk/figs/gnuplot/dat.MAPPastInteractionsPopularity 
		 * ~/thesis_project/trunk/figs/gnuplot/dat.mapfmpastinteractionspopularity
		 */
		PrintWriter printWriter = new PrintWriter (outputFile);
		Map<String,Double>countryValue = new TreeMap<String,Double>();
		System.out.println(inputFile1);
		try (BufferedReader br = new BufferedReader(new FileReader(inputFile1))) {
			String line;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
				String [] array = line.split(" ");
				String countryCode = array[0];
				double val = Double.parseDouble(array[1]);
				countryValue.put(countryCode, val);
				//				System.out.println(countryValue);
			}
			br.close();
		}
		//		System.out.println(countryValue);
		try (BufferedReader br = new BufferedReader(new FileReader(inputFile2))) {
			String line;
			while ((line = br.readLine()) != null) {
				//				System.out.println(line);
				String [] array = line.split(" ");
				String countryCode = array[0];
				printWriter.print(countryCode+" ");
				for(int i = 1 ; i < array.length ; i++){
					printWriter.print(array[i]+" ");
				}

				//				System.out.println(countryValue.get(countryCode));
				printWriter.println(countryValue.get(countryCode));
			}
			br.close();
			printWriter.close();
		}
	}

	public static void extractNumLines(String inputFile,String outputFile) throws IOException{
		/*
		 *  //java -cp . postProcess.Operations ../../data/output/countryfiles/numTestUsersCountriesTemp ../../data/output/countryfiles/numTestUsersCountries
		 */

		try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
			PrintWriter printWriter = new PrintWriter (outputFile);
			String line;
			while ((line = br.readLine()) != null) {
				line = line.trim().replace("testUsers_", "");
				String [] array = line.split(" ");
				String linetoBeWritten = array[1].concat(" "+array[0]);
				printWriter.println(linetoBeWritten);
			}
			printWriter.close();
		}
	}

	public static void findMinimumMaximum(String inputFile) throws IOException{
		try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
			String line;
			double min = Double.MAX_VALUE;
			int index = 0;
			while ((line = br.readLine()) != null) {
				index++;
				if(index >=24){
					String [] array = line.split("\t");
					int beginIndex = array[2].indexOf("=");
					double score = Double.parseDouble(array[2].substring(beginIndex+1));
					if(score < min)
					{
						min = score;
					}
				}
			}
			System.out.println(min);
		}
	}

	public static void computeProduct(String inputFile,String outputFile)throws IOException{
		try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
			String line;
			PrintWriter printWriter = new PrintWriter (outputFile);
			while((line = br.readLine())!=null){
				String [] array = line.split(" ");
				double product = Double.parseDouble(array[1])*Double.parseDouble(array[2]);
				printWriter.println(array[0]+" "+product);
			}
			printWriter.close();
		}
	}

	public static void computeSum(String inputFile,String outputFile)throws IOException{
		try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
			String line;
			PrintWriter printWriter = new PrintWriter (outputFile);
			double val = 0.0;
			while((line = br.readLine())!=null){				
				String [] array = line.split(" ");
				val = val + Double.parseDouble(array[1]);
				printWriter.println(line);
			}
			printWriter.println(val);
			printWriter.close();
		}
	}
}


