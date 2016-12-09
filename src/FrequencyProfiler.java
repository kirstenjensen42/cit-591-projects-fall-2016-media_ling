package src;


/**
 * This class is used to calculate the relative frequency of occurrence of a given word between two separate 
 * corpora. It uses a method of evaluation described in this article: http://ucrel.lancs.ac.uk/acl/W/W00/W00-0901.pdf
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
	 * This method calculates the expected values of the search word in each corpus.
	 * @param observedA the expectation of the word appearing in corpusA
	 * @param observedB the expectation of the word appearing in corpusB
	 */
	private void getExpectedValues(double observedA, double observedB ) {
		if(observedA == 0) observedA = 0.5;
		if(observedB ==0) observedB = 0.5;
		double freqOfOtherWordsA = corpusA - observedA ;
		double freqOfOtherWordsB = corpusB - observedB ;
		
		expectedValueA = (corpusA*(observedA + observedB))/(corpusA + corpusB);
		expectedValueB = (corpusB*(observedA + observedB))/(corpusA + corpusB);
		
	}
	
	/**
	 * This method calculates the word's log-likelihood in the corpus in which it appears with 
	 * the highest relative frequency.
	 * @param expectedValueA
	 * @param expectedValueB
	 */
	private void calculateLogLikelihood(double expectedValueA, double expectedValueB) {
		//  ll = 2*((a*ln (a/E1)) + (b*ln (b/E2)))
		
		double ll = 2*(Math.log10(corpusA/expectedValueA) + Math.log10(corpusB/expectedValueB)) ;
		
	}


}
