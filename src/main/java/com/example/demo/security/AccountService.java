package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.demo.dto.Account;
import com.example.demo.mapper.AccountMapper;
import lombok.extern.slf4j.Slf4j;

@Service
public class AccountService implements UserDetailsService {

  @Autowired
  AccountMapper securityMapper;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Account account = securityMapper.findAccountByUsername(username).orElseThrow(
        () -> new UsernameNotFoundException(String.format("Username[%s] not found", username)));

    account.setRoles(securityMapper.findRolesByUsername(username));

    return account;
  }

  public void saveAccount(Account account) {
    securityMapper.saveAccount(account);
  }

  public Account findAccountByUsername(String username) throws UsernameNotFoundException {
    Account account = securityMapper.findAccountByUsername(username).orElseThrow(
        () -> new UsernameNotFoundException(String.format("Username[%s] not found", username)));

    return account;
  }
}
