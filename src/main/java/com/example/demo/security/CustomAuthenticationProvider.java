package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import lombok.extern.slf4j.Slf4j;

public class CustomAuthenticationProvider extends DaoAuthenticationProvider {
  @Autowired
  AuthMapper authMapper;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    Account account = authMapper.findAccountByUsername(authentication.getName())
        .orElseThrow(() -> new BadCredentialsException("Invalid username or password"));
    return super.authenticate(authentication);
  }

}
