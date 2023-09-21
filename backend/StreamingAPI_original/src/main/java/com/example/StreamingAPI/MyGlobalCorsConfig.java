package com.example.StreamingAPI;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jakarta.servlet.MultipartConfigElement;

@Configuration
public class MyGlobalCorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*");
    }

        @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        
        // Single file max size.
        factory.setMaxFileSize(DataSize.ofMegabytes(200));  // Set file size to 20MB
        
        // Set total data size.
        factory.setMaxRequestSize(DataSize.ofMegabytes(200));  // Set total request size to 20MB
        
        return factory.createMultipartConfig();
    }
}
