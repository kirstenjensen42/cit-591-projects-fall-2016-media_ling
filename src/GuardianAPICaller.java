package src;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GuardianAPICaller {
	private String date  ;
	private int page ;
	
	private final String URL_BASE = "https://content.guardianapis.com/search?format=json";
	private final String ARTICLE_URL_BASE = "https://content.guardianapis.com/";
	private final String ARTICLE_URL_TAIL = "?format=json&show-fields=wordcount,body" ;
	private final String FULL_PAGE = "&page-size=50";
	private final String KEY = "&api-key=2ebaa53e-8de0-4af4-b538-279f54e9d815";
	private final String USER_AGENT = "007";
	
	String dateCall ;


	public GuardianAPICaller(String date) {
		this.date = date;
		dateCall = "&from-date=" + date + "&to-date=" + date;
	}
	
	
	public String buildURL(int page) {
		this.page = page;
		String pageCall = "&page=" + page;
		
		String url = URL_BASE + dateCall + FULL_PAGE + pageCall + KEY;
				
		return url;
	}
	
	public String buildURL(String articleId) {
		
		String url = ARTICLE_URL_BASE + articleId + ARTICLE_URL_TAIL + KEY;
				
		return url;
	}
	
	
	public String makeCall(String url) throws Exception {
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");
		
		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);


		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream(), Charset.forName("UTF-8")));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		
		return response.toString();
				
	}
	

}
