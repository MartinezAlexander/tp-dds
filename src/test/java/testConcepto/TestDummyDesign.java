package testConcepto;
import pruebaConcepto.*;

import org.junit.Assert;
import org.junit.Test;

public class TestDummyDesign {
	
	@Test
	public void testIntegrante4() {
		Assert.assertEquals(DummyDesign.integrante4(),4);
	}
	
	@Test
	public void testIntegrante1() {
		Assert.assertEquals(DummyDesign.integrante1(), 1);
	}

}
