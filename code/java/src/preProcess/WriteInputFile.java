package preProcess;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class WriteInputFile {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		Map<String,Map<String,Long>>userItemRatingMap = new 
				TreeMap<String,Map<String,Long>>(); 
		try (BufferedReader br = new BufferedReader(new FileReader(new File(args[0])))) {
			String line;
			br.readLine();
			while ((line = br.readLine()) != null) {
				String [] array = line.split(",");
				String user = (array[0]);
				String item = (array[1]);
				long rating = 1;
				if(userItemRatingMap.containsKey(user)){
					TreeMap<String,Long>itemRatingMap = 
							(TreeMap<String, Long>) userItemRatingMap.get(user);
					if(itemRatingMap.containsKey(item)){
						itemRatingMap.put(item, rating);
					}
					else{
						itemRatingMap.put(item, rating);
					}
					userItemRatingMap.put(user, itemRatingMap);
				}
				else{
					TreeMap<String,Long>itemRatingMap = new TreeMap<String,Long>();
					itemRatingMap.put(item,rating);
					userItemRatingMap.put(user, itemRatingMap);
				}
			}
			br.close();
		}
		PrintWriter bufferedWriterUser = new PrintWriter (args[1]);
		for(Map.Entry<String,Map<String,Long>> entry : userItemRatingMap.entrySet()) {
			String user = entry.getKey();
			bufferedWriterUser.print(user+" ");
			TreeMap<String,Long>itemRatingMap = (TreeMap<String, Long>) entry.getValue();
			for(Map.Entry<String, Long>innerEntry:itemRatingMap.entrySet()){
				String item = innerEntry.getKey();
				long rating = innerEntry.getValue();
				bufferedWriterUser.print(item+":"+rating+" ");
			}
			bufferedWriterUser.println();
		}
		bufferedWriterUser.close();

	}

}

