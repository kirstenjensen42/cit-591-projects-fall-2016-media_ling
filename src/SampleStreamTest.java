import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class SampleStreamTest {
	
	private static ArrayList<String> allTweetTest = new ArrayList<String>();
	private static SampleStream streamTest;
	
	@Before
	public void setUP(){
//		TweetParser twpTest = new TweetParser();
		streamTest = new SampleStream();
	}
	
	@Test
	public void test() {
		allTweetTest =  streamTest.getTweets();
		assertNotNull(allTweetTest);
	}

}
