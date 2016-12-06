import java.util.ArrayList;

import twitter4j.FilterQuery;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterException;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

/*
 * Code template taken from Twitter4j github /examples/PrintSampleStream
 * @author Yusuke Yamamoto
 * 
 */

/**
 * 
 * @author DannyR
 *
 */
public class SampleStream  {
	   	private final int TWEET_CAP = 1000;
		private ArrayList<String> fullTweet = new ArrayList<String>();
		
		public ArrayList<String> getTweets() {
			 return fullTweet;
		}
		
		/**
	     * Main entry of this application.
	     *
	     * @param args arguments doesn't take effect with this example
	     * @throws TwitterException when Twitter service or network is unavailable
	     */
	    public void beginStream (){
	    	final long startTime = System.currentTimeMillis();
	    	TwitterStream twitterStream = Config.createStream();
//	    	TwitterStream twitterStream = new TwitterStreamFactory().getInstance();
//	    	FilterQuery langFilter = new FilterQuery("en");
	        StatusListener listener = new StatusListener() {
	            
	        	
	            @Override
	            public void onStatus(Status status) {
	            	fullTweet.add(status.getText().toLowerCase()); //adding and making all words lowercase;
	            	if (fullTweet.size() > TWEET_CAP){
	            		twitterStream.shutdown();
	            		
	            	}
//	                System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
	                
	            }

	            @Override
	            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
	                //System.out.println("Got a status deletion notice id:" + statusDeletionNotice.getStatusId());
	            }

	            @Override
	            public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
	                System.out.println("Got track limitation notice:" + numberOfLimitedStatuses);
	            }

	            @Override
	            public void onScrubGeo(long userId, long upToStatusId) {
	                System.out.println("Got scrub_geo event userId:" + userId + " upToStatusId:" + upToStatusId);
	            }

	            @Override
	            public void onStallWarning(StallWarning warning) {
	                System.out.println("Got stall warning:" + warning);
	            }

	            @Override
	            public void onException(Exception ex) {
	                ex.printStackTrace();
	            }

	            	
				
	        };
	        twitterStream.addListener(listener);
	        twitterStream.sample("en"); //only gets english sample tweets
//	        final long endTime = System.currentTimeMillis();
//            System.out.println("Total execution time: " + (endTime - startTime)); //somehow not reached
	    }

		
	    
	
}
