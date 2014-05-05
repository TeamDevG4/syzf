package org.group4.test;

import static org.junit.Assert.assertEquals;

import org.group4.util.User;
import org.junit.Before;
import org.junit.Test;

public class UserTest {
	private static User user = new User();
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testSetBaseInfo() {
		user.setBaseInfo("doubleganger", 40, 1000, 498, 950, 400);
		assertEquals("doubleganger",user.username);
		assertEquals(40,user.rank);
		assertEquals(1000,user.submitted);
		assertEquals(498,user.solved);
		assertEquals(950,user.submissions);
		assertEquals(400,user.accepted);
	}

}
