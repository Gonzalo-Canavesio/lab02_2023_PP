package parser;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.StringReader;
import org.xml.sax.InputSource;
import httpRequest.RoughFeed;
import feed.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* Esta clase implementa el parser de feed de tipo rss (xml)
 * https://www.tutorialspoint.com/java_xml/java_dom_parse_document.htm
 * */

public class RssParser extends GeneralParser implements FeedParser{

    protected RoughFeed roughFeed;

    public RssParser(RoughFeed roughFeed) {
        this.roughFeed = roughFeed;
    }

    public Feed parse() {
        Document doc = null;
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            InputSource inputSource = new InputSource(new StringReader(this.roughFeed.getFeed()));
            doc = dBuilder.parse(inputSource);
        } catch (Exception e) {
            System.out.println("Error al parsear el feed rss");
            e.printStackTrace();
        }
        Feed feed = new Feed(doc.getElementsByTagName("title").item(0).getTextContent());
        NodeList nList = doc.getElementsByTagName("item");
            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = (Node) nList.item(i);
                Element eElement = (Element) nNode;
                String title = eElement.getElementsByTagName("title").item(0).getTextContent();
                String link = eElement.getElementsByTagName("link").item(0).getTextContent();
                String description = eElement.getElementsByTagName("description").item(0).getTextContent();
                String pubDate = eElement.getElementsByTagName("pubDate").item(0).getTextContent();
                SimpleDateFormat formato = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
                try {
                    Date fecha = formato.parse(pubDate);
                    Article articulo = new Article(title, description, fecha, link);
                    feed.addArticle(articulo);
                } catch (Exception e) {
                    System.out.println("Error al parsear el feed rss");
                    e.printStackTrace();
                }
            }
        return feed;
    }
}
