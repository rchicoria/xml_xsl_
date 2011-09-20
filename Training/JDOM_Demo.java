/*
* Small application that shows how to generate XML using JDOM.
*
* (c) Paulo Marques (pmarques@dei.uc.pt), DEI/FCTUC
* 25-Aug-2005
*/

import java.io.*;
import javax.xml.transform.*;
import org.jdom.*;
import org.jdom.output.*;

public class JDOM_Demo
{
	public static void main(String[] args) throws IOException
	{
		// Create a simple XML document
		Element pubElement = new Element("publications");
		Document myDocument = new Document(pubElement);
		
		Element autor = new Element("author");
		autor.addContent("Henrique Madeira");
		pubElement.addContent(autor);
		
		// Write it to disk
		XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
		FileWriter writer = new FileWriter("publications.xml");
		outputter.output(myDocument, writer);
		writer.close();
	}
}
