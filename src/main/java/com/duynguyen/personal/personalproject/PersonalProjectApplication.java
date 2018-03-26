package com.duynguyen.personal.personalproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@EnableAutoConfiguration
public class PersonalProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonalProjectApplication.class, args);
	}

	@Bean
	WebMvcConfigurer configurer () {
		return new WebMvcConfigurerAdapter() {

			@Override
			public void addResourceHandlers (ResourceHandlerRegistry registry) {
				registry.addResourceHandler("/dist/**").addResourceLocations("classpath:/static/dist/");
			}
		};
	}

}
