package src;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONTokener;

public class NYTTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ArrayList<String> ids = new ArrayList<String>();
 
		GuardianAPICaller g = new GuardianAPICaller();
		String json = "";
		try {
			json = g.setAPIResponces("2016-12-09");
		} catch (Exception e) {
			System.out.println("\n\nException!\n\n");
		}
		
//		System.out.println(json);
		
		JSONTokener tokens = new JSONTokener(json);
		
		JSONArray array = new JSONArray(tokens);
		
		array.get(0);
		
		
//		System.out.println(ids.get(5));
		

	}

}
