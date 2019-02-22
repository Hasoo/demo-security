package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;

public class MybatisFailureCountRepository implements FailureCountRepository {

  @Autowired
  private SecurityMapper securityMapper;

  @Override
  public int get(String username) {
    return securityMapper.findFailureCountByUsername(username).orElse(0);
  }

  @Override
  public void plusOne(String username) {
    securityMapper.updateFailureCount(username,
        securityMapper.findFailureCountByUsername(username).orElse(0) + 1);
  }

  @Override
  public void update(String username, int count) {
    securityMapper.updateFailureCount(username, count);
  }
}
