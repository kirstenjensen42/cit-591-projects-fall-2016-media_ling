import java.io.FileNotFoundException;
import java.util.Scanner;

import org.json.JSONObject;
/**
 * This class tests the OE api
 * @author DannyR
 *
 */
public class OETester {

	public static void main(String[] args) {

		try{

		System.out.println("test a word: ");

//		Scanner in = new Scanner(System.in);
//		String word = in.nextLine();

		OECaller oe = new OECaller("blanch");
//		System.out.println(oe.getJSO());
		//need to get etymologies: or whatever you want:
		JSONObject ety = oe.getJSO();
		//JSONObject next = ety.getJSONArray("results").getJSONObject(0);
		//pulls etymologies - just for fun
//		System.out.println(	ety.getJSONArray("results").getJSONObject(0).getJSONArray("lexicalEntries"
//				+ "").getJSONObject(0).getJSONArray("entries").getJSONObject(0).getJSONArray("etymologies").get(0));
//		//Pulls definitions
//		//need to make this into a method
//		System.out.println(	ety.getJSONArray("results").getJSONObject(0).getJSONArray("lexicalEntries"
//				+ "").getJSONObject(0).getJSONArray("entries").getJSONObject(0).getJSONArray("senses"
//						+ "").getJSONObject(0).getJSONArray("definitions").get(0));
//		}

		String work = String.valueOf(oe.getDef(oe.getJSO()));
		System.out.println(work);

		}

		catch(Exception e){
			System.out.println("Can't get etymology for this word");
		}


	}

}