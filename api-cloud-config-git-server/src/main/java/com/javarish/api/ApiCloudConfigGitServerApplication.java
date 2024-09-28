package com.javarish.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigServer
public class ApiCloudConfigGitServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiCloudConfigGitServerApplication.class, args);
	}

}
