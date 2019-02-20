package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

@Service
public class AccountService implements UserDetailsService {

  @Autowired
  AuthMapper authMapper;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Account account = authMapper.findAccountByUsername(username).orElseThrow(
        () -> new UsernameNotFoundException(String.format("Username[%s] not found", username)));

    account.setRoles(authMapper.findRolesByUsername(username));

    return account;
  }

  void saveAccount(Account account) {
    authMapper.saveAccount(account);
  }

  public Account findAccountByUsername(String username) throws UsernameNotFoundException {
    Account account = authMapper.findAccountByUsername(username).orElseThrow(
        () -> new UsernameNotFoundException(String.format("Username[%s] not found", username)));

    return account;
  }
}
