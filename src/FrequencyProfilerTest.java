import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class FrequencyProfilerTest {
	private FrequencyProfiler fp;

	@Before
	public void setUp() throws Exception {
		fp = new FrequencyProfiler(500,500);

	}

	@Test
	public void givesNegativeNumberForB() {
		double llTest = fp.getFrequencyProfile(17,20);
		assertTrue("assigns negative loglikeliness when overuse in corpus B", llTest<0);
	}

}
