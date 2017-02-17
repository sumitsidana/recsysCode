package preProcess;



import java.io.*;
import java.util.*;

public class CopyMultipleLines {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		List<String> countryCodes = Arrays.asList("at","be","br","ch","cz","de","dk","es","fi","ie","nb","nl","no","pl","pt","ru","se","uk","it","fr");
		for(int i = 0 ; i < countryCodes.size() ; i++){
			String countryCode = countryCodes.get(i);
			int numLines = InputOutput.countLines(args[1]+"/"+countryCode);
			InputOutput.copyMultipleLines(args[0]+"/"+"mostpopularitems_"+countryCode+".txt", numLines);
		}
	}

}
