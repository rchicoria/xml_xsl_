/*
* Small application showing how to retrieve a web page from a site
*
* (c) Filipe Arauqo (filipius@dei.uc.pt), DEI/FCTUC
* v1.0 Sep/2010
*/

import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class HttpClientDemo {
	private static final String url = "http://www.google.com";
	
	public static void main(String args[]) throws IOException
	{
		HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(url);
		HttpResponse response = client.execute(get);
		HttpEntity e = response.getEntity();
		byte[] text = EntityUtils.toByteArray(e);
		System.out.println(new String(text));
	}
}
