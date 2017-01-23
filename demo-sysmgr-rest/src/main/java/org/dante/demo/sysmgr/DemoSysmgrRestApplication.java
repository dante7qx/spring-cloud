package org.dante.demo.sysmgr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class DemoSysmgrRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoSysmgrRestApplication.class, args);
	}
}
