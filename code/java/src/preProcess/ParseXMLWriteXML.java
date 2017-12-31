package preProcess;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class ParseXMLWriteXML {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		// TODO Auto-generated method stub
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser saxParser = factory.newSAXParser();
		File dir = new File("/Users/sumitsidana/category.anon/");
		String[] chld = dir.list();
		Arrays.sort(chld);
		for(int i = 0 ; i < chld.length ; i++){
			System.out.println("processing file" + chld[i]);
			saxParser.parse("/Users/sumitsidana/category.anon/"+chld[i]
					, new XMLParser());
		}
	}
}
