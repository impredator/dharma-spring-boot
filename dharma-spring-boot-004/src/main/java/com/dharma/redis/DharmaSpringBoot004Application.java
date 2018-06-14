package com.dharma.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@EnableCaching
public class DharmaSpringBoot004Application {

	public static void main(String[] args) {
		SpringApplication.run(DharmaSpringBoot004Application.class, args);
	}

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/api/*").allowedOrigins("http://localhost:8080");
            }
        };
    }
}
