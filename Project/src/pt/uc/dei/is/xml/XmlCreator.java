package pt.uc.dei.is.xml;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import org.jdom.*;
import org.jdom.output.*;

public class XmlCreator {
	
	private static Document createXML(ArrayList<Ebook> ebooks)
	{
		Element listElement = new Element("list");
		Document document = new Document(listElement);
		
		Iterator<Ebook> iterator = ebooks.iterator();
		while (iterator.hasNext())
		{
			Ebook currentEbook = iterator.next();
			Element ebook = new Element("ebook");
			
			Element titulo = new Element("titulo");
			titulo.addContent(currentEbook.getTitulo());
			ebook.addContent(titulo);
			
			Element autor = new Element("autor");
			autor.addContent(currentEbook.getAutor());
			ebook.addContent(autor);
			
			Element capaURL = new Element("capaURL");
			capaURL.addContent(currentEbook.getCapaURL());
			ebook.addContent(capaURL);
			
			Element classificacao = new Element("classificacao");
			classificacao.addContent(currentEbook.getClassificacao());
			ebook.addContent(classificacao);
			
			Element formato = new Element("formato");
			formato.addContent(currentEbook.getFormato());
			ebook.addContent(formato);
			
			Element anoEdicao = new Element("anoEdicao");
			anoEdicao.addContent(Integer.toString(currentEbook.getAnoEdicao()));
			ebook.addContent(anoEdicao);
			
			Element editor = new Element("editor");
			editor.addContent(currentEbook.getEditor());
			ebook.addContent(editor);
			
			Element ISBN = new Element("ISBN");
			ISBN.addContent(currentEbook.getISBN());
			ebook.addContent(ISBN);
			
			Element pontosBertrand = new Element("pontosBertrand");
			pontosBertrand.addContent(Float.toString(currentEbook.getPontosBertrand()));
			ebook.addContent(pontosBertrand);
			
			Element preco = new Element("preco");
			preco.addContent(Float.toString(currentEbook.getPreco()));
			ebook.addContent(preco);
			
			listElement.addContent(ebook);
		}
		return document;
	}
	
	public static Boolean writeXML(ArrayList<Ebook> ebooks)
	{
		try {
			Document document = createXML(ebooks);
			XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
			FileWriter writer = new FileWriter("ebooks.xml");
			outputter.output(document, writer);
			writer.close();
			return true;
		}
		catch (IOException e) {
			XML.debug(e.toString());
			return false;
		}
	}

}
