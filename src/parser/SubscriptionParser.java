package parser;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.json.JSONArray;
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
            // Creo la suscripción single
            SingleSubscription singleSubscription = new SingleSubscription(url, null, urlType);
            // Agrego los parámetros de la suscripción single
            for(int j = 0; j < urlParams.length(); j++){
                singleSubscription.setUrlParams(urlParams.getString(j));
            }
            // Agrego la suscripción single a la suscripción
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
