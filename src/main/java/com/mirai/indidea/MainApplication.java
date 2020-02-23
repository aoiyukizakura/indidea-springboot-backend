package com.mirai.indidea;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MainApplication {

	public static void main(String[] args) {
//		System.out.println(DateUtils.getTime(0.5));
		SpringApplication.run(MainApplication.class, args);
	}

}
