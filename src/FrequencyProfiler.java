package src;


/**
 * This class is used to calculate the relative frequency of occurrence of a given word between two separate 
 * corpora. It uses a method of evaluation described in this article: http://ucrel.lancs.ac.uk/acl/W/W00/W00-0901.pdf
 * and also here: http://wordhoard.northwestern.edu/userman/analysis-comparewords.html
 * 
 * The results given by this method are more accurate in terms of wholistic language use when the word 
 * counts of two corpora is approximately equal. However, takes the corpora sizes into account in the equation 
 * it will also offer insight into linguistic differences between two corpora of differing sizes.
 * 
 * The same methods can also be used to compare an extract of a corpus to the corpus as a whole.
 * 
 * @author kirstenjensen42
 *
 */
public class FrequencyProfiler {
	
	// appearances of word in corpus A
	private double observedA;
	// total words in corpus A
	private double corpusA;
	
	// appearances of word in corpus B
	private double observedB;
	// total words in corpus B
	private double corpusB;
	
	private double expectedValueA ;
	private double expectedValueB ;
	
	/**
	 * This is the constructor. It takes in two arguments: the total word count of corpus A and the total 
	 * word count of corpus A.
	 * 
	 * A FrequencyProfiler object should be created for each set of corpora you will compare. Once instanciated
	 * the object will be passed observed counts of a search work through its getFrequencyProfile method.
	 * 
	 * @param corpusA
	 * @param corpusB
	 */
	public FrequencyProfiler(double corpusA, double corpusB) {
		this.corpusA = corpusA;
		this.corpusB = corpusB;
		
		observedA = 0;
		observedB = 0;
		expectedValueA = 0;
		expectedValueB = 0;
	}
	
	/**
	 * This public method is called with 2 arguments--the frequency of occurrence of a particular search 
	 * query in corpus A and in corpus B. It calculates the log-likeliness of the word's appearance in the 
	 * corpus in which it appeared with a greater relative frequency.
	 * 
	 * Log-likeliness will always be positive. However, for the purpose of displaying the results this 
	 * method returns the results as a positive value if the overuse of the word occurs in corpus A 
	 * and a negative value if the overuse is in corpus B.
	 * 
	 * @param observedA the number of times the search query appeared in corpus A
	 * @param observedB the number of times the search query appeared in corpus B
	 * @return the log-likelihood (positive means relatively more in A, negative means more in B)
	 */
	public double getFrequencyProfile(double observedA, double observedB ) {
		double frequencyProfile = 0;
		this.observedA = observedA;
		this.observedB = observedB;
		
		if (observedA == 0) observedA = .1;
		if (observedB == 0) observedB = .1;
		
		expectedValueA = (corpusA)*(observedA+observedB)/(corpusA+corpusB) ;
		expectedValueB = (corpusB)*(observedA+observedB)/(corpusA+corpusB) ;

		double lla = observedA*(Math.log(observedA)-Math.log(expectedValueA)) ;
		double llb = observedB*(Math.log(observedB)-Math.log(expectedValueB)) ;		
		
		double ll = 2*(lla+llb);
						
		frequencyProfile = ll * overuseIndicator();
		return frequencyProfile;
	}
	
	
	/**
	 * This method compares the relative frequency of the occurrence of the search query in 
	 * corpusA vs corpusB and sets an indicator to 1 or -1 based on whether it appears in A 
	 * or B with a higher relative frequency.
	 * 
	 * -1 indicates overuse in corpus B
	 * 1 indicates overuse in corpus A
	 * 
	 * @return
	 */
	private int overuseIndicator() {
		int indicator = -1;
		if (observedA/corpusA > observedB/corpusB) indicator = 1;
		return indicator;
	}
	

}
