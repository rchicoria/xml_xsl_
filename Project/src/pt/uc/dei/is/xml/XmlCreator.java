package pt.uc.dei.is.xml;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.jdom.*;
import org.jdom.output.*;

public class XmlCreator {
	
	private static Document createXML(ArrayList<Ebook> ebooks)
	{
		Element listElement = new Element("list",Namespace.getNamespace("b","http://bertrand.pt"));
		Document document = new Document(listElement);
		
		HashMap piMap = new HashMap( 2 );
		piMap.put( "type", "text/xsl" );
		piMap.put( "href", "ebooks.xsl" );
		ProcessingInstruction pi = new ProcessingInstruction( "xml-stylesheet", piMap );

		document.getContent().add( 0, pi );
		
		Iterator<Ebook> iterator = ebooks.iterator();
		while (iterator.hasNext())
		{
			Ebook currentEbook = iterator.next();
			Element ebook = new Element("ebook",Namespace.getNamespace("b","http://bertrand.pt"));
			ebook.setAttribute("ISBN", currentEbook.getISBN());
			
			Element titulo = new Element("titulo",Namespace.getNamespace("b","http://bertrand.pt"));
			titulo.addContent(currentEbook.getTitulo());
			ebook.addContent(titulo);
			
			Element autor = new Element("autor",Namespace.getNamespace("b","http://bertrand.pt"));
			autor.addContent(currentEbook.getAutor());
			ebook.addContent(autor);
			
			Element capaURL = new Element("capaURL",Namespace.getNamespace("b","http://bertrand.pt"));
			capaURL.addContent(currentEbook.getCapaURL());
			ebook.addContent(capaURL);
			
			Element formato = new Element("formato",Namespace.getNamespace("b","http://bertrand.pt"));
			formato.addContent(currentEbook.getFormato());
			ebook.addContent(formato);
			
			Element edicao = new Element("edicao",Namespace.getNamespace("b","http://bertrand.pt"));
			
			Element anoEdicao = new Element("anoEdicao",Namespace.getNamespace("b","http://bertrand.pt"));
			anoEdicao.addContent(Integer.toString(currentEbook.getAnoEdicao()));
			edicao.addContent(anoEdicao);
			
			Element editor = new Element("editor",Namespace.getNamespace("b","http://bertrand.pt"));
			editor.addContent(currentEbook.getEditor());
			edicao.addContent(editor);
			
			ebook.addContent(edicao);
			
			Element tema = new Element("tema", Namespace.getNamespace("b","http://bertrand.pt"));
			
			Element categoria = new Element("categoria", Namespace.getNamespace("b","http://bertrand.pt"));
			categoria.addContent(currentEbook.getCategoria());
			tema.addContent(categoria);
			
			Element subcategoria = new Element("subcategoria", Namespace.getNamespace("b","http://bertrand.pt"));
			subcategoria.addContent(currentEbook.getSubcategoria());
			tema.addContent(subcategoria);
			
			ebook.addContent(tema);
			
			Element pontosBertrand = new Element("pontosBertrand",Namespace.getNamespace("b","http://bertrand.pt"));
			pontosBertrand.addContent(Float.toString(currentEbook.getPontosBertrand()));
			ebook.addContent(pontosBertrand);
			
			Element preco = new Element("preco",Namespace.getNamespace("b","http://bertrand.pt"));
			preco.addContent(Float.toString(currentEbook.getPreco()));
			preco.setAttribute("moeda", "€");
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
