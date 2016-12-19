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
	public void testAgainstCalculator() {
		double llTest = fp.getFrequencyProfile(17,20);
		System.out.print(llTest);
//		assertEquals("calculates ll like online calculator", 63.27, llTest);
	}

}
