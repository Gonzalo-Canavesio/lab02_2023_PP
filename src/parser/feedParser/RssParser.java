package parser.feedParser;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.StringReader;
import org.xml.sax.InputSource;
import httpRequest.RoughFeed;
import parser.GeneralParser;
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

    private Document getDocument(String feed) {
        Document doc = null;
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            InputSource inputSource = new InputSource(new StringReader(feed));
            doc = dBuilder.parse(inputSource);
        } catch (Exception e) {
            System.out.println("Error al parsear el feed rss");
            e.printStackTrace();
        }
        return doc;
    }

    private Article parseArticle(Element item) {
        Article articulo = null;
        try {
            String title = item.getElementsByTagName("title").item(0).getTextContent();
            String link = item.getElementsByTagName("link").item(0).getTextContent();
            String description = item.getElementsByTagName("description").item(0).getTextContent();
            String pubDate = item.getElementsByTagName("pubDate").item(0).getTextContent();
            SimpleDateFormat formato = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
            Date fecha = formato.parse(pubDate);
            articulo = new Article(title, description, fecha, link);
        } catch (Exception e) {
            System.out.println("Error al parsear el feed rss");
            e.printStackTrace();
            return null;
        }
        return articulo;
    }

    public Feed parse() {
        Document doc = getDocument(roughFeed.getFeed());
        Feed feed = new Feed(doc.getElementsByTagName("title").item(0).getTextContent()+ " ("+roughFeed.getUrlParam() + " - " + roughFeed.getUrlType()+")");
        NodeList articles = doc.getElementsByTagName("item");
            for (int i = 0; i < articles.getLength(); i++) {
                Node nNode = articles.item(i);
                Article articulo = parseArticle((Element) nNode);
                feed.addArticle(articulo);
            }
        return feed;
    }
}
