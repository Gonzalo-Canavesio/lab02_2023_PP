import parser.*;
import subscription.*;
import httpRequest.*;
import java.util.List;
import feed.Feed;

public class FeedReaderMain {

	private static void printHelp(){
		System.out.println("Please, call this program in correct way: FeedReader [-ne]");
	}

    private static FeedParser createParser(RoughFeed roughFeed) {
        FeedParser result = null;
        if(roughFeed.getUrlType().equals("rss")){
            result = new RssParser(roughFeed);
        } else if (roughFeed.getUrlType().equals("reddit")){
            result = new RedditParser(roughFeed);
        }
        return result;
    }

	public static void main(String[] args) {
		System.out.println("************* FeedReader version 1.0 *************");
		if (args.length == 0) {

			// Leer el archivo de suscription por defecto y parsearlo
			SubscriptionParser subscriptionParser = new SubscriptionParser("config/subscriptions.json");
			Subscription subscription = subscriptionParser.parse();

			// Llamar al httpRequester para obtener el feed del servidor
			httpRequester httpRequester = new httpRequester(subscription);
			List<RoughFeed> roughFeeds = httpRequester.getFeeds();

			// Llamar al Parser especifico para extraer los datos necesarios por la aplicacion, instanciar los feeds e imprimirlos
			for(RoughFeed roughFeed : roughFeeds){
                FeedParser parser = createParser(roughFeed);
                try {
                    Feed feed = parser.parse();
                    feed.prettyPrint();
                } catch(Exception e){
                    e.printStackTrace();
                    System.out.print("Invalid feed type");
                }
			}

		} else if (args.length == 1){

			/*
			Leer el archivo de suscription por defecto;
			Llamar al httpRequester para obtenr el feed del servidor
			Llamar al Parser especifico para extrar los datos necesarios por la aplicacion
			Llamar al constructor de Feed
			Llamar a la heuristica para que compute las entidades nombradas de cada articulos del feed
			LLamar al prettyPrint de la tabla de entidades nombradas del feed.
			 */

		}else {
			printHelp();
		}
	}

}
