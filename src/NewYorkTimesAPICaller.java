
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

public class NewYorkTimesAPICaller {



	private String searchTerm = "ship";
	private String beginDate = "20161001";
	private String endDate = "20161031";
	private String searchAllDate = "20160227" ;
	private int page = 0;


	private final String API_BASE = "http://api.nytimes.com/svc/search/v2/articlesearch.json?";
//	private final String MY_KEY = "34da68b19efd46d4b0535917e373b5d0";
	private final String MY_KEY = "b45c562013fb4e80aabd14d0b9d1a32a" ;
	private final String USER_AGENT = "something";
	private final String Q = "q=" + searchTerm;
	private final String FQ = "&fq=source:(%22The%20New%20York%20Times%22)";
//	private final String FQ = "&fq=source:(%22The%20New%20York%20Times%22)%AND%type_of_material:(%22News%22%%22Review%22%%22Op-Ed%22%%22briefing%22)";
	//%AND%%22type_of_material:(%22News%22%%22Review%22%%22Op-Ed%22%%22briefing%22)
	private final String FL = "&fl=word_count";
	private final String BEGIN_DATE = "&begin_date=" + beginDate;
	private final String END_DATE = "&end_date=" + endDate;
	private final String DATE = "&begin_date=" + searchAllDate + "&end_date=" + searchAllDate;
	private String pageOffset = "&page=" + page;
	private final String KEY = "&api-key=" + MY_KEY;

	private String searchQueryURL = API_BASE + Q + FQ + FL + BEGIN_DATE + END_DATE + pageOffset + KEY;
	private String getDaysArticles ;

	public NewYorkTimesAPICaller() {

	}

	public String getURLDaySearch(String date, int page) {
		searchAllDate = date ;
		this.page = page ;
		getDaysArticles = API_BASE + FQ + FL + DATE + "&page=" + page + KEY;

		return getDaysArticles ;
	}

	public String doApiCall(String url) throws Exception {

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
