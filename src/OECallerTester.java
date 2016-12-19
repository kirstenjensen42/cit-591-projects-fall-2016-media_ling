import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;

public class OECallerTester {
	
	@Before
	public void setUp() throws Exception {
		
	}

	//entering a fake word. Should give JSON Exception
	@Test(expected = JSONException.class)
	public void noEntryTest() {
		OECaller oe = new OECaller("slfkjasfhl");

	}

}
