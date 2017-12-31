package preProcess;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;

public class XMLParser extends DefaultHandler  {

	public String ancestorId = null;
	public String name_att = null;
	public String id = null;
	public String filenameTemp = null;
	public String filename;

	Locator locator;
	public void setDocumentLocator(Locator locator) {
		this.locator = locator;
		filenameTemp = locator.getSystemId();
		int indexLastSlash = filenameTemp.lastIndexOf("/");
		filename = filenameTemp.substring(indexLastSlash+1);

	}
	// TODO Auto-generated method stub
	public void startElement(String uri, String localName,
			String qName, Attributes attributes)
					throws SAXException 
	{

		if(qName.equals("Category")){
			//			System.out.println("localname: "+filename);
			int length = attributes.getLength();
			for(int i = 0 ; i < length ; i++){
				String name = attributes.getQName(i);
				if(name.equals("ancestorIds")){
					ancestorId = attributes.getValue(i);
				}
				if(name.equals("name")){
					name_att = attributes.getValue(i);
				}
				if(name.equals("id")){
					id = attributes.getValue(i);
				}
				System.out.println("Name:" + name);
				String value = attributes.getValue(i);
				System.out.println("Value:" + value);
			}

			try {
				OutputStream outputStream;
				outputStream = new FileOutputStream(new File("/Users/sumitsidana/Desktop/category.anon/"+filename),true);
				XMLStreamWriter out = XMLOutputFactory.newInstance().createXMLStreamWriter(
						new OutputStreamWriter(outputStream, "utf-8"));
				out.writeStartElement("Category");
				out.writeAttribute("ancestorIds", ancestorId);
				out.writeAttribute("name", name_att);
				out.writeAttribute("id", id);
				out.writeEndElement();
				out.close();
			} catch (FileNotFoundException | UnsupportedEncodingException | XMLStreamException | FactoryConfigurationError e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		}

	}



}
