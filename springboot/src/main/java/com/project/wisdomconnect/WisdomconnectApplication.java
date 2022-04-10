package com.project.wisdomconnect;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.project.wisdomconnect.mapper")
public class WisdomconnectApplication {

	public static void main(String[] args) {
		SpringApplication.run(WisdomconnectApplication.class, args);
	}

}
