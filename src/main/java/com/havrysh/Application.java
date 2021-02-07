package com.havrysh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableCaching
@EnableSwagger2
@SpringBootApplication
public class Application implements WebMvcConfigurer {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}