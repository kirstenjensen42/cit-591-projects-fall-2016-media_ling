import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Word {

	private StringProperty word = new SimpleStringProperty(this, "word");
	private StringProperty twitterLL;
	private StringProperty guardianLL;
	private String definition = "Definition: ";
	private OECaller oe;
	private Object def;

	/**
	 * @return the def
	 */
	public Object getDef() {
		return def;
	}

	public Word(StringProperty searchWord, String wordString, double ll) {
		word = searchWord ;


		try {
			oe = new OECaller(wordString);
			def = oe.getDef(oe.getJSO());
			definition = "Definition: " + String.valueOf(def);
		} catch (Exception e){
			definition = "No definition available";
		}

	    StringProperty logLikelihood = new SimpleStringProperty();
	    if (ll > 0) {
	    	logLikelihood.setValue(String.valueOf(ll));
	    	guardianLL = logLikelihood;
	    } else if (ll < 0) {
	    	logLikelihood.setValue(String.valueOf(ll*-1));
	    	twitterLL = logLikelihood;
	    } else {
	    	logLikelihood.setValue(String.valueOf(0));
	    	guardianLL = logLikelihood;
	    	twitterLL = logLikelihood;
	    }




	}

    /**
	 * @param twitterLL the twitterLL to set
	 */
	public void setTwitterLL(StringProperty twitterLL) {
		this.twitterLL = twitterLL;
	}

	/**
	 * @param guardianLL the guardianLL to set
	 */
	public void setGuardianLL(StringProperty guardianLL) {
		this.guardianLL = guardianLL;
	}

	public void setWord(String value) { wordProperty().set(value); }
    public String getWord() { return wordProperty().get(); }
    public StringProperty wordProperty() {
        if (word == null) word = new SimpleStringProperty(this, "word");
        return word;
    }

    public void setTwitterLL(String value) { twitterLLProperty().set(value); }
    public String getTwitterLL() { return twitterLLProperty().get(); }
    public StringProperty twitterLLProperty() {
        if (twitterLL == null) twitterLL = new SimpleStringProperty(this, "twitterLL");
        return twitterLL;
    }

    public void setGuardianLL(String value) { guardianLLProperty().set(value); }
    public String getGuardianLL() { return guardianLLProperty().get(); }
    public StringProperty guardianLLProperty() {
        if (guardianLL == null) guardianLL = new SimpleStringProperty(this, "guardianLL");
        return guardianLL;
    }

	public String getDefinition() {
		return definition;
	}

	public void setDefinition(String definition) {
		this.definition = definition;
	}





}
