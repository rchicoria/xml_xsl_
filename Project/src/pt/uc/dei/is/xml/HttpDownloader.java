package pt.uc.dei.is.xml;

import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class HttpDownloader {
	
	/**
	 * Devolve o conteúdo HTML de um dado URL
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public static String html(String url) throws IOException
	{
		HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(url);
		HttpResponse response = client.execute(get);
		HttpEntity e = response.getEntity();
		byte[] text = EntityUtils.toByteArray(e);
		return new String(text);
	}
}
