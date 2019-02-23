package com.example.demo.failure;

import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.mapper.AccountMapper;

public class MybatisFailureCountRepository implements FailureCountRepository {

  @Autowired
  private AccountMapper securityMapper;

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
