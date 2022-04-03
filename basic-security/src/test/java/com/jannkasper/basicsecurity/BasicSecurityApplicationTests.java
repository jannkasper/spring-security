package com.jannkasper.basicsecurity;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootTest
@ComponentScans({ @ComponentScan("com.jannkasper.basicsecurity.config"), @ComponentScan("com.jannkasper.basicsecurity.controller") })
class BasicSecurityApplicationTests {

	@Test
	void contextLoads() {
	}

}
