package parser;

import feed.Article;
import feed.Feed;
import httpRequest.RoughFeed;
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

    public Feed parse() {
        Feed ResultFeed = null;
        try {
            String JsonFeed = this.roughFeed.getFeed();
            JSONObject feedz = new JSONObject(JsonFeed);

            JSONArray items = feedz.getJSONObject("data").getJSONArray("children");
            ResultFeed = new Feed(feedz.getJSONObject("data").getJSONArray("children").getJSONObject(0).getJSONObject("data").getString("subreddit")+ " ("+ roughFeed.getUrlType()+")");
            for (int i = 0; i < items.length(); i++) {
                JSONObject item = items.getJSONObject(i).getJSONObject("data");
                String title = item.getString("title");
                String link = "https://www.reddit.com/"+item.getString("permalink");
                String description = item.getString("selftext");
                Date pubDate = new Date(item.getLong("created_utc") * 1000);
                Article articulo = new Article(title, description, pubDate, link);
                ResultFeed.addArticle(articulo);
            }
        }catch (Exception e) {
            System.out.println("Error al parsear el feed reddit");
            e.printStackTrace();
        }
            return ResultFeed;
        }
    }
