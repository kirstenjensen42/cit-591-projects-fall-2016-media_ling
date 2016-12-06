import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;


/*
 * 
 * Question for Kirsten/Vicky: 
 * Twitter API appears to just return the tweets that contain a term,
 * it doesn't give a random sampling of tweets...
 * -maybe there is a way to do this. 
 * -might have to use a stream 
 * see: https://dev.twitter.com/streaming/reference/get/statuses/sample
 * 
 */

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

	private ArrayList<String> tweetList = new ArrayList<String>();
	
	public void restTweet(String word){
//		Twitter twitter = new TwitterFactory().getInstance();
//		Config config = new Config();
		Twitter twitter = Config.createTwitterObject();
        try {
            Query query = new Query(word);  //@TODO handling uppercase?
            query.setLang("en");
            query.setResultType(Query.RECENT);
            query.setCount(10); //the number of pages?
            QueryResult result; 
            do {
                result = twitter.search(query);
                List<Status> tweets = result.getTweets();
                for (Status tweet : tweets) {
                	tweetList.add(tweet.getText().toLowerCase()); //seems to be working, @TODO test here
                	
 //                   System.out.println("@" + tweet.getUser().getScreenName() + " - " + tweet.getText());
                }
                
            } while ((query = result.nextQuery()) != null);
            System.exit(0);
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
