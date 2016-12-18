import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

public class TweetSearchJUnit {

	private TweetSearch ts;
	
	@Before
	public void setUp() throws Exception {
		ts = new TweetSearch();
	}

	@Test
	public void testTweetListNotNull() {
		assertNotNull(ts.getTweetList());
	}
	
	@Test
	public void testYesterdayDate(){
		String yest = ts.dateBuilder(-1);
		assertEquals(yest, ts.getYesterday());
	}

}
