import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Word {

	private StringProperty word = new SimpleStringProperty(this, "word");
	private StringProperty twitterLL;
	private StringProperty guardianLL;
	private String definition = "Definition";


	public Word(StringProperty searchWord) {
		word = searchWord ;


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
