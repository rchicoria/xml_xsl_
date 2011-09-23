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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class XML {
	
	private static Boolean DEBUG = true;
	private static final String site = "http://www.bertrand.pt";
	//private static final String url = "/home/vertodos/?local=meio&areaid=11709&facetcode=temas&sectionid=130";

	/**
	 * Método principal da aplicação, extrai informação da secção de eBooks da Bertrand
	 * e representa-a sob a forma de XML
	 * @param args
	 */
	public static void main(String[] args) {

		String url = "/home/vertodos/?local=meio&areaid=11709&facetcode=temas&sectionid=130";
		do 
		{
			// Arranja o HTML das páginas
			String html = getWebContents(site + url);
			if (html == null)
			{
				System.out.println(	"Ocorreu um erro no carregamento da página.\n" +
									"Verifique o estado da sua ligação à Internet.");
				return;
			}
			
			// Arranja os links para as páginas de produto
			ArrayList<String> urls = RegEx.getURLs(html);
			
			// Arranja o HTML das páginas de produto e cria o respectivo objecto
			ArrayList<Ebook> ebooks = createEbooks(urls);
			
			// Cria um XML com base na lista de Ebooks
			debug(XmlCreator.writeXML(ebooks).toString());
			
			url = RegEx.getNextPage(html);
				
		} while (url != null);

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
	private static ArrayList<Ebook> createEbooks(ArrayList<String> urls)
	{
		ArrayList<Ebook> ebooks = new ArrayList<Ebook>();
		Iterator<String> iterator = urls.iterator();
		
		while (iterator.hasNext())
		{
			Ebook ebook = createEbook(site + iterator.next());
			if (ebook != null)
			{
				ebooks.add(ebook);
			}
		}
		return ebooks;
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
