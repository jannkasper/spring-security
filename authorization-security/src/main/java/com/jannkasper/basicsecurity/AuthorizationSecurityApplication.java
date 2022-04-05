package com.jannkasper.basicsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScans({ @ComponentScan("com.jannkasper.controller"), @ComponentScan("com.jannkasper.config")})
@EnableJpaRepositories("com.jannkasper.repository")
@EntityScan("com.jannkasper.model")
public class AuthorizationSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthorizationSecurityApplication.class, args);
	}

}
