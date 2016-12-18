
import java.util.ArrayList;
import java.util.HashMap;

public class Corpus {

	private ArrayList<String> text ;
	private ArrayList<String> wordList ;
	private HashMap<String, Integer> words = new HashMap<String, Integer>() ;

	public Corpus(ArrayList<String> text) {
		this.text = text ;

		wordList = WordCounter.getWordList(text) ;
		words = WordCounter.countOccurrences(wordList) ;


	}



	/**
	 * @return the words
	 */
	public HashMap<String, Integer> getWords() {
		return words;
	}


	/**
	 * A getter method for the ArrayList of Strings holding the corpus text.
	 * @return the text
	 */
	public ArrayList<String> getText() {
		return text;
	}

	public int getTotalWordCount() {
		return wordList.size() ;
	}

}