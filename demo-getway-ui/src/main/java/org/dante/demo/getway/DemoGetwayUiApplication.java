package org.dante.demo.getway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableFeignClients
@EnableZuulProxy
public class DemoGetwayUiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoGetwayUiApplication.class, args);
	}
}
