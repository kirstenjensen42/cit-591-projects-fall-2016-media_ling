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
	private final String FULL_PAGE = "&page-size=50";
	private final String KEY = "&api-key=2ebaa53e-8de0-4af4-b538-279f54e9d815";

	private final String USER_AGENT = "007";
	
	private ArrayList<String> articleIDs ;
	private ArrayList<String> APIResponces ;

	public GuardianAPICaller() {
		articleIDs = new ArrayList<String>();
		APIResponces = new ArrayList<String>();
		
	}
	
	public String setAPIResponces(String date) throws Exception {
//		articleIDs.clear();
		APIResponces.clear();
		int p = 0;
		
		//first call
		String url = buildURL(date, 1);
		String responce = "[" + makeCall(url) + "]";
		APIResponces.add(responce);
//		String thing1 = "\\[.*\\]";
		String thing1 = "(\\[[^\\[\\]]*\\])";
		Pattern pat2 = Pattern.compile(thing1);
		Matcher m2 = pat2.matcher(responce);
		if (m2.find()) responce = m2.group(0);
		return responce;
		
////		String id = "\"id\":\"([^\"]*)";
////		Pattern pat1 = Pattern.compile(id);
////		Matcher m1 = pat1.matcher(APIResponces.get(0));
////		if (m1.matches()) {
////			for (int k = 0; k < m1.groupCount(); k++) {
////				System.out.println("Group " + k + ": " + m1.group(k) + "\n");
////			}
////		} else {
////			System.out.println("No match found.");
////		}
		
		
//		String pageCount = "\"pages\":(\\d*)," ;
//		Pattern pat = Pattern.compile(pageCount) ;
//		Matcher m = pat.matcher(articleIDs.get(0)) ;
		
//		if (m.find()) {
//			String pp = m.group(1);
//			p = Integer.valueOf(pp);
//		}
		
//		for (int i = 2; i <= p; i++) {
//		url = buildURL(date, i);
//		responce = makeCall(url);
//		APIResponces.add(responce);
//		}
		
	}
	
	public ArrayList<String> getArticleIDs(String date) {
		
		
		
		return articleIDs;
	}
	
	public String buildURL(String date, int page) {
		this.date = date;
		this.page = page;
		String dateCall = "&from-date=" + date + "&to-date=" + date;
		String pageCall = "&page=" + page;
		
//		String url = URL_BASE + dateCall + FULL_PAGE + pageCall + KEY;
		
		String url = URL_BASE + dateCall + KEY;
		
		return url;
	}
	
	private String makeCall(String url) throws Exception {
//		String results ="";
		
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
