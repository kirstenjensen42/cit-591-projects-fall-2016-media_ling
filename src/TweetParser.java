import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class will gather the tweets and parse it by words
 * @author DannyR
 *
 */
public class TweetParser {
	
	private ArrayList<String> tweets;
	private ArrayList<String> words = new ArrayList<String>();
	private double freq;
	private String testWord;
//	private TweetSearch ts;
	
	public TweetParser(String rest, String tw){
		testWord = tw;
		TweetSearch ts = new TweetSearch();
		ts.restTweet();
		tweets = ts.getTweetList(); //@TODO test for this 
		parse(); //asynch issues?
		
	}
	
	public TweetParser(String tw){
		SampleStream allTweets = new SampleStream();
		allTweets.beginStream(); 
		tweets = allTweets.getTweets();
		testWord = tw;
//		parse(); //asynchronicity problems?
		
		
	}
	//splitting the tweets into individual words
	public void parse(){
		for(String t : tweets){
			t.replaceAll("\\W", " ");
			words.addAll(Arrays.asList(t.split(" ")));
		}
		//clean tweets
		System.out.println(words); //write test for this
		 wordFreq(testWord);
		
	}
	
	public void wordFreq(String target){
		int counter = 0;
		for (String w : words){
			if(w.equals(target)){
				counter++;
			}
			
		}
		 freq = counter/words.size();
//		return freq;
	}
	
	public double getFreq(){
		return freq;
	}
	
}
