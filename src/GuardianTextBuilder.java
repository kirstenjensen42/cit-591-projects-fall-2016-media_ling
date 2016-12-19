
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONArray;
import org.json.JSONTokener;

public class GuardianTextBuilder {

	private ArrayList<String> articleText;
	private ArrayList<String> articleIDs ;
	private ArrayList<String> firstCallList ;
	private GuardianAPICaller apiCaller ;
	private int totalWords = 0;

	public GuardianTextBuilder(String date) {
		apiCaller = new GuardianAPICaller(date) ;
		articleText = new ArrayList<String>();
		articleIDs = new ArrayList<String>();
		firstCallList = new ArrayList<String>();
	}


	public void firstCall() {
		firstCallList.clear();
//		int p = 2 ;
//		int pp = 0 ;
//		String pageCount = "\"pages\":(\\d*)," ;
		String jsonObject = "(\\[[^\\[\\]]*\\])" ;
		Pattern pat1 = Pattern.compile(jsonObject) ;
//		Pattern pat2 = Pattern.compile(pageCount) ;
		String responce ;

		//call first page (first page is 1)
		String url = apiCaller.buildURL(1);
		try {
			responce = "[" + apiCaller.makeCall(url) + "]";
		} catch (Exception e) {
			System.out.println("Error retrieving Guardian text.");
			return;
		}

		// if you want articles for the whole day use this loop to get the other pages
//		// get last page number
//		Matcher match = pat2.matcher(responce) ;
//		if (match.find()) {
//			String lastPage = match.group(1);
//			pp = Integer.valueOf(lastPage);
//		}

		// add formatted first page
		Matcher m1 = pat1.matcher(responce);
		if (m1.find()) {
			responce = m1.group(0);
			firstCallList.add(responce);
		}

		// again, if you want all the pages for one day get this
//		for (; p <= pp; p++) {
//			url = apiCaller.buildURL(p);
//			try {
//				responce = "[" + apiCaller.makeCall(url) + "]";
//				m1 = pat1.matcher(responce);
//
//				if (m1.find()) {
//					responce = m1.group(0);
//					firstCallList.add(responce);
//				}
//
//			} catch (Exception e) {
//				// do nothing
//			}
//
//		}



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
		articleText.clear();
		getArticleIDs();
		String wordCount = ".*\"wordcount\":\"(.*)\"}" ;
		Pattern pat1 = Pattern.compile(wordCount) ;


		for (int k = 0; k < articleIDs.size(); k++) {
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

				// add text to ArrayList
				articleText.add(text);
			} catch (Exception e) {
				// skip
			}
		}

		return articleText;

	}


	/**
	 * @return the totalWords
	 */
	public int getTotalWords() {
		return totalWords;
	}

}

