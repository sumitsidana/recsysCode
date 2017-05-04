package preProcess;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class InputOutput {

	public static <K, V extends Comparable<? super V>> Map<K, V> 
	sortByValue( Map<K, V> map )
	{
		List<Map.Entry<K, V>> list =
				new LinkedList<>( map.entrySet() );
		Collections.sort( list, new Comparator<Map.Entry<K, V>>()
		{
			@Override
			public int compare( Map.Entry<K, V> o1, Map.Entry<K, V> o2 )
			{
				return ( o2.getValue() ).compareTo( o1.getValue() );
			}
		} );

		Map<K, V> result = new LinkedHashMap<>();
		for (Map.Entry<K, V> entry : list)
		{
			result.put( entry.getKey(), entry.getValue() );
		}
		return result;
	}

	public static long numberofLines(String fileName)  throws IOException {
		long numberofLines = 0;
		try (BufferedReader br = new BufferedReader(new FileReader(new File(fileName)))) {
			String line;
			while ((line = br.readLine()) != null) {
				numberofLines++;
				// TODO Auto-generated method stub
			}
			br.close();
		}

		return numberofLines;
	}
	public static void writeTrainTestFiles(long nLinesTrain,String fullFile, String fileTrain, String fileTest) throws IOException{

		PrintWriter printWriterTrain = new PrintWriter (fileTrain);
		PrintWriter printWriterTest = new PrintWriter (fileTest);
		try (BufferedReader br = new BufferedReader(new FileReader(new File(fullFile)))) {
			String line;
			long index = 0;
			while ((line = br.readLine()) != null) {
				line = line.replace("[", "").replace("]", "");
				if(index <=nLinesTrain)
					printWriterTrain.println(line);
				else
					printWriterTest.println(line);
				index++;
				// TODO Auto-generated method stub
			}
			printWriterTest.close();
			printWriterTrain.close();
			br.close();
		}

	}

	public static void removeBrackets(String inputFile, String outputFile) throws IOException{

		PrintWriter printWriterTrain = new PrintWriter (outputFile);
		try (BufferedReader br = new BufferedReader(new FileReader(new File(inputFile)))) {
			String line;
			while ((line = br.readLine()) != null) {
				line = line.replace("[", "").replace("]", "");
				printWriterTrain.println(line);
				// TODO Auto-generated method stub
			}
			printWriterTrain.close();
			br.close();
		}

	}

	public static void writeTestFileProperFormat(String fileName, String usersOffersCounts, String usersOffers) throws IOException{
		/*
		 * Take test clicks file as input and writes them in the desired format
		 * Offer is more relevant if it has been clicked more
		 *  
		 */
		Map<String,Map<String,Long>>usersOffersCount = new LinkedHashMap<String,Map<String,Long>>();
		try (BufferedReader br = new BufferedReader(new FileReader(new File(fileName)))) {
			String line;
			while ((line = br.readLine()) != null) {
				// TODO Auto-generated method stub
				String []array = line.split(",");
				String userId = array[0];
				String offerViewId = array[1];
				Map<String,Long>offerCount = new LinkedHashMap<String,Long>();
				if(usersOffersCount.containsKey(userId)){
					offerCount  = (LinkedHashMap<String, Long>) usersOffersCount.get(userId);
					if(offerCount.containsKey(offerViewId)){
						offerCount.put(offerViewId, offerCount.get(offerViewId)+1);
					}
					else{
						offerCount.put(offerViewId,(long) 1);
					}
				}
				else{
					offerCount = new LinkedHashMap<String,Long>();
					offerCount.put(offerViewId, (long)1);
				}
				usersOffersCount.put(userId, offerCount);
			}
		}
		for (Map.Entry<String, Map<String,Long>> entry : usersOffersCount.entrySet()) {
			String userId = entry.getKey();
			Map<String,Long> offerCounts = (LinkedHashMap<String, Long>) entry.getValue();
			Map<String,Long>revSortedOfferCounts = sortByValue(offerCounts);
			usersOffersCount.put(userId,revSortedOfferCounts);
		}
		PrintWriter pwUOCounts  = new PrintWriter (usersOffersCounts);
		PrintWriter  pwUO= new PrintWriter (usersOffers);

		for (Map.Entry<String, Map<String,Long>> entry : usersOffersCount.entrySet()) {
			String userId = entry.getKey();
			Map<String,Long> offerCounts = (LinkedHashMap<String, Long>) entry.getValue();
			pwUO.print(userId+" ");
			pwUOCounts.print(userId+" ");
			for(Map.Entry<String,Long>internalEntry: offerCounts.entrySet()){
				String offerId = internalEntry.getKey();
				Long count = internalEntry.getValue();
				pwUO.print(offerId+" ");
				pwUOCounts.print(offerId+":"+count+" ");
			}
			pwUO.println();
			pwUOCounts.println();
		}
		pwUO.close();
		pwUOCounts.close();
	}
	public static void writeTestUsers(String testGroundTruth/* dat.clicksTest */,String testUsers) throws IOException{
		try (BufferedReader br = new BufferedReader(new FileReader(new File(testGroundTruth)))) {
			String line;
			PrintWriter printWriter  = new PrintWriter(testUsers);
			Set<String>users = new LinkedHashSet<String>();
			while ((line = br.readLine()) != null) {
				String user = line.substring(0,line.indexOf("\t"));
				users.add(user);

			}
			for(String user: users){
				printWriter.println(user);
			}
			printWriter.close();
		}
	}
	public static void writeTestOffers(String testGroundTruth/* dat.clicksTest */,String testUsers) throws IOException{
		try (BufferedReader br = new BufferedReader(new FileReader(new File(testGroundTruth)))) {
			String line;
			PrintWriter printWriter  = new PrintWriter(testUsers);
			Set<String>offers = new LinkedHashSet<String>();
			while ((line = br.readLine()) != null) {
				String [] array = line.split("\t");
				String offer = array[1];
				offers.add(offer);
			}
			for(String offer: offers){
				printWriter.println(offer);
			}
			printWriter.close();
		}
	}
	public static int countLines(String filename) throws IOException {
		InputStream is = new BufferedInputStream(new FileInputStream(filename));
		try {
			byte[] c = new byte[1024];
			int count = 0;
			int readChars = 0;
			boolean empty = true;
			while ((readChars = is.read(c)) != -1) {
				empty = false;
				for (int i = 0; i < readChars; ++i) {
					if (c[i] == '\n') {
						++count;
					}
				}
			}
			return (count == 0 && !empty) ? 1 : count;
		} finally {
			is.close();
		}
	}
	public static void copyMultipleLines(String fileName,long num) throws IOException{
		String line;
		try (BufferedReader br = new BufferedReader(new FileReader(new File(fileName)))) {
			line = br.readLine();
			// TODO Auto-generated method stub
		}
		long numberofTimestoBeCopied = num;
		PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(fileName, true)));
		for(int i = 0 ; i < numberofTimestoBeCopied ; i++){
			printWriter.println(line);
		}

		printWriter.close();

	}
	public static void writeFileFMFormat(String inputFile, String outputFile) throws IOException{
		try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
			PrintWriter printWriter = new PrintWriter (outputFile);
			String line;
			while ((line = br.readLine()) != null) {
				line = line.replace("[", "").replace("]", "");
				String [] array = line.split(",");
				if(array[0].equals("null")){
					printWriter.println(array[2]+"\t"+array[3]+"\t"+"-1");
				}
				else{
					printWriter.println(array[2]+"\t"+array[3]+"\t"+"1");
				}

			}
			printWriter.close();
		}
	}

	public static void writeFileIntroduceNewLineForSpaces(String inputFile, String outputFile) throws IOException{
		try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
			PrintWriter printWriter = new PrintWriter (outputFile);
			String line;
			while ((line = br.readLine()) != null) {
				line = line.replace("msecs", "");
				line = line.replace(" ","\n");
				printWriter.println(line);
			}
			printWriter.close();
		}
	}

	public static void getRankedListGroundTruthForUser(String groundTruth, String inputFile,String ofGT, String ofPR) throws NumberFormatException, IOException{
		/*
		 * 
		 */
		Map<String,Map<String,Double>> userOfferProbability = new LinkedHashMap<String,Map<String,Double>>();
		Map<String,Map<String,Long>> userOfferGroundTruth = new LinkedHashMap<String,Map<String,Long>>();

		BufferedReader gtBr = new BufferedReader(new FileReader(groundTruth));
		BufferedReader inBr = new BufferedReader(new FileReader(inputFile));
		Map<String,Map<String,Double>>revSortedUserOfferProbability = new LinkedHashMap<String,Map<String,Double>>();

		while(true){
			String partOne = gtBr.readLine();
			String partTwo = inBr.readLine();
			if (partOne == null || partTwo == null)
				break;
			String [] array = partOne.split(",");
			String user = array[0];
			String offer = array[1];

			String gt = array[2];
			String pred = partTwo;

			if(userOfferGroundTruth.containsKey(user)){
				if(gt.equals("1")){
					Map<String,Long>offerGroundTruth = userOfferGroundTruth.get(user);
					if(offerGroundTruth.containsKey(offer)){
						continue;
					}
					offerGroundTruth.put(offer, Long.parseLong(gt));
					userOfferGroundTruth.put(user, offerGroundTruth);
				}
			}
			else{
				Map<String,Long>offerGroundTruth = new LinkedHashMap<String,Long>();
				if(gt.equals("1")){
					offerGroundTruth.put(offer, Long.parseLong(gt));
				}
				userOfferGroundTruth.put(user, offerGroundTruth);
			}

			if(userOfferProbability.containsKey(user)){
				Map<String,Double>offerProbability = userOfferProbability.get(user);
				if(offerProbability.containsKey(offer)){
					double prediction = offerProbability.get(offer);
					if(Double.parseDouble(pred) > prediction)
					{
						offerProbability.put(offer, Double.parseDouble(pred));
					}
				}
				offerProbability.put(offer, Double.parseDouble(pred));
				userOfferProbability.put(user, offerProbability);
			}
			else{
				Map<String,Double>offerProbability = new LinkedHashMap<String,Double>();
				offerProbability.put(offer, Double.parseDouble(pred));
				userOfferProbability.put(user, offerProbability);
			}
		}

		for (Map.Entry<String, Map<String,Double>> entry : userOfferProbability.entrySet()) {
			String userId = entry.getKey();
			Map<String,Double> offerProbability = (LinkedHashMap<String, Double>) entry.getValue();
			Map<String,Double>revSortedOfferProbability = sortByValue(offerProbability);
			revSortedUserOfferProbability.put(userId,revSortedOfferProbability);
		}
		/*
		 * Write revSortedUserOfferProbability
		 * and
		 * UserOfferGroundTruth
		 */

		PrintWriter pWGT = new PrintWriter (ofGT);
		PrintWriter pWPR = new PrintWriter (ofPR);

		for(Entry<String, Map<String,Long>> entry: userOfferGroundTruth.entrySet()){
			String user = entry.getKey();
			pWGT.print(user+" ");
			Map<String,Long>offerGroundTruth =  entry.getValue();
			for(Entry<String,Long>innerEntry: offerGroundTruth.entrySet()){
				pWGT.print(innerEntry.getKey()+" ");
			}
			pWGT.println();
		}
		for(Entry<String, Map<String,Double>> entry: revSortedUserOfferProbability.entrySet()){
			String user = entry.getKey();
			pWPR.print(user+" ");
			Map<String,Double>offerProbability =  entry.getValue();

			for(Entry<String,Double>innerEntry: offerProbability.entrySet()){
				pWPR.print(innerEntry.getKey()+" ");
			}
			pWPR.println();
		}
		gtBr.close();
		inBr.close();
		pWGT.close();
		pWPR.close();
	}

	public static void getRankedListGroundTruthForUserFFM(String groundTruth, String inputFile, String ofGT, String ofPR) throws NumberFormatException, IOException{
		/*
		 * 
		 */
		Map<String,Map<String,Double>> userOfferProbability = new LinkedHashMap<String,Map<String,Double>>();
		Map<String,Map<String,Long>> userOfferGroundTruth = new LinkedHashMap<String,Map<String,Long>>();

		BufferedReader gtBr = new BufferedReader(new FileReader(groundTruth));
		BufferedReader inBr = new BufferedReader(new FileReader(inputFile));
		Map<String,Map<String,Double>>revSortedUserOfferProbability = new LinkedHashMap<String,Map<String,Double>>();
		gtBr.readLine();
		while(true){
			String partOne = gtBr.readLine();
			String partTwo = inBr.readLine();
			if (partOne == null || partTwo == null)
				break;
			String [] array = partOne.split("\t");
			String user = array[0];
			String offer = array[1];

			String gt = array[array.length - 1];
			String pred = partTwo;

			if(userOfferGroundTruth.containsKey(user)){
				if(gt.equals("1")){
					Map<String,Long>offerGroundTruth = userOfferGroundTruth.get(user);
					if(offerGroundTruth.containsKey(offer)){
						//						System.out.println(user+"::::"+offer);
						//						System.out.println("User-Offer Pairs should be unique");
						//						System.exit(1);
						continue;
					}
					offerGroundTruth.put(offer, Long.parseLong(gt));
					userOfferGroundTruth.put(user, offerGroundTruth);
				}
			}
			else{
				Map<String,Long>offerGroundTruth = new LinkedHashMap<String,Long>();
				if(gt.equals("1")){
					offerGroundTruth.put(offer, Long.parseLong(gt));
				}
				userOfferGroundTruth.put(user, offerGroundTruth);
			}

			if(userOfferProbability.containsKey(user)){
				Map<String,Double>offerProbability = userOfferProbability.get(user);
				if(offerProbability.containsKey(offer)){
					//					System.out.println(user+"::::"+offer);
					//					System.out.println("User-Offer Pairs should be unique");
					//					System.exit(1);
					double prediction = offerProbability.get(offer);
					if(Double.parseDouble(pred) > prediction)
					{
						offerProbability.put(offer, Double.parseDouble(pred));
					}

				}
				offerProbability.put(offer, Double.parseDouble(pred));
				userOfferProbability.put(user, offerProbability);
			}
			else{
				Map<String,Double>offerProbability = new LinkedHashMap<String,Double>();
				offerProbability.put(offer, Double.parseDouble(pred));
				userOfferProbability.put(user, offerProbability);
			}
		}

		for (Map.Entry<String, Map<String,Double>> entry : userOfferProbability.entrySet()) {
			String userId = entry.getKey();
			Map<String,Double> offerProbability = (LinkedHashMap<String, Double>) entry.getValue();
			Map<String,Double>revSortedOfferProbability = sortByValue(offerProbability);
			revSortedUserOfferProbability.put(userId,revSortedOfferProbability);
		}
		/*
		 * Write revSortedUserOfferProbability
		 * and
		 * UserOfferGroundTruth
		 */

		PrintWriter pWGT = new PrintWriter (ofGT);
		PrintWriter pWPR = new PrintWriter (ofPR);

		for(Entry<String, Map<String,Long>> entry: userOfferGroundTruth.entrySet()){
			String user = entry.getKey();
			pWGT.print(user+" ");
			Map<String,Long>offerGroundTruth =  entry.getValue();
			for(Entry<String,Long>innerEntry: offerGroundTruth.entrySet()){
				pWGT.print(innerEntry.getKey()+" ");
			}
			pWGT.println();
		}
		for(Entry<String, Map<String,Double>> entry: revSortedUserOfferProbability.entrySet()){
			String user = entry.getKey();
			pWPR.print(user+" ");
			Map<String,Double>offerProbability =  entry.getValue();

			for(Entry<String,Double>innerEntry: offerProbability.entrySet()){
				pWPR.print(innerEntry.getKey()+" ");
			}
			pWPR.println();
		}
		gtBr.close();
		inBr.close();
		pWGT.close();
		pWPR.close();
	}
	public static void getRankedListforUserMF(String groundTruth, String inputFile, String ofGT, String ofPR) throws NumberFormatException, IOException{
		Map<String,Map<String,Double>> userOfferProbability = new LinkedHashMap<String,Map<String,Double>>();
		Map<String,Map<String,Double>> userOfferGroundTruth = new LinkedHashMap<String,Map<String,Double>>();

		BufferedReader gtBr = new BufferedReader(new FileReader(groundTruth));
		BufferedReader inBr = new BufferedReader(new FileReader(inputFile));
		Map<String,Map<String,Double>>revSortedUserOfferProbability = new LinkedHashMap<String,Map<String,Double>>();

		String partone;
		while ((partone = gtBr.readLine()) != null) {
			partone = partone.replace("Rating", "").replaceAll("[()]", "");
			String [] array = partone.split(",");
			String user = array[0];
			String offer = array[1];
			String gt = array[2];

			if(userOfferGroundTruth.containsKey(user)){
				if(gt.equals("1.0")){
					Map<String,Double> offerGroundTruth = userOfferGroundTruth.get(user);
					offerGroundTruth.put(offer,Double.parseDouble(gt));
					userOfferGroundTruth.put(user,offerGroundTruth);
				}
			}
			else{
				Map<String,Double>offerGroundTruth = new LinkedHashMap<String,Double>();
				if(gt.equals("1.0")){
					offerGroundTruth.put(offer, Double.parseDouble(gt));
				}
				userOfferGroundTruth.put(user,offerGroundTruth);
			}
		}
		String parttwo;
		while ((parttwo = inBr.readLine()) != null) {
			parttwo = parttwo.replaceAll("[()]", "");
			String [] array = parttwo.split(",");
			String user = array[0];
			String offer = array[1];
			String pred = array[2];
			if(userOfferProbability.containsKey(user)){
				Map<String,Double> offerProbability = userOfferProbability.get(user);
				offerProbability.put(offer, Double.parseDouble(pred));
				userOfferProbability.put(user,offerProbability);
			}
			else{
				Map<String,Double>offerProbability = new LinkedHashMap<String,Double>();
				offerProbability.put(offer, Double.parseDouble(pred));
				userOfferProbability.put(user,offerProbability);
			}
		}

		for (Map.Entry<String, Map<String,Double>> entry : userOfferProbability.entrySet()) {
			String userId = entry.getKey();
			Map<String,Double> offerProbability = (LinkedHashMap<String, Double>) entry.getValue();
			Map<String,Double>revSortedOfferProbability = sortByValue(offerProbability);
			revSortedUserOfferProbability.put(userId,revSortedOfferProbability);
		}

		TreeMap<String,Map<String,Double>>sortedUserOfferGroundTruth = new TreeMap<String,Map<String,Double>>();
		TreeMap<String,Map<String,Double>>sortedUserOfferProbability = new TreeMap<String, Map<String, Double>>() ;
		sortedUserOfferGroundTruth.putAll(userOfferGroundTruth);
		sortedUserOfferProbability.putAll(revSortedUserOfferProbability);

		for(Entry<String,Map<String,Double>> entry: sortedUserOfferGroundTruth.entrySet()){
			String user = entry.getKey();
			if(sortedUserOfferProbability.containsKey(user)){
				continue;
			}
			else{
				Map<String,Double>offerProbability = new LinkedHashMap<String,Double>();
				sortedUserOfferProbability.put(user, offerProbability);
			}
		}

		/*
		 * Write revSortedUserOfferProbability
		 * and
		 * UserOfferGroundTruth
		 */

		PrintWriter pWGT = new PrintWriter (ofGT);
		PrintWriter pWPR = new PrintWriter (ofPR);

		for(Entry<String, Map<String,Double>> entry: sortedUserOfferGroundTruth.entrySet()){
			String user = entry.getKey();
			pWGT.print(user+" ");
			Map<String,Double>offerGroundTruth =  entry.getValue();
			for(Entry<String,Double>innerEntry: offerGroundTruth.entrySet()){
				pWGT.print(innerEntry.getKey()+" ");
			}
			pWGT.println();
		}
		for(Entry<String, Map<String,Double>> entry: sortedUserOfferProbability.entrySet()){
			String user = entry.getKey();
			pWPR.print(user+" ");
			Map<String,Double>offerProbability =  entry.getValue();

			for(Entry<String,Double>innerEntry: offerProbability.entrySet()){
				pWPR.print(innerEntry.getKey()+" ");
			}
			pWPR.println();
		}
		gtBr.close();
		inBr.close();
		pWGT.close();
		pWPR.close();
	}

	public static void inputNegativeFeedbackToNeutral(String inputFile, String outputFile) throws IOException{
		String line;
		PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(outputFile, true)));
		try (BufferedReader br = new BufferedReader(new FileReader(new File(inputFile)))) {
			line = br.readLine();
			printWriter.println(line);
			while ((line = br.readLine()) != null) {
				String [] array = line.split("\t");
				if(array[2].equals("1")){
					printWriter.println(line);
				}
				else{
					String linetobeWritten = array[0]+"\t"+array[1]+"\t"+"0";
					printWriter.println(linetobeWritten);
				}

			}
			// TODO Auto-generated method stub
		}
		printWriter.close();

	}
	public static void writeTabSeparatedFile(String inputFile, String outputFile, String header) throws IOException{
		String line;
		PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(outputFile, true)));
		try (BufferedReader br = new BufferedReader(new FileReader(new File(inputFile)))) {
			line = br.readLine();
			printWriter.println(header);
			while ((line = br.readLine()) != null) {
				String [] array = line.split(",");
				String useridclicks = array[0];
				for(int i = 2  ; i  < array.length ; i++){
					if(i==array.length - 2||i==array.length-4){
						continue;
					}
					printWriter.print(array[i]+"\t");
				}
				if(useridclicks.equals("null")){
					printWriter.println("0");
				}
				else{
					printWriter.println("1");
				}

			}
			// TODO Auto-generated method stub
		}
		printWriter.close();

	}

	public static void writeDefaultTabSeparatedFile(String inputFile, String outputFile, String header) throws IOException{
		String line;
		PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(outputFile, true)));
		try (BufferedReader br = new BufferedReader(new FileReader(new File(inputFile)))) {
			line = br.readLine();
			printWriter.println(header);
			while ((line = br.readLine()) != null) {
				String [] array = line.split(",");
				String useridclicks = array[0];

				printWriter.print(array[2]+"\t");
				printWriter.print(array[3]+"\t");

				if(useridclicks.equals("null")){
					printWriter.println("0");
				}
				else{
					printWriter.println("1");
				}

			}
			// TODO Auto-generated method stub
		}
		printWriter.close();

	}

	public static void writeEvaluationFile(String inputFile, String outputFile, String evalMetric, String datafile) throws IOException{

		//List<String> countryCodes = Arrays.asList("0.1","0.2","0.3","0.4","0.5","0.6","0.7","0.8","0.9");
		//		List<String> countryCodes = Arrays.asList("10","20","30","50","75","100");
		List<String> countryCodes = Arrays.asList("1","2","3","4");

		PrintWriter printWriter = new PrintWriter (outputFile);
		Map<String,Double>countryMetrics = new LinkedHashMap<String,Double>();
		for(int i = 0 ; i < countryCodes.size() ; i++){
			try (BufferedReader br = new BufferedReader(new FileReader(new File(inputFile+"/"+"len"+countryCodes.get(i)+"/em/evalMetrics_")))) {
				String line;
				while ((line = br.readLine()) != null) {
					if(line.contains(evalMetric)){
						countryMetrics.put(countryCodes.get(i), Double.parseDouble(line.replace(evalMetric+": ", "")));
						//						printWriter.println(countryCodes.get(i)+" "+line.replace(evalMetric+": ", ""));
					}
				}
			}
		}
		for(Map.Entry<String,Double>entry:countryMetrics.entrySet()){
			printWriter.println(entry.getKey()+" "+entry.getValue());
		}

		printWriter.close();
	}

	/*
	 * This is for indexing cofactor files
	 * 
	 */
	public static void indexTrainAndTestAndTest_All_Files(String inputFile1, String inputFile2, String inputFile3, String outputFile1, String
			outputFile2, String outputFile3) throws IOException{
		PrintWriter printWriter1 = new PrintWriter (outputFile1);
		PrintWriter printWriter2 = new PrintWriter (outputFile2);
		PrintWriter printWriter3 = new PrintWriter (outputFile3);
		Map<Long,Long> userIndex = new LinkedHashMap<Long,Long>();
		Map<Long,Long> itemIndex = new LinkedHashMap<Long,Long>();
		try (BufferedReader br = new BufferedReader(new FileReader(inputFile1))) {
			String line;
			line = br.readLine();
			printWriter1.println(line);
			while ((line = br.readLine()) != null) {
				String [] array = line.split(",");
				long user = Long.parseLong(array[1]);
				long item = Long.parseLong(array[2]);
				long indexUser;
				if(userIndex.containsKey(user)){
					indexUser = userIndex.get(user);
				}
				else{
					indexUser = userIndex.size();
					userIndex.put(user, indexUser);
				}
				long indexItem;
				if(itemIndex.containsKey(item)){
					indexItem = itemIndex.get(item);
				}
				else{
					indexItem = itemIndex.size();
					itemIndex.put(item, indexItem);
				}
				String lineWritten = array[0]+","+indexUser+","+indexItem;
				printWriter1.println(lineWritten);				
			}
		}

		try (BufferedReader br = new BufferedReader(new FileReader(inputFile2))) {
			String line;
			line = br.readLine();
			printWriter2.println(line);
			while ((line = br.readLine()) != null) {
				String [] array = line.split(",");
				long user = Long.parseLong(array[1]);
				long item = Long.parseLong(array[2]);
				long indexUser;
				if(userIndex.containsKey(user)){
					indexUser = userIndex.get(user);
				}
				else{
					indexUser = userIndex.size();
					userIndex.put(user, indexUser);
				}
				long indexItem;
				if(itemIndex.containsKey(item)){
					indexItem = itemIndex.get(item);
				}
				else{
					indexItem = itemIndex.size();
					itemIndex.put(item, indexItem);
				}
				String lineWritten = array[0]+","+indexUser+","+indexItem;
				printWriter2.println(lineWritten);				
			}
		}

		try (BufferedReader br = new BufferedReader(new FileReader(inputFile3))) {
			String line;
			line = br.readLine();
			printWriter3.println(line);
			while ((line = br.readLine()) != null) {
				String [] array = line.split(",");
				long user = Long.parseLong(array[1]);
				long item = Long.parseLong(array[2]);
				long indexUser;
				if(userIndex.containsKey(user)){
					indexUser = userIndex.get(user);
				}
				else{
					indexUser = userIndex.size();
					userIndex.put(user, indexUser);
				}
				long indexItem;
				if(itemIndex.containsKey(item)){
					indexItem = itemIndex.get(item);
				}
				else{
					indexItem = itemIndex.size();
					itemIndex.put(item, indexItem);
				}
				String lineWritten = array[0]+","+indexUser+","+indexItem;
				printWriter3.println(lineWritten);				
			}
		}
		printWriter1.close();
		printWriter2.close();
		printWriter3.close();
	}
	public static void writeGroundTruthForBPRMF(String inputFile, String outputFile) throws IOException{
		PrintWriter gtTest = new PrintWriter (outputFile);
		try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
			String line;
			//			br.readLine();
			while ((line = br.readLine()) != null) {
				String [] array = line.split("\t");
				long user = Long.parseLong(array[0]);
				gtTest.print(user+" ");
				String itemList = array[1];
				String [] itemArray = itemList.split(",");
				for(int i = 0 ; i < itemArray.length ; i++){
					String [] itemScore = itemArray[i].split(":");
					String item = itemScore[0];
					String score = itemScore[1];
					if(score.equals("4")){
						gtTest.print(item+" ");
					}
				}
				gtTest.println();

			}
		}
		gtTest.close();
	}	
	/*
	 * For indexing general train and test files
	 * 
	 */
	public static void indexTrainAndTestFiles(String inputFile1, String inputFile2, String outputFile1, String
			outputFile2) throws IOException{
		PrintWriter printWriter1 = new PrintWriter (outputFile1);
		PrintWriter printWriter2 = new PrintWriter (outputFile2);
		Map<String,Long> userIndex = new LinkedHashMap<String,Long>();
		Map<String,Long> itemIndex = new LinkedHashMap<String,Long>();
		try (BufferedReader br = new BufferedReader(new FileReader(inputFile1))) {
			String line;
			while ((line = br.readLine()) != null) {
				String [] array = line.split(",");
				String user = (array[0]);
				String item = (array[1]);
				long indexUser;
				if(userIndex.containsKey(user)){
					indexUser = userIndex.get(user);
				}
				else{
					indexUser = userIndex.size();
					userIndex.put(user, indexUser);
				}
				long indexItem;
				if(itemIndex.containsKey(item)){
					indexItem = itemIndex.get(item);
				}
				else{
					indexItem = itemIndex.size();
					itemIndex.put(item, indexItem);
				}
				String lineWritten = indexUser+","+indexItem+","+array[2]+","+array[3];
				printWriter1.println(lineWritten);		
			}
		}

		try (BufferedReader br = new BufferedReader(new FileReader(inputFile2))) {
			String line;
			while ((line = br.readLine()) != null) {
				String [] array = line.split(",");
				String user = (array[0]);
				String item = (array[1]);
				long indexUser;
				if(userIndex.containsKey(user)){
					indexUser = userIndex.get(user);
				}
				else{
					indexUser = userIndex.size();
					userIndex.put(user, indexUser);
				}
				long indexItem;
				if(itemIndex.containsKey(item)){
					indexItem = itemIndex.get(item);
				}
				else{
					indexItem = itemIndex.size();
					itemIndex.put(item, indexItem);
				}
				String lineWritten = indexUser+","+indexItem+","+array[2]+","+array[3];
				printWriter2.println(lineWritten);				
			}
		}
		printWriter1.close();
		printWriter2.close();
		//		printWriter3.close();
	}
}
