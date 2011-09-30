/**
 * Projecto 1 de Integração de Sistemas
 * 
 * XML and XML Manipulation
 * 
 * Aplicação para extrair e apresentar a informação sobre os eBooks
 * disponíveis na loja online da Bertrand
 * 
 * @author Gustavo Fernandes
 * @author Ricardo Lopes
 * @author Rui Chicória
 */

package pt.uc.dei.is.xml;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.jdom.transform.XSLTransformException;
import org.jdom.transform.XSLTransformer;

public class XML {
	
	//Número de páginas a seres lidas
	public static int NPAGES = 20;
	
	private static Boolean DEBUG = true;
	//private static final String site = "http://www.bertrand.pt";
	private static final String site = "http://localhost:8080/bertrand";
	//private static final String url = "/home/vertodos/?local=meio&areaid=11709&facetcode=temas&sectionid=130";

	/**
	 * Método principal da aplicação, extrai informação da secção de eBooks da Bertrand
	 * e representa-a sob a forma de XML
	 * @param args
	 */
	public static void main(String[] args) {
		
		ArrayList<Ebook> ebooks = new ArrayList<Ebook>();
		ArrayList<String> wgets = new ArrayList<String>();
		ArrayList<String> categories = new ArrayList<String>();
		
		String url = "/home/index/11708x11709/temas";
		/*Adiciona o url da página que contem as categorias a lista de sites
		para fazer wget*/
		wgets.add(url);
		
		// Vai buscar as varias categorias à pagina
		String html = getWebContents(site + url);
		categories = RegEx.getCategoriesURL(html);
		
		Iterator<String> iterator = categories.iterator();
		int count = 0;
		while (iterator.hasNext() && count < NPAGES)
		{
			url=iterator.next();
			
			// Arranja o HTML das páginas de cada categoria
			html = getWebContents(site+url);
			
			// Adiciona o url da categoria à lista de wgets
			wgets.add(url);
			
			if (html == null)
			{
				System.out.println(	"Ocorreu um erro no carregamento da página.\n" +
									"Verifique o estado da sua ligação à Internet.");
				return;
			}
			// Arranja os links para as páginas de produto
			ArrayList<String> urls = RegEx.getURLs(html);
			wgets.addAll(urls);
			// Arranja o HTML das páginas de produto e cria o respectivo objecto
			createEbooks(urls, ebooks);
			
			count++;
				
		}
		System.out.println(count);
		
		// Cria um XML com base na lista de Ebooks
		debug(XmlCreator.writeXML(ebooks).toString());
		
		iterator = wgets.iterator();
		String temp = null;
		ArrayList<String> changeNames = new ArrayList<String>();
		while(iterator.hasNext())
		{
			temp=iterator.next();
			String temp2 = "";
			try
			{
				temp2 = "mv www.bertrand.pt"+temp+" www.bertrand.pt"+temp.substring(0,temp.indexOf("?"));
				changeNames.add(temp2);
			} catch (StringIndexOutOfBoundsException e){}
			System.out.println("wget "+site+temp+" -x");
		}
		iterator = changeNames.iterator();
		while(iterator.hasNext())
		{
			System.out.println(iterator.next());
		}
		
		SAXBuilder builder = new SAXBuilder();
        Document doc2 = null;
        try{
            Document doc = builder.build("ebooks.xml");
            XSLTransformer transformer = new XSLTransformer("ebooks.xsl");
            doc2 = transformer.transform(doc);
        }
        catch(XSLTransformException e){
        }
        catch(JDOMException e){
        }
        catch(IOException e){}

        // Write the resulting document to file 'dvds.htm'
        XMLOutputter out = new XMLOutputter(Format.getPrettyFormat());
        try {
			out.output(doc2, new FileWriter("ebooks.html"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Faz print da mensagem no ecrã caso a flag DEBUG esteja activada
	 * @param msg
	 */
	public static String debug(String msg)
	{
		if (DEBUG)
			System.out.println(msg);
		return msg;
	}
	
	/**
	 * Devolve o HTML do URL pedido ou null em caso de erro
	 * @param url
	 * @return HTML da página (ou null em caso de erro)
	 */
	private static String getWebContents(String url)
	{
		String html = null;
		try {
			html = HttpDownloader.html(url);
		}
		catch (IOException e) {
			html = null;
		}
		return html;
	}
	
	/**
	 * Devolve um ArrayList com todos os Ebooks, já com os dados completos
	 * @param urls
	 * @return ArrayList de Ebooks com todos os dados
	 */
	private static void createEbooks(ArrayList<String> urls, ArrayList<Ebook> ebooks)
	{
//		ArrayList<Ebook> ebooks = new ArrayList<Ebook>();
		Iterator<String> iterator = urls.iterator();
		
		while (iterator.hasNext())
		{
			Ebook ebook = createEbook(site + iterator.next());
			if (ebook != null)
			{
				ebooks.add(ebook);
			}
		}
	}
	
	/**
	 * Devolve um Ebook com os dados completos, para ser usado em createEbooks
	 * @param url
	 * @return Ebook com todos os dados (ou null em caso de erro)
	 */
	private static Ebook createEbook(String url)
	{
		String content = getWebContents(url);
		if (content != null)
		{
			//debug("Arranjou o HTML da página de produto");
			return new Ebook(content);
		}
		else
		{
			debug("Não foi possível carregar a página.");
			return null;
		}
	}

}
