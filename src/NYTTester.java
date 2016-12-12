package src;

import java.util.ArrayList;
import java.util.regex.Pattern;

import org.hamcrest.Matcher;
import org.json.JSONArray;
import org.json.JSONTokener;

public class NYTTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		GuardianTextBuilder gBuild = new GuardianTextBuilder("2016-12-09") ;
		
		try {
			gBuild.getArticleIDs();
		} catch (Exception e) {
			e.printStackTrace();
		}
		

		
		
		
//		ArrayList<String> ids = new ArrayList<String>();
// 
//		GuardianAPICaller g = new GuardianAPICaller("2016-12-09");
//		String json = "";
//		try {
//			json = g.makeCall(g.buildURL(1));
//		} catch (Exception e) {
//			System.out.println("\n\nException!\n\n");
//		}
//		
//		System.out.println(g.buildURL("us-news/2016/dec/09/steve-bannon-seinfeld-royalties-peter-mehlman-trump"));

		
//		JSONTokener tokens = new JSONTokener(json);
//		
//		JSONArray array = new JSONArray(tokens);
//		
//		for (int y = 0; y < 10; y++) {
//			String j = array.get(y).toString();
//			String getId = "\"id\":\"([^\"]*)\"" ;
//			Pattern pat1 = Pattern.compile(getId) ;
//			java.util.regex.Matcher match = pat1.matcher(j) ;
//			if (match.find()) j = match.group(1);
//			System.out.println(j);
//		}
		
//		System.out.println(ids.get(5));
		

	}

}
