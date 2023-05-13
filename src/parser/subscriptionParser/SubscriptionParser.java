package parser.subscriptionParser;
import org.json.JSONObject;
import org.json.JSONTokener;

import parser.GeneralParser;

import org.json.JSONArray;

import java.io.FileReader;
import subscription.*;
import java.util.ArrayList;
import java.util.List;

/*
 * Esta clase implementa el parser del  archivo de suscripcion (json)
 * Leer https://www.w3docs.com/snippets/java/how-to-parse-json-in-java.html
 * */

public class SubscriptionParser implements GeneralParser<Subscription, String> {

    private FileReader openFileReader(String path) {
        FileReader reader;
        try{
            reader = new FileReader(path);
        } catch (Exception e){
            System.out.println("Error al abrir el archivo de suscripciones");
            e.printStackTrace();
            return null;
        }
        return reader;
    }

    private FileReader closeFileReader(FileReader reader) {
        try {
            reader.close(); // Cierro el archivo
        } catch (Exception e){
            System.out.println("Error al cerrar el archivo de suscripciones");
            e.printStackTrace();
            return null;
        }
        return reader;
    }

    private List<JSONObject> getJsonObjects(FileReader reader) {
        JSONTokener tokener = new JSONTokener(reader);
        JSONArray jsonArray = new JSONArray(tokener);
        List<JSONObject> jsonObjects = new ArrayList<JSONObject>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            jsonObjects.add(jsonObject);
        }
        return jsonObjects;
    }

    private SingleSubscription parseJSON(JSONObject jsonObject) {

        String url = jsonObject.getString("url");
        String urlType = jsonObject.getString("urlType");
        // Transformo el JSONArray de los parámetros de la subscripción en un List<String>
        JSONArray urlParams = jsonObject.getJSONArray("urlParams");
        // Creo la suscripción single
        SingleSubscription singleSubscription = new SingleSubscription(url, null, urlType);
        // Agrego los parámetros de la suscripción single
        for(int j = 0; j < urlParams.length(); j++){
            singleSubscription.setUrlParams(urlParams.getString(j));
        }
        return singleSubscription;
    }


    public Subscription parse(String path) {
        Subscription subscription = new Subscription(path);
 
        FileReader reader = openFileReader(path);

        List<JSONObject> jsonObjects = getJsonObjects(reader);

        // Itero sobre cada una de las suscripciones
        for (JSONObject jsonObject : jsonObjects) {

            // Parseo la suscripción
            SingleSubscription singleSubscription = parseJSON(jsonObject);

            // Agrego la suscripción al objeto subscription
            subscription.addSingleSubscription(singleSubscription);
        }

        reader = closeFileReader(reader);

        return subscription; 
    }
}
