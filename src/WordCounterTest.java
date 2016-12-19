import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class WordCounterTest {

	private String testString =  "In a tweet on Saturday, Trump combined two of those three qualities "
			+ "when he accused China of 'stealing' a US navy drone--'ripping' it out of the water, "
			+ "no less--in what he said was an 'unpresidented' act.";
	private ArrayList<String> paragraph ;
	private ArrayList<String> words ;
	private WordCounter wc;
	private HashMap<String, Integer> map;

	@Before
	public void setUp() throws Exception {
		wc = new WordCounter() ;
		paragraph = new ArrayList<String>() ;
		words = new ArrayList<String>() ;
		map = new HashMap<String, Integer>() ;
		paragraph.add(testString);
		words = wc.getWordList(paragraph);
	}

	@Test
	public void testForWord() {
		String word = words.get(0);
		assertTrue("Should contain word", words.contains("In"));
	}

	@Test
	public void testForNoPunctuation() {
		String word = words.get(0);
		assertFalse("Should not contain punctuation", words.contains("'unpresidented'"));
	}

	@Test
	public void testForWordThatHadPunctuation() {
		String word = words.get(0);
		assertTrue("Should have removed punctuation", words.contains("unpresidented"));
	}

	@Test
	public void testHashMap() {
		map = wc.countOccurrences(words) ;
		int count = map.get("in");
		assertEquals("Should count 2 'in' occurrences", 2, count) ;
	}

	@Test
	public void allLowerCase() {
		map = wc.countOccurrences(words) ;
		assertEquals("Should count no 'Trump' occurrences", null, map.get("Trump")) ;
	}


}
