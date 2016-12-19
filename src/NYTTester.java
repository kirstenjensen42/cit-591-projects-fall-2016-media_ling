import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.regex.Pattern;

import org.hamcrest.Matcher;
import org.json.JSONArray;
import org.json.JSONTokener;

public class NYTTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String text = "{\"response\":{\"status\":\"ok\",\"userTier\":\"developer\",\"total\":1,\"content\":{\"id\":"
				+ "\"us-news/2016/dec/09/steve-bannon-seinfeld-royalties-peter-mehlman-trump\",\"type\":\"article\""
				+ ",\"sectionId\":\"us-news\",\"sectionName\":\"US news\",\"webPublicationDate\":\"2016-12-09T23:28:"
				+ "05Z\",\"webTitle\":\"Seinfeld writer says 'it's pretty galling' that Steve Bannon still earns "
				+ "royalties\",\"webUrl\":\"https://www.theguardian.com/us-news/2016/dec/09/steve-bannon-seinfeld-"
				+ "royalties-peter-mehlman-trump\",\"apiUrl\":\"https://content.guardianapis.com/us-news/2016/dec/"
				+ "09/steve-bannon-seinfeld-royalties-peter-mehlman-trump\",\"fields\":{\"body\":\"<p>As a writer "
				+ "and producer on <a href=\"https://www.theguardian.com/tv-and-radio/seinfeld\">Seinfeld</a>, Peter "
				+ "Mehlman enshrined ?shrinkage?, ?yada yada? and the ethics of double-dipping into comedy lore."
				+ "<br /></p> <p>He savored the sitcom?s success all the more because it was, famously, ?about "
				+ "nothing? and broke network rules about likeable characters and punchlines.</p> <p>But now the "
				+ "joke is on Mehlman and other liberals who worked on Seinfeld.</p> <p>The show?s billion-dollar "
				+ "revenues enriched and empowered <a href=\"https://www.theguardian.com/us-news/stephen-bannon\">"
				+ "Steve Bannon</a>, an investor-turned political guru who ran the campaign for Donald Trump, "
				+ "another rule-breaker who according to critics knows nothing, and will be his chief White House "
				+ "strategist.</p> <p><a href=\"http://pmehlman.com/\">Mehlman</a> is aghast that Bannon continues "
				+ "to mint a fortune from Seinfeld royalties.</p> <p>The writer told the Guardian that he felt "
				+ "Bannon had proven himself to be a ?raging antisemite?, and the fact that he?d made ?all this "
				+ "money off a show that?s associated with Jewish humor ? that?s pretty galling?.</p> <p>Bannon "
				+ "has rejected claims that he is antisemitic. Breitbart News, which he ran before taking over "
				+ "Trump?s campaign, is strongly pro-Israel.<br tabindex=\"-1\" /></p> <p>But under Bannon?s "
				+ "stewardship one <a href=\"http://www.breitbart.com/2016-presidential-race/2016/05/15/bill-"
				+ "kristol-republican-spoiler-renegade-jew/\">headline</a> branded the conservative commentator "
				+ "Bill Kristol a ?renegade Jew?. Another article called Washington Post columnist Anne Applebaum "
				+ "?<a href=\"http://www.breitbart.com/london/2016/09/27/anne-applebaums-russian-style-"
				+ "disinformation-offensive-msm-vs-anti-globalist-right-will-people/\">a Polish, Jewish, American "
				+ "elitist</a>?. </p> <p>In July, Bannon <a href=\"http://www.motherjones.com/politics/2016/08/"
				+ "stephen-bannon-donald-trump-alt-right-breitbart-news\">boasted</a> of turning the rightwing "
				+ "site <a href=\"http://www.breitbart.com/\">Breitbart News</a> into a ?platform for the alt-right?"
				+ ", a far-right movement in the US. Two weeks after the election, a group of self-described"
				+ " ?alt-right? leaders met at a conference where they <a href=\"http://www.nytimes.com/2016/11/"
				+ "21/us/alt-right-salutes-donald-trump.html\">mimicked Nazi language</a>, spoke angrily about "
				+ "Jewish people and said the US belonged to white people.</p> <p>During a contentious divorce in "
				+ "2007 Bannon?s ex-wife, Mary Louise Piccard, said in a court declaration that he objected to "
				+ "their daughters attending a certain school with many Jewish pupils. ?He said that he doesn?t "
				+ "like the way they raise their kids to be ?whiny brats? and that he didn?t want the girls going "
				+ "to school with Jews,? Piccard wrote. Bannon <a href=\"https://www.theguardian.com/us-news/2016/"
				+ "aug/27/trump-campaign-ceo-stephen-bannon-denies-antisemitic-remarks\">denied</a> he made the "
				+ "remarks, saying through a spokeswoman that he ?proudly? sent the girls to the private school."
				+ "</p> <p>Mehlman believes there is ample evidence. ?If he?s not antisemitic what do you have to "
				+ "do to be considered antisemitic? Shoot Woody Allen??</p> <p>The writer said colleagues in Los "
				+ "Angeles were still absorbing Trump?s victory. ?People are just starting to rouse from an "
				+ "incredible funk. A few sitcom writers in my neigborhood were walking around in a daze for a "
				+ "couple of weeks. It was pretty shocking.?</p> <p>Bannon, a former financier at Goldman Sachs, "
				+ "acquired a share of the royalties from Seinfeld in 1993 as part of the sale of Castle Rock "
				+ "Entertainment to Turner Broadcasting System. The NBC show, which ran for nine seasons from "
				+ "1989 to 1998, subsequently became a cultural and financial phenomenon. Bannon?s wealth smoothed "
				+ "his path from finance to media and politics.</p> <p>?He made a ton of money. It was a smart "
				+ "decision,? said Mehlman, who worked with Jerry Seinfeld and Larry David on the show about four "
				+ "neurotic New Yorkers. ?It doesn?t make him any less miserable as a human being.?</p> <p>Most "
				+ "of the show?s writers and actors have remained silent on Trump, with a few exceptions. During "
				+ "the campaign Julia Louis-Dreyfus, who played Elaine, and Jason Alexander, who played George, "
				+ "<a href=\"http://www.ew.com/article/2016/10/20/seinfeld-julia-louis-dreyfus-jason-alexander-"
				+ "trump-emmys\">mocked his claims that the Emmys were rigged</a>. After Trump won, Alexander "
				+ "tweeted: ?Sorry, world. Pray for us.?</p> <p>Almost two decades after the final episode aired, "
				+ "the show remains a cash cow with a record-breaking series of syndication cycles. The size of "
				+ "Bannon?s stake is unclear but just half a percent ?makes you pretty wealthy?, Mehlman said. "
				+ "</p> <p>Affection for Seinfeld seemed undimmed, said the writer. ?In a strange way it?s a "
				+ "tribute to the strength of the show. It?s not like anyone?s saying let?s boycott Seinfeld or "
				+ "stop watching the reruns. I think if [Osama] bin-Laden had said he was a fan people wouldn?t "
				+ "be upset.?</p> <p>Bannon did not respond to an interview request but family and friends defended "
				+ "him, saying media bias had smeared him. </p> <p>?A lot of things have been twisted and it hasn?t "
				+ "been very pleasant,? said Martin Bannon, the strategist?s father. ?A lot of the stuff coming out "
				+ "? they just want to kill the messenger.?</p> <p>John Sullivan, an LA-based documentary maker, "
				+ "said Bannon had Jewish friends and colleagues: ?The antisemitic thing is ridiculous. I don?t "
				+ "recognise this stuff.?</p> <p>Bannon had no creative role in Seinfeld but Melhman said he had "
				+ "joked to a Seinfeld veteran that he had written the Bubble Boy episode. ?It?s generally accepted "
				+ "that words with Bs and Ks are very funny.? Except, maybe, Bannon.</p> <p><br tabindex=\"-1\""
				+ " /></p>\",\"wordcount\":\"773\"},\"isHosted\":false}}}";


		ZoneId zonedId = ZoneId.of( "America/Montreal" );
		LocalDate today = LocalDate.now( zonedId );
		System.out.println( "today : " + today );

//		text = text.replaceFirst("[^\\<]*<p>", "");
//
//		text = text.replaceAll("\",\"wordcount\".*}", "");
//
//		text = text.replaceAll("\\<[^\\>]*\\>", "");
//
//		text = text.replaceAll("\\b\\?\\b", "'");
//
//		text = text.replaceAll("\\B\\?", "'");



//		System.out.println(text);


//		GuardianTextBuilder gBuild = new GuardianTextBuilder("2016-12-09") ;

//		ArrayList<String> articles = gBuild.callArticleTexts();
//		System.out.println(articles.get(15));
//		System.out.println("\n\n");
//		System.out.println(gBuild.getTotalWords());

//		try {
//			gBuild.getArticleIDs();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}





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

















