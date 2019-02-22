package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class CustomAuthenticationProvider extends DaoAuthenticationProvider {

  @Autowired
  private LoginFailureCountProvider loginFailureCountProvider;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {

    String otpCode = ((CustomWebAuthenticationDetails) authentication.getDetails()).getOtpCode();
    if (otpCode.isEmpty()) {
      throw new BadCredentialsException("Invalid verfication code");
    }

    // want to reset, failure_count column of account table
    if (loginFailureCountProvider.isExceed(authentication.getName())) {
      throw new BadCredentialsException("blocked because failure count is exceeded");
    }

    return super.authenticate(authentication);
  }

}
