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
		
		//Namespace n = Namespace.getNamespace("xsi", "http://www.w3.org/2001/XMLSchema-instance");
		listElement.setNamespace(Namespace.getNamespace("http://bertand.pt"));
		//listElement.addNamespaceDeclaration(n);
		//listElement.setAttribute(new Attribute("schemaLocation", "http://google.com ebooks.xsd", n));
		
		Iterator<Ebook> iterator = ebooks.iterator();
		while (iterator.hasNext())
		{
			Ebook currentEbook = iterator.next();
			Element ebook = new Element("ebook");
			ebook.setNamespace(Namespace.getNamespace("http://bertand.pt"));
			ebook.setAttribute("ISBN", currentEbook.getISBN());
			
			Element titulo = new Element("titulo");
			titulo.addContent(currentEbook.getTitulo());
			titulo.setNamespace(Namespace.getNamespace("http://bertand.pt"));
			ebook.addContent(titulo);
			
			Element autor = new Element("autor");
			autor.addContent(currentEbook.getAutor());
			autor.setNamespace(Namespace.getNamespace("http://bertand.pt"));
			ebook.addContent(autor);
			
			Element capaURL = new Element("capaURL");
			capaURL.addContent(currentEbook.getCapaURL());
			capaURL.setNamespace(Namespace.getNamespace("http://bertand.pt"));
			ebook.addContent(capaURL);
			
			Element classificacao = new Element("classificacao");
			classificacao.addContent(currentEbook.getClassificacao());
			classificacao.setNamespace(Namespace.getNamespace("http://bertand.pt"));
			ebook.addContent(classificacao);
			
			Element formato = new Element("formato");
			formato.addContent(currentEbook.getFormato());
			formato.setNamespace(Namespace.getNamespace("http://bertand.pt"));
			ebook.addContent(formato);
			
			Element edicao = new Element("edicao");
			edicao.setNamespace(Namespace.getNamespace("http://bertand.pt"));
			
			Element anoEdicao = new Element("anoEdicao");
			anoEdicao.addContent(Integer.toString(currentEbook.getAnoEdicao()));
			anoEdicao.setNamespace(Namespace.getNamespace("http://bertand.pt"));
			edicao.addContent(anoEdicao);
			
			Element editor = new Element("editor");
			editor.addContent(currentEbook.getEditor());
			editor.setNamespace(Namespace.getNamespace("http://bertand.pt"));
			edicao.addContent(editor);
			
			ebook.addContent(edicao);
			
			Element pontosBertrand = new Element("pontosBertrand");
			pontosBertrand.addContent(Float.toString(currentEbook.getPontosBertrand()));
			pontosBertrand.setNamespace(Namespace.getNamespace("http://bertand.pt"));
			ebook.addContent(pontosBertrand);
			
			Element preco = new Element("preco");
			preco.addContent(Float.toString(currentEbook.getPreco()));
			preco.setAttribute("moeda", "€");
			preco.setNamespace(Namespace.getNamespace("http://bertand.pt"));
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
