package parser.feedParser;

import feed.Article;
import feed.Feed;
import httpRequest.RoughFeed;
import parser.GeneralParser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Date;

/*
 * Esta clase implementa el parser de feed de tipo reddit (json)
 * pero no es necesario su implemntacion
 * */

public class RedditParser extends GeneralParser implements FeedParser{
    protected RoughFeed roughFeed;

    public RedditParser(RoughFeed roughFeed) {
        this.roughFeed = roughFeed;
    }


    private Article parseArticle(JSONObject item) {
        Article articulo = null;
        try {
            String title = item.getJSONObject("data").getString("title");
            String link = item.getJSONObject("data").getString("url");
            String description = item.getJSONObject("data").getString("selftext");
            Date fecha = new Date(item.getJSONObject("data").getLong("created_utc"));
            articulo = new Article(title, description, fecha, link);
        } catch (Exception e) {
            System.out.println("Error al parsear el feed reddit");
            e.printStackTrace();
            return null;
        }
        return articulo;
    }

    public Feed parse() {
        Feed ResultFeed = null;

        String JsonFeed = this.roughFeed.getFeed();
        JSONObject feedz = new JSONObject(JsonFeed);

        JSONArray items = feedz.getJSONObject("data").getJSONArray("children");

        ResultFeed = new Feed(feedz.getJSONObject("data").getJSONArray("children").getJSONObject(0).getJSONObject("data").getString("subreddit")+ " ("+ roughFeed.getUrlType()+")");
        
        for (int i = 0; i < items.length(); i++) {
            JSONObject item = items.getJSONObject(i);
            Article articulo = parseArticle(item);
            ResultFeed.addArticle(articulo);
        }

            return ResultFeed;
        }
    }
