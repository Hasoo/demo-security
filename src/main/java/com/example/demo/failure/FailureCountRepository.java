package com.example.demo.failure;

public interface FailureCountRepository {

  public int get(String username);

  public void plusOne(String username);

  public void update(String username, int count);
}
