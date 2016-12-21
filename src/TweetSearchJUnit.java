import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

public class TweetSearchJUnit {

	private TweetSearch ts;

	@Before
	public void setUp() throws Exception {
		ts = new TweetSearch();
		ts.restTweet();
	}

	@Test
	public void testTweetListNotNull() {
		assertNotNull(ts.getTweetList());
	}

	@Test
	public void testYesterdayDate(){
		String yest = ts.dateBuilder(1);
		assertEquals("date builder should produce yesterday's date", yest, ts.getYesterday());
	}

	public void testTwoDaysAgoDate(){
		String twoD = ts.dateBuilder(2);
		assertEquals("date builder should produce date from two days ago", twoD, ts.getTwoDaysAgo());
	}

	public void checkTweetListSize(){
		assertTrue("Tweet list should be smaller than tweet cap", ts.getTweetList().size() < ts.getTWEET_CAP());
	}


}

