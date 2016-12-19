public class RandomWord {

	public RandomWord() {

	}

	public static String get() {
		String random ;

		GuardianAPICaller call = new GuardianAPICaller("");

		try {
			random = call.makeCall("http://randomword.setgetgo.com/get.php");
		} catch (Exception e) {
			random = "random";
		}
		return random ;
	}

}
