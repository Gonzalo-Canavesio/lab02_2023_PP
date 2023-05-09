import org.json.JSONObject;
import org.json.JSONArray;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SubscriptionParserTest {

    @Test
    public void testParserJson() throws IOException {
        SubscriptionParser parser = new SubscriptionParser();
        String jsonStr = "{\"name\":\"John\",\"age\":30,\"city\":\"New York\"}";
        Files.write(Paths.get("test.json"), jsonStr.getBytes());
        JSONObject expectedJson = new JSONObject(jsonStr);
        JSONObject actualJson = parser.ParserJson("test.json");
        assertEquals(expectedJson.toString(), actualJson.toString());
    }

}
