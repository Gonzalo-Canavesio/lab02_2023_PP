import parser.*;
import parser.feedParser.FeedParser;
import parser.feedParser.RedditParser;
import parser.feedParser.RssParser;
import parser.subscriptionParser.SubscriptionParser;
import subscription.*;
import httpRequest.*;
import namedEntity.NamedEntity;
import namedEntity.heuristic.*;
import feed.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

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

			// Leer el archivo de suscription por defecto y parsearlo
			SubscriptionParser subscriptionParser = new SubscriptionParser("config/subscriptions.json");
			Subscription subscription = subscriptionParser.parse();

			// Llamar al httpRequester para obtener el feed del servidor
			httpRequester httpRequester = new httpRequester(subscription);
			List<RoughFeed> roughFeeds = httpRequester.getFeeds();

			Map<String,NamedEntity> namedEntities = new HashMap<String,NamedEntity>();
			Heuristic heuristic = new QuickHeuristic();
			// Llamar al Parser especifico para extraer los datos necesarios por la aplicacion, instanciar los feeds y analizarlos con la heuristica
			for(RoughFeed roughFeed : roughFeeds){
                FeedParser parser = createParser(roughFeed);
				// Si el feed es de tipo reddit, no se analiza ya que genera demasiado ruido
				if(roughFeed.getUrlType().equals("reddit")){
					continue;
				}
                try {
                    Feed feed = parser.parse();
                    for(Article article : feed.getArticleList()){
					String[] words = article.getText().split(" ");
						for(String word : words){
							if(heuristic.isEntity(word)){
								if(namedEntities.containsKey(word)){
									namedEntities.get(word).incFrequency();
								} else {
									NamedEntity namedEntity = new NamedEntity(word,heuristic.getCategory(word),1);
									namedEntities.put(word,namedEntity);
								}
							}
						}
					}

                } catch(Exception e){
                    e.printStackTrace();
                    System.out.print("Invalid feed type");
                }
			}
			// Llamar al prettyPrint de la tabla de entidades nombradas del feed.
			for(NamedEntity namedEntity : namedEntities.values()){
				namedEntity.prettyPrint();
			}

			/*
			Llamar a la heuristica para que compute las entidades nombradas de cada articulos del feed
			LLamar al prettyPrint de la tabla de entidades nombradas del feed.
			 */

		}else {
			printHelp();
		}
	}

}
