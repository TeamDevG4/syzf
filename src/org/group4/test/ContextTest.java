package org.group4.test;

import static org.junit.Assert.assertEquals;

import org.group4.util.Context;
import org.junit.Before;
import org.junit.Test;

public class ContextTest {
	private static Context context = new Context();
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testSetUserID() {
		context.setUserID("doubleganger");
		assertEquals("doubleganger",context.getUserID());
	}
}
