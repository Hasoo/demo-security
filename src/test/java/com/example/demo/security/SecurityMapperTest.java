package com.example.demo.security;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import java.util.List;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SecurityMapperTest {

  @Autowired
  private SecurityMapper securityMapper;

  PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

  @Test
  public void findAccountByUsernameTest() {
    String username = "hasoo";
    String password = "test";

    securityMapper.saveAccount(Account.builder().username(username)
        .password(passwordEncoder.encode(password)).groupname("TEST").fee(11).enabled(true)
        .credentialsNonExpired(true).accountNonExpired(true).accountNonLocked(true).build());

    Account account = securityMapper.findAccountByUsername(username)
        .orElseThrow(() -> new RuntimeException("no exist account"));
    assertEquals(username, account.getUsername());
    assertThat(passwordEncoder.matches(password, account.getPassword()));
  }

  @SuppressWarnings("unchecked")
  @Test
  public void findAccountRolesTest() {
    String username = "hasoo";
    String password = "test";
    String role1 = "USER1";
    String role2 = "USER2";

    securityMapper.saveAccount(Account.builder().username(username)
        .password(passwordEncoder.encode(password)).groupname("TEST").fee(11).enabled(true)
        .credentialsNonExpired(true).accountNonExpired(true).accountNonLocked(true).build());

    securityMapper.saveAccountRoles(username, role1);
    securityMapper.saveAccountRoles(username, role2);

    List<AccountRoles> accountRoles = securityMapper.findAccountRolesByUsername(username);

    assertThat(accountRoles,
        Matchers.containsInAnyOrder(Matchers.hasProperty("role", Matchers.is(role1)),
            Matchers.hasProperty("role", Matchers.is(role2))));
  }

  @Test
  public void findRolesTest() {
    String username = "hasoo";
    String password = "test";
    String role1 = "USER1";
    String role2 = "USER2";

    securityMapper.saveAccount(Account.builder().username(username)
        .password(passwordEncoder.encode(password)).groupname("TEST").fee(11).enabled(true)
        .credentialsNonExpired(true).accountNonExpired(true).accountNonLocked(true).build());

    securityMapper.saveAccountRoles(username, role1);
    securityMapper.saveAccountRoles(username, role2);

    List<String> roles = securityMapper.findRolesByUsername(username);

    assertTrue(roles.contains(role1));
    assertTrue(roles.contains(role2));
  }

  @Test
  public void deleteAccountRolesTest() {
    String username = "hasoo";
    String password = "testpwd";
    String role = "USER";

    securityMapper.saveAccount(Account.builder().username(username)
        .password(passwordEncoder.encode(password)).groupname("TEST").fee(11).enabled(true)
        .credentialsNonExpired(true).accountNonExpired(true).accountNonLocked(true).build());

    securityMapper.saveAccountRoles(username, role);

    securityMapper.deleteAccountRoles(username, role);

    List<AccountRoles> accountRoles = securityMapper.findAccountRolesByUsername(username);

    assertThat(null == accountRoles);
  }

  @Test
  public void failureCountTest() {
    String username = "hasoo";
    String password = "testpwd";

    securityMapper.saveAccount(Account.builder().username(username)
        .password(passwordEncoder.encode(password)).groupname("TEST").fee(11).enabled(true)
        .credentialsNonExpired(true).accountNonExpired(true).accountNonLocked(true).build());

    int count = 1;
    securityMapper.updateFailureCount(username, count++);
    securityMapper.updateFailureCount(username, count++);
    securityMapper.updateFailureCount(username, count);

    assertEquals(count, (securityMapper.findFailureCountByUsername(username).orElse(0)).intValue());

    securityMapper.updateFailureCount(username, 0);

    for (int i = 1; i < 3;) {
      securityMapper.updateFailureCountPlusOne(username);
      assertEquals(i, (securityMapper.findFailureCountByUsername(username).orElse(0)).intValue());
      i++;
    }
  }

}
