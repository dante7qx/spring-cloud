package org.dante.demo.sysmgr;

import java.util.List;

import org.dante.demo.sysmgr.dao.UserDao;
import org.dante.demo.sysmgr.domain.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserDaoTest extends DemoSysmgrDaoApplicationTests {
	@Autowired
	private UserDao userDao;
	
	@Test
	public void findAll() {
		List<User> users = userDao.findAll();
		System.out.println(users);
		org.junit.Assert.assertNotNull(users);
	}
}
