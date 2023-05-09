package httpRequest;


/* Esta clase se encarga de realizar efectivamente el pedido de feed al servidor de noticias
 * Leer sobre como hacer una http request en java
 * https://www.baeldung.com/java-http-request
 * */

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;


public class httpRequester {
	
	public String getFeedRss(String urlFeed) throws IOException {
		URL url = new URL(urlFeed);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		if(conn.getResponseCode()==200){
			BufferedReader read = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String feedRssXml;
			StringBuilder content = new StringBuilder();
			while ((feedRssXml = read.readLine()) != null) {
				content.append(feedRssXml);
			}
			read.close();
			return content.toString();
		} else {
			throw new IOException("Failed to retrieve RSS feed. Response code: " + conn.getResponseCode());
		}
	}

	public String getFeedReedit(String urlFeed) {
		String feedReeditJson = null;
		return feedReeditJson;
	}

}
