package pt.uc.dei.is.xml;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegEx {
	
	private static final int titulo = 1;
	private static final int autor = 2;
	private static final int capaURL = 3;
	private static final int classificacao = 4;
	private static final int formato = 5;
	private static final int anoEdicao = 6;
	private static final int editor = 7;
	private static final int ISBN = 8;
	private static final int pontosBertrand = 9;
	private static final int preco = 10;
	
	private static final int sobreLivro = 11;
	private static final int sobreAutor = 12;

	public static ArrayList<String> getURLs(String html)
	{
		ArrayList<String> urls = new ArrayList<String>();
		int flags = Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE;
		
		Pattern pattern = Pattern.compile("<div[ ]+id=\"f1_imagem\"[ ]+>[ ]*<a[ ]+href=\"(/ficha/[^\"]+)\"", flags);
		Matcher matcher = pattern.matcher(html);
		
        while (matcher.find())
        {
        	try {
        		String url = matcher.group(1);
        		urls.add(url);
        	}
        	catch (IllegalStateException e) {
        		XML.debug("IllegalStateException");
        	}
        }
        
		return urls;
	}
	
	private static String getDadosEbook(int campo, String html)
	{
		Pattern pattern;
		switch (campo)
		{
		case titulo:
			pattern = Pattern.compile("class=\"produto_imagem\" title=\"([^\"]+)\" />");
			break;
		case autor:
			pattern = Pattern.compile("<span itemprop=\"author\">([^<]+)");
			break;
		case capaURL:
			pattern = Pattern.compile("<img itemprop=\"image\" src=\"([^\"]+)\" class=\"produto_imagem\"");
			break;
		case classificacao:
			pattern = Pattern.compile("<p id=\"classificacao\">[^\"]+\"[^\"]+\">([^<]+)");
			break;
		case formato:
			pattern = Pattern.compile("\'fich_prod_ebook_formato\'>[^=]*=[^=]*=\"text\">([^<]+)");
			break;
		case anoEdicao:
			pattern = Pattern.compile(">([^<]+)</time>");
			break;
		case editor:
			pattern = Pattern.compile("\"publisher\">([^<]+)");
			break;
		case ISBN:
			pattern = Pattern.compile("\"isbn\">([^<]+)");
			break;
		case pontosBertrand:
			pattern = Pattern.compile("Pontos Leitor Bertrand:</div>[^>]+>([^<]+)");
			break;
		case preco:
			pattern = Pattern.compile("\"fich_prod_preco_com_desconto_texto\">€([^<]+)");
			break;
		default:
			return null;
		}
		Matcher matcher = pattern.matcher(html);
		matcher.find();
		return matcher.group(1).trim();
	}
	
	private static int getDadosIntEbook(int campo, String html)
	{
		try {
			return Integer.parseInt(getDadosEbook(campo, html));
		}
		catch (Exception e) {
			XML.debug(e.toString());
			return -1;
		}
	}
	
	private static Float getDadosFloatEbook(int campo, String html)
	{
		try {
			return Float.parseFloat(getDadosEbook(campo, html).replace(',', '.'));
		}
		catch (Exception e) {
			XML.debug(e.toString());
			return null;
		}
	}
	
	public static String getTitulo(String html)
	{
		return getDadosEbook(titulo, html);
	}
	
	public static String getAutor(String html)
	{
		return getDadosEbook(autor, html);
	}
	
	public static String getCapaURL(String html)
	{
		return getDadosEbook(capaURL, html);
	}
	
	public static String getClassificacao(String html)
	{
		return getDadosEbook(classificacao, html);
	}
	
	public static String getFormato(String html)
	{
		return getDadosEbook(formato, html);
	}
	
	public static int getAnoEdicao(String html)
	{
		return getDadosIntEbook(anoEdicao, html);
	}
	
	public static String getEditor(String html)
	{
		return getDadosEbook(editor, html);
	}
	
	public static String getISBN(String html)
	{
		return getDadosEbook(ISBN, html);
	}
	
	public static Float getPontosBertrand(String html)
	{
		return getDadosFloatEbook(pontosBertrand, html);
	}
	
	public static Float getPreco(String html)
	{
		return getDadosFloatEbook(preco, html);
	}
	
}
