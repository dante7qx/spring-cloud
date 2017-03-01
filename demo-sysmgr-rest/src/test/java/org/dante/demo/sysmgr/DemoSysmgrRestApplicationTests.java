package org.dante.demo.sysmgr;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.netflix.discovery.DiscoveryClient;
import com.netflix.discovery.DiscoveryManager;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoSysmgrRestApplicationTests {
	
	@Autowired
	private DiscoveryClient discoveryClient;

	@Test
	public void shutdown() {
		DiscoveryManager.getInstance().shutdownComponent();
	}
	
	

}
