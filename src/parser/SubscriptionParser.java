package parser;

/*
 * Esta clase implementa el parser del  archivo de suscripcion (json)
 * Leer https://www.w3docs.com/snippets/java/how-to-parse-json-in-java.html
 * */
import org.json.JSONObject;
import org.json.JSONArray;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SubscriptionParser extends GeneralParser{
    public JSONObject ParserJson(String filePath) throws IOException { // Devuelve un JSONObject con el contenido del archivo
        String StingJson = new String(Files.readAllBytes(Paths.get(filePath)));
        JSONObject json = new JSONObject(StingJson);
        return json;
    }
    public JSONArray filterUrlsByType(JSONObject json, String urlType){ // Devuelve un JSONArray con las urls filtradas por tipo
        JSONArray filteredUrls = new JSONArray();
        for (int i = 0 ; i < json.length() ; i++) { //Me fijo en cada suscripcion
            JSONObject subs = json.getJSONObject("subscriptions");
            String urlT = subs.getString("urlType");
            if (urlT.equals(urlType)){//Si es del tipo que busco es el mismo que el que me pasaron
                JSONArray Params = subs.getJSONArray("urlParams");
                String url = subs.getString("url");
                for (int j = 0 ; j < Params.length() ; j++){ // Me fijo en cada parametro de la suscripcion y lo agrego a la url filtrada
                    String param = Params.getString(j);
                    filteredUrls.put(url.replace("%s", param)); // Reemplazo el %s por el parametro
                }
            }
        }
        return  filteredUrls;
    }

    @Override
    public JSONObject responseParser(JSONObject json) {
        JSONArray rssUrls = filterUrlsByType(json, "rss");
        JSONArray redditUrls = filterUrlsByType(json, "reddit");
        JSONObject response = new JSONObject();
        response.put("rss", rssUrls);
        response.put("reddit", redditUrls);
        System.out.println(response.toString());
        return response;
    }
    @Override
    public JSONObject FileParser(String filePath) throws IOException{
        JSONObject json = ParserJson(filePath); // Obtenemos el JSON del path
        return responseParser(json); // Devolvemos la respuesta
    }
}
