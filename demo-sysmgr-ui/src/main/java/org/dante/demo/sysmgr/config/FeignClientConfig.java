package org.dante.demo.sysmgr.config;

import org.dante.demo.sysmgr.hystrix.UserHystrixFallback;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RibbonClients({ @RibbonClient(name = "micro-demo-sysmgr-rest") })
public class FeignClientConfig {
	@Bean
	public UserHystrixFallback userHystrixFallback() {
		return new UserHystrixFallback();
	}
}
