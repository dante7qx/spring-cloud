package org.dante.demo.sysmgr;

import java.util.Arrays;
import java.util.List;

import org.dante.demo.sysmgr.dao.ResourceDao;
import org.dante.demo.sysmgr.domain.Resource;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ResourceDaoTest extends DemoSysmgrDaoApplicationTests {
	@Autowired
	private ResourceDao resourceDao;

	@Test
	public void findAll() {
		List<Resource> resources = resourceDao.findAll();
		System.out.println(resources);
		org.junit.Assert.assertNotNull(resources);
	}
	
	@Test
	public void findByAuthCodes() {
		List<String> authCodes = Arrays.asList("111", "222");
		List<Resource> resources = resourceDao.findByAuthCodes(authCodes);
		org.junit.Assert.assertNotNull(resources);
	}
	
	@Test
	public void findAllParentId() {
		resourceDao.findAllParentId();
	}
}
