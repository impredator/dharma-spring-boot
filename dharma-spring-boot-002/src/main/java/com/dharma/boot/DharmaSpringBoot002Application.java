package com.dharma.boot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@ServletComponentScan
@SpringBootApplication
public class DharmaSpringBoot002Application {

    @Value("${dharma.path}")
    private String filePath;

    public static void main(String[] args) {
        SpringApplication.run(DharmaSpringBoot002Application.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/api/*").allowedOrigins("http://localhost:8080");
            }

            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                registry
                        .addResourceHandler("/upload/**")
                        .addResourceLocations("file:"+filePath);
            }
        };
    }


}
