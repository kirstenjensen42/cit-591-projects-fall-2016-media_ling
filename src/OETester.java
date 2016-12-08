import java.io.FileNotFoundException;

import org.json.JSONObject;

public class OETester {

	public static void main(String[] args) {
		
		try{	
		
			
		OECaller oe = new OECaller("obsequious");
		System.out.println(oe.getJSO());
		//need to get etymologies: or whatever you want:
		JSONObject ety = oe.getJSO();
		//JSONObject next = ety.getJSONArray("results").getJSONObject(0);
		System.out.println(	ety.getJSONArray("results").getJSONObject(0).getJSONArray("lexicalEntries"
				+ "").getJSONObject(0).getJSONArray("entries").getJSONObject(0).getJSONArray("etymologies").get(0));
		}
		
		catch(Exception e){
			System.out.println("Can't get etymology for this word");
		}
		
		
	}

}
