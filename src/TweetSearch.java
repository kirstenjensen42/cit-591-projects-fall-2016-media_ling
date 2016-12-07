import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;


/*
 * Code template taken from Twitter4J example 'search'
 * 
 */

/**
 * This class will gather tweets 
 * 
 * @author DannyR
 *
 */
public class TweetSearch {
	
//	ZonedDateTime since = ZonedDateTime.now();
//	ZonedDateTime until = since; //what is the format for this?
	private final int TWEET_CAP = 100;
	private ArrayList<String> tweetList = new ArrayList<String>();
	
	public void restTweet(){
//		Twitter twitter = new TwitterFactory().getInstance();
//		Config config = new Config();
		Twitter twitter = Config.createTwitterObject();
        try {
            Query query = new Query("e");  //@TODO handling uppercase?
            query.setLang("en");
 //           query.setResultType(Query.RECENT);
            query.since("2016-12-05");
            query.until("2016-12-06"); //can this be more specific?
            query.setCount(1); //the number of pages?
            QueryResult result; 
            do {
                result = twitter.search(query);
                List<Status> tweets = result.getTweets();
                for (Status tweet : tweets) {
                	tweetList.add(tweet.getText().toLowerCase()); //seems to be working, @TODO test here
                	if(tweetList.size() > TWEET_CAP) {
                		break;
                	}
                    System.out.println("@" + tweet.getUser().getScreenName() + " - " + tweet.getText());
                }
                
            } while ((query = result.nextQuery()) != null);
//            System.exit(0);
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to search tweets: " + te.getMessage());
            System.exit(-1);
        }
		
	
	}
	/**
	 * Accessor method for tweetList ArrayList
	 * @return tweetList
	 */
	public ArrayList<String> getTweetList(){
		return tweetList;
	}

}
