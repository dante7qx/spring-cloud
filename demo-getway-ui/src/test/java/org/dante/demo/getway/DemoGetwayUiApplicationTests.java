package org.dante.demo.getway;

import java.util.Map;
import java.util.Set;

import org.dante.demo.getway.dto.resp.BaseResp;
import org.dante.demo.getway.dto.resp.UserResp;
import org.dante.demo.getway.service.ResourceService;
import org.dante.demo.getway.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoGetwayUiApplicationTests {
	private static final Logger logger = LoggerFactory.getLogger(DemoGetwayUiApplicationTests.class);
	
	@Autowired
	private UserService userService;
	@Autowired
	private ResourceService resourceService;
	
	@Test
	public void querySingleUser() {
		BaseResp<UserResp> resp = userService.queryByAccount("dante");
		Assert.assertArrayEquals(new Integer[1000], new Integer[resp.getResultCode()]);
		logger.info(resp.getResult().getEmail());
	}
	
	@Test
	public void queryResources() {
		Map<RequestMatcher, Set<ConfigAttribute>> map = resourceService.findResourceMap();
		logger.info("ResourceMap: {}.", map.toString());
	}

}
