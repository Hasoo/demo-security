package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.example.demo.failure.FailureCountRepository;
import com.example.demo.failure.LoginFailureCountProvider;
import com.example.demo.failure.MybatisFailureCountRepository;

@Configuration
public class LoginFailureCountConfig {
  @Value("${nbiz.security.max-failure-count}")
  private int maxFailureCount;

  @Autowired
  FailureCountRepository mybatisFailureCountRepository;

  @Bean
  public LoginFailureCountProvider loginFailureCountProvider() {
    // @formatter:off
    return LoginFailureCountProvider.builder()
        .maxCount(maxFailureCount)
//        .failureCountRepository(new MybatisFailureCountRepository())
        .failureCountRepository(mybatisFailureCountRepository)
        .build();
    // @formatter:on
  }

  @Bean
  public FailureCountRepository mybatisFailureCountRepository() {
    return new MybatisFailureCountRepository();
  }
}
