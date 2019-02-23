package com.example.demo.failure;

import lombok.Builder;

@Builder
public class LoginFailureCountProvider {

  private int maxCount;

  private FailureCountRepository failureCountRepository;

  public boolean isExceed(String username) {
    if (failureCountRepository.get(username) < maxCount) {
      return false;
    }
    return true;
  }

  public void success(String username) {
    failureCountRepository.update(username, 0);
  }

  public void failure(String username) {
    failureCountRepository.plusOne(username);
  }

}
