package com.example.demo.security;

import lombok.Builder;

@Builder
public class LoginFailureCountProvider {

  private int maxCount;

  private FailureCountRepository failureCountRepository;

  public boolean isExceed(String username) {
    if (failureCountRepository.get(username) < maxCount) {
      return true;
    }
    return false;
  }

  public void success(String username) {
    failureCountRepository.update(username, 0);
  }

  public void failure(String username) {
    failureCountRepository.plusOne(username);
  }

}
