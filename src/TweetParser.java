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
		ArrayList<String> temp = new ArrayList<String>(); //to avoid comod exception - probably not the issue
		for(String t : tweets){
			temp.addAll(Arrays.asList(t.split(" ")));
		}
		words = cleanTweets(temp);
		 wordFreq(testWord);
		
	}
	
	public void wordFreq(String target){
		double counter = 0.0;
		for (String w : words){ 
//			System.out.println(w);
			if(w.equals(target)){ //or contains
				counter++;
			}
			
		}
		 freq = counter/words.size();

	}
	/**
	 * Accessor method for frequency
	 * @return frequency
	 */
	public double getFreq(){
		return freq;
	}
	
	/**
	 * Takes in a ArrayList of Strings removes punctuation and links
	 * @return ArrayList of Strings of 'clean' words
	 */
	public ArrayList<String> cleanTweets(ArrayList<String> dirtyWords){
		ArrayList<String> temp = new ArrayList<String>(dirtyWords); //to avoid comod exception
		String tempWord = new String();
		for(String word : temp){
		 word = word.replaceAll("\\W", " "); //cleaning tweets, still getting hashtags and handles replace/replaceALL
		  if(word.contains("http")){
			  dirtyWords.remove(word);
		  }
		}
		System.out.println(temp);
		return dirtyWords;
		
	}
	
}
