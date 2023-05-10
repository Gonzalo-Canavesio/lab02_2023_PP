package parser;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.json.JSONArray;
import java.util.List;
import java.util.ArrayList;
import java.io.FileReader;
import subscription.*;

/*
 * Esta clase implementa el parser del  archivo de suscripcion (json)
 * Leer https://www.w3docs.com/snippets/java/how-to-parse-json-in-java.html
 * */

public class SubscriptionParser extends GeneralParser{
    private String path;

    public SubscriptionParser(String path) {
        this.path = path;
    }

    public Subscription parse() {
        Subscription subscription = new Subscription(this.path); // No entiendo para que se usa el path en el constructor
 
        // Manejo del archivo de suscripciones
        FileReader reader;
        try{
            reader = new FileReader(this.path);
        } catch (Exception e){
            System.out.println("Error al abrir el archivo de suscripciones");
            e.printStackTrace();
            return null;
        }

        JSONTokener tokener = new JSONTokener(reader);
        JSONArray jsonArray = new JSONArray(tokener);

        // Itero sobre cada una de las suscripciones
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);

            // Obtengo los atributos de la suscripcion
            String url = jsonObject.getString("url");
            String urlType = jsonObject.getString("urlType");
            // Transformo el JSONArray de los parámetros de la subscripción en un List<String>
            JSONArray urlParams = jsonObject.getJSONArray("urlParams");
            List<String> urlParamsList = new ArrayList<String>();
            for(int j = 0; j < urlParams.length(); j++){
                urlParamsList.add(urlParams.getString(j));
            }

            // Creo la suscripción single y la agrego a la lista de suscripciones
            SingleSubscription singleSubscription = new SingleSubscription(url, urlParamsList, urlType);
            subscription.addSingleSubscription(singleSubscription);
        }

        try {
            reader.close(); // Cierro el archivo
        } catch (Exception e){
            System.out.println("Error al cerrar el archivo de suscripciones");
            e.printStackTrace();
            return null;
        }
        return subscription; // Devuelvo todas las suscripciones
    }
}
