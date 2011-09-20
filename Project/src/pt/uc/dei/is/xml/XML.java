package pt.uc.dei.is.xml;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class XML {
	
	private static Boolean DEBUG = true;
	private static final String site = "http://www.bertrand.pt";
	private static final String url = "/home/vertodos/?local=meio&areaid=11709&facetcode=temas&sectionid=130";

	public static void main(String[] args) {
		
		// Arranja o HTML da página principal
		ArrayList<String> html = new ArrayList<String>();
		String mainHtml;
		String content = getWebContents(site + url);
		if (content != null)
		{
			mainHtml = content;
			debug("Arranjou o HTML da página principal");
		}
		else
		{
			System.out.println("Ocorreu um erro no carregamento da página.");
			return;
		}
		
		// Arranja os links para as páginas de produto
		ArrayList<String> urls = RegEx.getURLs(mainHtml);
		Iterator<String> iterator = urls.iterator();
		debug("Arranjou os links para as páginas de produto");
		
		// Arranja o HTML das páginas de produto e cria o respectivo objecto
		ArrayList<Ebook> ebooks = new ArrayList<Ebook>();
		iterator = urls.iterator();
		while (iterator.hasNext())
		{
			content = getWebContents(site + iterator.next());
			if (content != null)
			{
				html.add(content);
				debug("Arranjou o HTML da página de produto");
				ebooks.add(new Ebook(content));
			}
			else
			{
				debug("Não foi possível carregar a página.");
			}
		}
		
		// Cria um XML com base na lista de Ebooks
		// Chicória!

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
	 * @return
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

}
