package com.jamal.siterank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan("com.jamal.siterank")
public class SiterankApplication {

	public static void main(String[] args) {
		SpringApplication.run(SiterankApplication.class, args);
	}
}
