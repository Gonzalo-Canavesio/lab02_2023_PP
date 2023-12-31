import parser.feedParser.RedditParser;
import parser.feedParser.RssParser;
import parser.subscriptionParser.JSONParser;
import subscription.*;
import httpRequest.*;
import namedEntity.EntidadNombrada;
import namedEntity.heuristic.Heuristic;
import namedEntity.heuristic.QuickHeuristic;
import feed.*;
import java.util.List;

public class FeedReaderMain {

	private static void printHelp(){
		System.out.println("Please, call this program in correct way: FeedReader [-ne]");
	}

    private static Feed doParse(RoughFeed roughFeed) {
        if(roughFeed.getUrlType().equals("rss")){
            RssParser result = new RssParser();
			return result.parse(roughFeed);
        } else if (roughFeed.getUrlType().equals("reddit")){
            RedditParser result = new RedditParser();
			return result.parse(roughFeed);
        } else {
			System.out.println("Invalid feed type");
			return null;
		}
    }

	public static void main(String[] args) {
		System.out.println("************* FeedReader version 1.0 *************");
		if (args.length == 0) {

			// Leer el archivo de suscription por defecto y parsearlo
			JSONParser subscriptionParser = new JSONParser();
			Subscription subscription = subscriptionParser.parse("config/subscriptions.json");

			// Llamar al httpRequester para obtener el feed del servidor
			httpRequester httpRequester = new httpRequester(subscription);
			List<RoughFeed> roughFeeds = httpRequester.getFeeds();

			// Llamar al Parser especifico para extraer los datos necesarios por la aplicacion, instanciar los feeds e imprimirlos
			for(RoughFeed roughFeed : roughFeeds){
                Feed feed = doParse(roughFeed);
				if(feed != null){
					feed.prettyPrint();
				}
			}

		} else if (args.length == 1 && args[0].equals("-ne")){

			// Leer el archivo de suscription por defecto y parsearlo
			JSONParser subscriptionParser = new JSONParser();
			Subscription subscription = subscriptionParser.parse("config/subscriptions.json");

			// Llamar al httpRequester para obtener el feed del servidor
			httpRequester httpRequester = new httpRequester(subscription);
			List<RoughFeed> roughFeeds = httpRequester.getFeeds();

			// Llamar al Parser especifico para extraer los datos necesarios por la aplicacion, instanciar los feeds
			Heuristic heuristica = new QuickHeuristic(); // Si se quiere cambiar la heuristica, modificar esta linea
			for(RoughFeed roughFeed : roughFeeds){
                Feed feed = doParse(roughFeed);
				// Extraer las entidades nombradas solo de los feeds RSS porque el texto de los feeds de Reddit bugea la heuristica
				if(feed != null && roughFeed.getUrlType().equals("rss")){
					for(Article article : feed.getArticleList()){
						// Llamar a la heuristica y extraer las entidades nombradas
						article.computeNamedEntities(heuristica);
					}
				}
			}
			// Imprimir las entidades nombradas
			Article articuloVacio = new Article(null, null, null, null);
			articuloVacio.prettyPrintNamedEntities();	
			EntidadNombrada prettyPrint = new EntidadNombrada(null, null, 1, null);
			prettyPrint.reduceFrequency();
			prettyPrint.prettyPrintFrecuencias();
		}else {
			printHelp();
		}
	}

}
