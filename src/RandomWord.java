package src;

public class RandomWord {

	public RandomWord() {

	}

	public static String get() {
		String random ;

		NewYorkTimesAPICaller call = new NewYorkTimesAPICaller() ;

		try {
			random = call.doApiCall("http://randomword.setgetgo.com/get.php");
		} catch (Exception e) {
			random = "random";
		}
		return random ;
	}

}
