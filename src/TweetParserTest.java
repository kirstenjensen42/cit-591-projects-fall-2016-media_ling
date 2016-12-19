import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class TweetParserTest {

	private TweetParser tp;
	
	@Before
	public void setUp() throws Exception {
		tp = new TweetParser();
	}

	@Test
	public void tweetsNotEmpty() {
		assertNotNull("Tweet list being passed from Tweet Search should be filled", tp.getTweetList());
	}
	
	@Test
	public void wordSplitting(){
		assertTrue("Splitting the list of tweets by spaces should produce "
				+ "more elements", tp.getWordList().size() > tp.getTweetList().size());
	}
	
	@Test
	public void removedHashTags(){
		ArrayList<String> clean = tp.getTweetList();
		assertTrue("clean tweet list should not contain hashtags", clean.contains("#") == false);
	}
	

}
