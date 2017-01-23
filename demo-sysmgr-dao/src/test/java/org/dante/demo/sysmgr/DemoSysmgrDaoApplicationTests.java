package org.dante.demo.sysmgr;

import java.util.List;

import org.dante.demo.sysmgr.dao.UserDao;
import org.dante.demo.sysmgr.dao.UserSpecs;
import org.dante.demo.sysmgr.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.common.collect.Lists;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoSysmgrDaoApplicationTests {

	@Autowired
	private UserDao userDao;
	
	@Test
	public void queryByAccountAndAuthCode() {
		String account = "dante";
		List<String> authCodes = Lists.newArrayList("sysmgr.user.query", "sysmgr.role.delete");
		User user = userDao.findOne(UserSpecs.queryByAccountAndAuthCode(account, authCodes));
		Assert.assertNotNull(user);
	}

}
