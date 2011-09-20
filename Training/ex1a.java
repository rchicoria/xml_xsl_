import java.io.*;
import javax.xml.transform.*;
import org.jdom.*;
import org.jdom.output.*;

public class ex1a
{
	public static void main(String[] args) throws IOException
	{
		// Create a simple XML document
		Element classElement = new Element("class");
		Document myDocument = new Document(classElement);

		Element student = new Element("student");

        Element name = new Element("name");
        name.addContent("Alberto");
        student.addContent(name);

        Element age = new Element("age");
        age.addContent("21");
        student.addContent(age);

        classElement.addContent(student);

        student = new Element("student");

        name = new Element("name");
        name.addContent("Patricia");
        student.addContent(name);

        age = new Element("age");
        age.addContent("22");
        student.addContent(age);

        classElement.addContent(student);

        student = new Element("student");

        name = new Element("name");
        name.addContent("Luis");
        student.addContent(name);

        age = new Element("age");
        age.addContent("21");
        student.addContent(age);

        classElement.addContent(student);


		// Write it to disk
		XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
		FileWriter writer = new FileWriter("publications.xml");
		outputter.output(myDocument, writer);
		writer.close();
	}
}

