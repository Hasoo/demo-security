package com.example.demo.security;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import com.example.demo.failure.FailureCountRepository;
import com.example.demo.failure.LoginFailureCountProvider;

@RunWith(SpringRunner.class)
public class LoginFailureCountTest {

  final private int maxCount = 5;

  private LoginFailureCountProvider loginFailureCountProvider = LoginFailureCountProvider.builder()
      .maxCount(maxCount).failureCountRepository(new FailureCountRepository() {

        private Map<String, Integer> maps = new HashMap<>();

        @Override
        public void update(String username, int count) {
          Integer i = maps.get(username);
          maps.put(username, count);
        }

        @Override
        public void plusOne(String username) {
          Integer i = maps.get(username);
          if (null == i) {
            maps.put(username, 1);
          } else {
            maps.put(username, i + 1);
          }
        }

        @Override
        public int get(String username) {
          return Optional.of(maps.get(username)).orElse(0);
        }

      }).build();


  @Test
  public void exceedTest() {
    String username = "test";

    for (int i = 0; i < maxCount; i++) {
      loginFailureCountProvider.failure(username);
    }

    assertTrue(loginFailureCountProvider.isExceed(username));

    loginFailureCountProvider.success(username);

    assertFalse(loginFailureCountProvider.isExceed(username));

    loginFailureCountProvider.failure(username);

    assertFalse(loginFailureCountProvider.isExceed(username));
  }
}
