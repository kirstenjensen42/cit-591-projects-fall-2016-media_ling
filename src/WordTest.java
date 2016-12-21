import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class WordTest {

	@Before
	public void setUp() throws Exception {
	}


	@Test
	public void testNoDefinition() {
		String notWord = "shelsfs";
		StringProperty spNotWord = new SimpleStringProperty();
		spNotWord.setValue(notWord);
		Word word = new Word(spNotWord, notWord, 0);
		Boolean noDef = word.getDefinition().equals("No definition available");
		assertTrue("Should not have definition", noDef);
	}

	@Test
	public void testHasDefinition() {
		String notWord = "test";
		StringProperty spNotWord = new SimpleStringProperty();
		spNotWord.setValue(notWord);
		Word word = new Word(spNotWord, notWord, 0);
		Boolean noDef = word.getDefinition().equals("No definition available");
		assertFalse("Should have definition", noDef);
	}



}
