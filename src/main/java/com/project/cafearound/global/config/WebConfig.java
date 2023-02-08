package com.project.cafearound.global.config;

import static org.springframework.http.HttpHeaders.LOCATION;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry
        .addMapping("/**")
        .allowedMethods("GET","POST","PUT","DELETE")
        .exposedHeaders(LOCATION)
        .allowedOrigins("http://localhost:3000");
  }
}
