import java.util.Scanner;

import javax.security.auth.callback.Callback;

/**
 * This class tests the stream and parser together 
 * @author DannyR
 *
 */
public class ProgramHead {
	

	public static void main(String[] args){
		System.out.println("Hi there, what word would you like to test?");
		Scanner in = new Scanner (System.in);
		String response = in.nextLine();
		TweetParser tp = new TweetParser("rest", response);
		System.out.println(tp.getFreq());
//		if(callback()){ //how to do callbacks??
//			parseAfter(response);
//		}
		
	}
	
}
