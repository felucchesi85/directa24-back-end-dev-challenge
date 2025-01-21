package com.directa24.directa24_back_end_dev_challenge.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;


@Configuration
public class AppConfig {
    
    @Bean
    public RestTemplate restTemplate(){return new RestTemplate();}


}
