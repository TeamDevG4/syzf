package org.group4.test;

import static org.junit.Assert.assertTrue;

import org.group4.util.URLOpener;
import org.junit.Before;
import org.junit.Test;

public class URLOpenerTest {
	private static URLOpener urlOpener = new URLOpener();
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testOpenURL() {
		assertTrue(urlOpener.openURL("acm.hdu.edu.cn"));
		
	}

}
