package httpRequest;
import java.net.HttpURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URI;
import java.io.IOException;
import subscription.*;
import java.util.List;
import java.util.ArrayList;

/* Esta clase se encarga de realizar efectivamente el pedido de feed al servidor de noticias
 * Leer sobre como hacer una http request en java
 * https://www.baeldung.com/java-http-request
 * */

public class httpRequester {
	private Subscription subscription;

	public httpRequester(Subscription subscription){
		this.subscription = subscription;
	}

	public List<RoughFeed> getFeeds(){
		List<RoughFeed> roughFeeds = new ArrayList<RoughFeed>();
		for(SingleSubscription singleSubscription : this.subscription.getSubscriptionsList()){
			String urlType = singleSubscription.getUrlType();
			String feed = null;
			for(int i = 0; i < singleSubscription.getUrlParamsSize(); i++){
				if(urlType.equals("rss")){
					feed = this.getFeedRss(singleSubscription.getFeedToRequest(i));
				} else if(urlType.equals("reddit")){
					feed = this.getFeedReddit(singleSubscription.getFeedToRequest(i));
				}
				if(feed != null){
					RoughFeed roughFeed = new RoughFeed(urlType, singleSubscription.getUrlParams(i), feed);
					roughFeeds.add(roughFeed);
				}
			}
		}
		return roughFeeds;
	}


	public String getFeedRss(String urlFeed){
		URL url;
		HttpURLConnection conn;
		// Hago el pedido de feed al servidor de noticias
		try{
			url = (new URI(urlFeed)).toURL();
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
		} catch (Exception e){
			System.out.println("Error al hacer el pedido de feed al servidor de noticias");
			e.printStackTrace();
			return null;
		}
		// Chequeo que el pedido haya sido exitoso, y en ese caso devuelvo el contenido del feed
		try{
			if(conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
				BufferedReader read = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				String inputLine;
				StringBuffer content = new StringBuffer();
				while ((inputLine = read.readLine()) != null) {
					content.append(inputLine);
				}
				read.close();
				return content.toString();
			} else {
				// En caso de que el pedido no haya sido exitoso, lanzo una excepcion.
				throw new IOException("Failed to retrieve RSS feed. Response code: " + conn.getResponseCode());
			}
		} catch (Exception e){
			System.out.println("Error al obtener el contenido del feed");
			e.printStackTrace();
			return null;
		}
	}

	public String getFeedReddit(String urlFeed) {
		String feedRedditJson = null;
		return feedRedditJson;
	}

}
