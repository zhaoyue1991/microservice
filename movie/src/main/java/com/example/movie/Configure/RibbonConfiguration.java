package com.example.movie.Configure;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RibbonConfiguration {

  @Bean
  public IRule ribbonRule() {
    return new RandomRule();
  }
}
