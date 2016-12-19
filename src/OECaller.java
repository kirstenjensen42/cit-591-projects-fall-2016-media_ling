
//	import android.os.Bundle;
//    import android.support.v7.app.AppCompatActivity;

//add dependencies to your class
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

import org.json.JSONArray;
import org.json.JSONObject;

// see giant bomb

public class OECaller {

	private final String app_id = "0753efd6";
    private final String app_key = "7aa1a3db61a7ac0ee18a473d508fdb70";
	private final String word;
	private JSONObject jso;

	public OECaller(String word) {
		this.word = word;
		this.jso = new JSONObject(callOE());


//		JSONArray results = jso.getJSONArray("results");
//		JSONObject jso2 = jso.getJSONObject("lexicalEntries");
//		System.out.println(jso2.toString());

	}

    private String dictionaryEntries() {
        final String language = "en";

 //       final String filters = "registers=Rare;domains=Art";
        final String word_id = word.toLowerCase(); //word id is case sensitive and lowercase is required
        return "https://od-api.oxforddictionaries.com:443/api/v1/entries/" + language + "/" + word_id;
    }


    private String callOE() {

        try {
            URL url = new URL(dictionaryEntries());
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
            urlConnection.setRequestProperty("Accept","application/json");
            urlConnection.setRequestProperty("app_id",app_id);
            urlConnection.setRequestProperty("app_key",app_key);

            // read the output from the server
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();

            String line = null;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line + "\n");
            }

            return stringBuilder.toString();

        }

        catch(FileNotFoundException f){
//			System.out.println("Could not find entry.");
			return f.toString();
		}

        catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        }
     }

    /**
     * Accessor method for the JSON Object OE word entry
     * @return the entry in JSONObject
     */
    public JSONObject getJSO(){
    	return jso;
    }

    /**
     * Accessor method
     * @param word
     * @return
     */
    public Object getDef(JSONObject word){
    	Object definition = getJSO();
        definition = word.getJSONArray("results").getJSONObject(0).getJSONArray("lexicalEntries"
				+ "").getJSONObject(0).getJSONArray("entries").getJSONObject(0).getJSONArray("senses"
						+ "").getJSONObject(0).getJSONArray("definitions").get(0);
    	return definition;
    }

}