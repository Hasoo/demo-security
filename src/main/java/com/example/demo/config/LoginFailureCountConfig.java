package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.example.demo.security.LoginFailureCountProvider;
import com.example.demo.security.MybatisFailureCountRepository;

@Configuration
public class LoginFailureCountConfig {
  @Value("${nbiz.security.max-failure-count}")
  private int maxFailureCount;

  @Bean
  public LoginFailureCountProvider loginFailureCountProvider() {
    // @formatter:off
    return LoginFailureCountProvider.builder()
        .maxCount(maxFailureCount)
        .failureCountRepository(new MybatisFailureCountRepository())
        .build();
    // @formatter:on
  }

}
