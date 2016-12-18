import java.util.ArrayList;
import java.util.HashMap;

public class WordCounter {


	public WordCounter() {

	}



	public static ArrayList<String> getWordList(ArrayList<String> corpus) {

		////////// SET TO TRUE TO NOT INCLUDE DIGITS OR TRUE TO INCLUDE DIGITS //////////
		boolean noDigits = true;

		ArrayList<String> words = new ArrayList<String>();

		// for each element of the Book object...
		for ( int b = 0; b < corpus.size(); b++ ) {

			// we're going to refer to that element as "text" for this loop
			// we're also adding a space character at the beginning and ending of the String
			// (this makes things more simple for when we look for regex patterns in a minute)
			String text = " " + corpus.get(b) + " ";

			// now we'll convert that element (it's a String representing a paragraph) to:
			//  - an array String objects, each element now representing a word
			// split at patterns:
			//  - at least one punctuation and at least one space and at least one punctuation
			//  - at least one punctuation and at least one space
			//  - at least one space and at least one punctuation
			String[] arrayOfWords = text.split("\\W+ +\\W+|\\W+ +| +\\W| +|--");

			// for each element in the array of words...
			for ( int i = 0; i < arrayOfWords.length; i++ ) {

				// we'll call this word iWord for this loop
				String iWord = arrayOfWords[i];

				// if the word starts with an underscore remove that underscore
				if ( iWord.startsWith("_") ) {
					iWord = iWord.substring(1);
				}
				// likewise if the word ends with an underscore
				if ( iWord.endsWith("_") ) {
					iWord = iWord.substring(0,iWord.length()-1);
				}

				//////////////// DON'T INCLUDE DIGITS //////////////////
				if ( noDigits ) {
					if (  !iWord.matches("\\d*")  ) {
						// add it to the ArrayList
						words.add(iWord);
					}
				} else {
					words.add(iWord);
				}
			}
		}
		return words;

	}

	/**
	 * This method takes an ArrayList of Strings and turns it into a HashMap with the value being the
	 * count of how many times the String occurs.
	 * @param toBeCounted a HashMap of Strings and the number of times they occurred in the ArrayList
	 */
	public static HashMap<String, Integer> countOccurrences( ArrayList<String> toBeCounted ) {
		HashMap<String, Integer> data = new HashMap<String, Integer>();

		for ( int i = 0; i < toBeCounted.size(); i++ ) {
			toBeCounted.set(i, toBeCounted.get(i).toLowerCase());
			if ( !data.containsKey(toBeCounted.get(i)) ) {
				data.put(toBeCounted.get(i), 1);
			} else {
				data.put(toBeCounted.get(i), data.get(toBeCounted.get(i)) + 1);
			}
		}

		return data;
	}


}
