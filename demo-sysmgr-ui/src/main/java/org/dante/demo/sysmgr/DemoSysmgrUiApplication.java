package org.dante.demo.sysmgr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.session.data.redis.RedisFlushMode;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@SpringBootApplication
@EnableFeignClients
@EnableZuulProxy
@EnableRedisHttpSession(redisNamespace = "getway-ui-session", maxInactiveIntervalInSeconds = 360, redisFlushMode = RedisFlushMode.IMMEDIATE)
public class DemoSysmgrUiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoSysmgrUiApplication.class, args);
	}
}
