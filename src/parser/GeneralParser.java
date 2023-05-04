package parser;

import org.json.JSONObject;

/*Esta clase modela los atributos y metodos comunes a todos los distintos tipos de parser existentes en la aplicacion*/
public abstract class GeneralParser {
        public abstract JSONObject responseParser(JSONObject json);
        public JSONObject UrlParser(JSONObject list, String urlType){
            return null;
        }
        public JSONObject FileParser(String filePath){
            return null;
        }

}
