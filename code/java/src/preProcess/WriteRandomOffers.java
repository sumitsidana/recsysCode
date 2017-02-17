package preProcess;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
public class WriteRandomOffers {

	public static void writeRandomOffers(String inputFile1,String inputFile2, String outputFile) throws IOException{
		Map<Long,String>indexOffer = new HashMap<Long,String>();
		try (BufferedReader br = new BufferedReader(new FileReader(new File(inputFile1)))) {
			String line;
			br.readLine();
			while ((line = br.readLine()) != null) {
				long index = indexOffer.size() + 1;
				indexOffer.put(index, line);
			}
		}
//		System.out.println(indexOffer);
		int range = indexOffer.size();
		PrintWriter printWriter  = new PrintWriter(outputFile);
		try (BufferedReader br = new BufferedReader(new FileReader(new File(inputFile2)))) {
			String line;
			while ((line = br.readLine()) != null) {
				printWriter.print(line+" ");
				for(int i = 0 ; i < 99 ; i++){
					long rnd = ThreadLocalRandom.current().nextInt(1, range + 1);
//					System.out.println(rnd);
					String offer = indexOffer.get(rnd);
//					System.out.println(offer);
					printWriter.print(offer+" ");
				}
				long rnd = ThreadLocalRandom.current().nextInt(1, range + 1);;
				String offer = indexOffer.get(rnd);
				printWriter.println(offer);
			}
			printWriter.close();
			br.close();
		}
	}

}
