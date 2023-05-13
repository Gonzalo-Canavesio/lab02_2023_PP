package parser.feedParser;
import feed.Feed;
import parser.GeneralParser;

public interface FeedParser extends GeneralParser<Feed, String> {
    Feed parse(String input);
}
