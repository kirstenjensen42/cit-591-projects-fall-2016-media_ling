package src;

import java.io.IOException;
import java.util.ArrayList;

public class APICallUnit {
	
	
	private ArrayList<String> allPages;
	private NewYorkTimesAPICaller nyt = new NewYorkTimesAPICaller() ;
	private int totalWords;
	
	
	public APICallUnit() {
		totalWords = 0;
		ArrayList<String> allPages = new ArrayList<String>() ;
	}
	
	/**
	 * Makes API call. If call returns error will return a null, otherwise returns String containing json from call.
	 * @param page
	 * @return
	 */
	public String callAPI(int page) {
		String theString = nyt.getURLDaySearch("20160503", page) ;
		try {
			theString = nyt.doApiCall(theString) ;
		} catch (IOException ioe){
			return null;
		} catch (Exception e) {
			return null;
		}
		return theString ;
	}
	
	/**
	 * Uses CallAPI method. If it gets a null (ie the call returned an Exception) it calls again until the 
	 * json String is obtained.
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public String getAPICall(int page) throws Exception {
		String gotIt = null;
		
		while (gotIt == null ) { 
			gotIt = callAPI(page);
		}
		
		return gotIt ;
	}
	
	
	
	
	

}
