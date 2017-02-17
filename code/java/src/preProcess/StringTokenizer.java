package preProcess;
import java.io.*;
import java.util.*;

public class StringTokenizer {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		try (BufferedReader br = new BufferedReader(new FileReader(new File(args[0])))) {
			String line;
			PrintWriter pWHashUser = new PrintWriter (args[1]);
			PrintWriter pWHashedOrigFile = new PrintWriter (args[3]);
			PrintWriter pWHashOffer = new PrintWriter (args[2]);
			Map<String,Long>offerIndex = new LinkedHashMap<String,Long>();
			List<Long>userOffers = new ArrayList<Long>();

			long indexUser = 1;
			while ((line = br.readLine()) != null) {
				userOffers = new ArrayList<Long>();
				int indexofSpace = line.indexOf(" ");
				String user = line.substring(0, indexofSpace);
				String offers = line.substring(indexofSpace + 1);
				String indexedOffers = "";
				String []offersArray = offers.split(" ");
				for(int i = 0 ; i < offersArray.length ; i++){
					String [] offerCountArray = offersArray[i].split(":");
					String offer = offerCountArray[0];
					if(offerIndex.containsKey(offer)){
						long index = offerIndex.get(offer);
						//indexedOffers = indexedOffers.concat(index+":1 ");
						//put index in arraylist
						userOffers.add(index);
					}
					else{
						long index = offerIndex.size()+1;
						//indexedOffers = indexedOffers.concat(index+":1 ");
						offerIndex.put(offer, index);
						pWHashOffer.println(offer+" "+index);
						userOffers.add(index);
					}
				}
				pWHashUser.println(user+" "+indexUser);
				Collections.sort(userOffers);
				pWHashedOrigFile.print(indexUser+" ");
				for(int i = 0 ; i < userOffers.size() ; i++){
					pWHashedOrigFile.print(userOffers.get(i)+":1 ");
				}
				pWHashedOrigFile.println();
				indexUser++;

			}
			br.close();
			pWHashUser.close();
			pWHashedOrigFile.close();
			pWHashOffer.close();
		}
	}
}