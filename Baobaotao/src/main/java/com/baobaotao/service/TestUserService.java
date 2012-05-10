package com.baobaotao.service;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.baobaotao.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext.xml" })
public class TestUserService {

	@Autowired
	UserService service;

	@Test
	public void hasMatchUser() {
		boolean b1 = service.hasMatchUser("admin", "123456");
		boolean b2 = service.hasMatchUser("admin", "123451");
		assertTrue(b1);
		assertTrue(b2);
	}

	@Test
	public void findUserByUserName() {
		User user = service.findUseByUserName("admin");
		assertTrue(user.getUserName().equalsIgnoreCase("admin"));
	}

	// @Test
	// public void test() {
	// fail("Not yet implemented");
	// }

}
