package src;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONTokener;

public class GuardianTextBuilder {
	
	private ArrayList<String> articleText;
	private ArrayList<String> articleIDs ;
	private ArrayList<String> firstCallList ;
	private ArrayList<String> dirtyArticles ;
	private GuardianAPICaller apiCaller ;
	private int totalWords = 0;
	
	public GuardianTextBuilder(String date) {
		apiCaller = new GuardianAPICaller(date) ;
		articleText = new ArrayList<String>();
		articleIDs = new ArrayList<String>();
		firstCallList = new ArrayList<String>();
		dirtyArticles = new ArrayList<String>();
	}
	
	
	public void firstCall() {
		firstCallList.clear();
		int p = 2 ;
		int pp = 0 ;
		String pageCount = "\"pages\":(\\d*)," ;
		String jsonObject = "(\\[[^\\[\\]]*\\])" ;
		Pattern pat1 = Pattern.compile(jsonObject) ;
		Pattern pat2 = Pattern.compile(pageCount) ;
		String responce ;
		
		//call first page
		String url = apiCaller.buildURL(1);
		try {
			responce = "[" + apiCaller.makeCall(url) + "]";
		} catch (Exception e) {
			System.out.println("Error retrieving Guardian text.");
			return;
		}
		
		// get last page number
		Matcher match = pat2.matcher(responce) ;
		if (match.find()) {
			String lastPage = match.group(1);
			pp = Integer.valueOf(lastPage);
		}
		
		// add formatted first page
		Matcher m1 = pat1.matcher(responce);
		if (m1.find()) {
			responce = m1.group(0);
			firstCallList.add(responce);
		}
		
		// get all the other pages
		for (; p <= pp; p++) {
			url = apiCaller.buildURL(p);
			try {
				responce = "[" + apiCaller.makeCall(url) + "]";
				m1 = pat1.matcher(responce);
				
				if (m1.find()) {
					responce = m1.group(0);
					firstCallList.add(responce);
				}
				
			} catch (Exception e) {
				// do nothing
			}
			
		}	
	}
	
	public void getArticleIDs() {
		firstCall();
		articleIDs.clear();
		String getId = "\"id\":\"([^\"]*)\"" ;
		Pattern pat1 = Pattern.compile(getId) ;
		
		for (int k = 0; k < firstCallList.size(); k++ ) {
			JSONTokener tok = new JSONTokener(firstCallList.get(k));
			
			JSONArray array = new JSONArray(tok);
			
			for (int m = 0; m < array.length(); m++) {
				String j = array.get(m).toString();
				Matcher match = pat1.matcher(j);
				if (match.find()) j = match.group(1);
				articleIDs.add(j);
			}
			
		}
		
		firstCallList.clear();
		
	}
	
	
	public ArrayList<String> callArticleTexts() {
		dirtyArticles.clear();		
		getArticleIDs();
		String wordCount = ".*\"wordcount\":\"(.*)\"}" ;
		Pattern pat1 = Pattern.compile(wordCount) ;

		
		for (int k = 0; k < articleIDs.size(); k++) {
//			for (int k = 0; k < 1; k ++) {
			try {
				String text = apiCaller.makeCall(apiCaller.buildURL(articleIDs.get(k)));

				// get wordcount
				Matcher match = pat1.matcher(text) ;
				if (match.find()) {
					int articleWordCount = Integer.valueOf(match.group(1));
					totalWords = totalWords + articleWordCount;
				}
				
				// clean up article
				text = text.replaceFirst("[^\\<]*<p>", "");
				text = text.replaceAll("\",\"wordcount\".*}", "");
				text = text.replaceAll("\\<[^\\>]*\\>", "");
//				text = text.replaceAll("\\b\\?\\b", "'");
//				text = text.replaceAll("\\B\\?", "'");

				// add text to ArrayList
				dirtyArticles.add(text);
			} catch (Exception e) {
				// skip
			}
		}
		
		return dirtyArticles;
		
	}


	/**
	 * @return the totalWords
	 */
	public int getTotalWords() {
		return totalWords;
	}

}














/*
for (int l = 0; l < 2; l++) {
	String text = dirtyArticles.get(l);
	System.out.println(text);
	text = text.replaceFirst("[^\\<]*<p>", "");
	System.out.println(text);
	text = text.replaceAll("\",\"wordcount\".*}", "");
	text = text.replaceAll("\\<[^\\>]*\\>", "");
	text = text.replaceAll("\\b\\?\\b", "'");
	text = text.replaceAll("\\B\\?", "'");
	articleText.add(text);
}
*/
//System.out.println(articleText.get(0));
//System.out.println("\n");
//System.out.println(articleText.get(2));






//String id = "\"id\":\"([^\"]*)";
//Pattern pat1 = Pattern.compile(id);
//Matcher m1 = pat1.matcher(APIResponces.get(0));
////if (m1.matches()) {
////	for (int k = 0; k < m1.groupCount(); k++) {
////		System.out.println("Group " + k + ": " + m1.group(k) + "\n");
////	}
////} else {
////	System.out.println("No match found.");
////}


//String pageCount = "\"pages\":(\\d*)," ;
//Pattern pat = Pattern.compile(pageCount) ;
//Matcher m = pat.matcher(articleIDs.get(0)) ;

//if (m.find()) {
//	String pp = m.group(1);
//	p = Integer.valueOf(pp);
//}

//for (int i = 2; i <= p; i++) {
//url = buildURL(date, i);
//responce = makeCall(url);
//APIResponces.add(responce);
//}



//for (int k = 0; k < firstCallList.size(); k++ ) {
//
//JSONTokener tok = new JSONTokener(firstCallList.get(k));
//
//JSONArray array = new JSONArray(tok);
//
//int i = 0 ;
//while (array.get(i) != null) {
//	String j = array.get(i).toString();
//	java.util.regex.Matcher match = pat1.matcher(j) ;
//	if (match.find()) j = match.group(1);
//	System.out.println(j);
//}
//}
