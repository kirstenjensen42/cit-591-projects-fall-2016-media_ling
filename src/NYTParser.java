package src;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NYTParser {
	
	private String test = "{\"response\":{\"meta\":{\"hits\":284,\"time\":206,\"offset\":0},\"docs\":"
			+ "[{\"word_count\":null},{\"word_count\":null},{\"word_count\":null},{\"word_count\":null}"
			+ ",{\"word_count\":\"22\"},{\"word_count\":\"620\"},{\"word_count\":\"168\"},{\"word_"
			+ "count\":\"179\"},{\"word_count\":\"608\"},{\"word_count\":\"376\"}]},\"status\":\"OK\""
			+ ",\"copyright\":\"Copyright (c) 2013 The New York Times Company.  All Rights Reserved.\"}";
	private String wordCount ;
	
	public NYTParser() {
		wordCount = "\"word_count\":\"(\\d*)\"" ;	
//		"word_count":"[65]"
		
		
	}
	
	public int getWordCount(String jsonString) {
		int callTotal = 0;
		
		Pattern p = Pattern.compile(wordCount) ;
		Matcher m = p.matcher(jsonString) ;
	
		while (m.find()) {
			System.out.print( m.group(1) + ", ");
			callTotal = callTotal + Integer.valueOf(m.group(1)) ;
		}
		System.out.println("Total: " + callTotal) ;
		return callTotal;

	}

}
