package parser;
import httpRequest.RoughFeed;
import feed.*;

/*
 * Esta clase implementa el parser de feed de tipo reddit (json)
 * pero no es necesario su implemntacion 
 * */

public class RedditParser extends GeneralParser {
    protected RoughFeed roughFeed;

    public RedditParser(RoughFeed roughFeed) {
        this.roughFeed = roughFeed;
    }

    public Feed parse() {
        return null;
    }
}
