package com.example.demo.security;

public interface FailureCountRepository {

  public int get(String username);

  public void plusOne(String username);

  public void update(String username, int count);
}
