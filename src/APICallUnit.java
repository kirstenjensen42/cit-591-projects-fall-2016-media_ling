package src;

import java.io.IOException;
import java.util.ArrayList;

public class APICallUnit {
	
	
	private ArrayList<String> allPages;
	NewYorkTimesAPICaller nyt = new NewYorkTimesAPICaller() ;
	
	
	public APICallUnit() {
		ArrayList<String> allPages = new ArrayList<String>() ;
	}
	
	
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
	
	public String getAPICall(int page) throws Exception {
		String gotIt = null;
		
		while (gotIt == null ) { 
			gotIt = callAPI(page);
		}
		
		return gotIt ;
	}		

}
