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

	
	public static void main(String[] args){
//		Twitter twitter = new TwitterFactory().getInstance();
//		Config config = new Config();
		Twitter twitter = Config.createTwitterObject();
        try {
        	System.out.println("What word would you like to search for?\n");
            Scanner in = new Scanner(System.in); //getting a word from the user. This will likely come from another class later
            String word = in.nextLine(); 
            Query query = new Query(word);  //@TODO handling uppercase?
            query.setLang("en");
            query.setResultType(Query.RECENT);
            query.setCount(1); //the number of pages?
            QueryResult result; 
            do {
                result = twitter.search(query);
                List<Status> tweets = result.getTweets();
                for (Status tweet : tweets) {
                    System.out.println("@" + tweet.getUser().getScreenName() + " - " + tweet.getText());
                }
                	
                
            } while ((query = result.nextQuery()) != null);
            System.exit(0);
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to search tweets: " + te.getMessage());
            System.exit(-1);
        }
		
	}
}
