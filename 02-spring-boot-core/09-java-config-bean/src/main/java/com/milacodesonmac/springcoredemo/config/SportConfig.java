package com.milacodesonmac.springcoredemo.config;

import com.milacodesonmac.springcoredemo.common.Coach;
import com.milacodesonmac.springcoredemo.common.SwimCoach;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SportConfig {
    // @Bean // --> this way default bean id would be same as the method name --> swimCoach
    @Bean("aquatic") // this way we can give custom bean id to a class
    public Coach swimCoach() {
        return new SwimCoach();
    }
}
