package com.dominikpiotrowski.eventSearcher.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
class EventSearchConfig {

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}