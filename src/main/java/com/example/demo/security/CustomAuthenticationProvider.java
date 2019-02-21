package com.example.demo.security;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class CustomAuthenticationProvider extends DaoAuthenticationProvider {

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {

    // after authentication
    //
    // Authentication afterAuth = super.authenticate(authentication);
    // String otpCode =
    // ((CustomWebAuthenticationDetails) authentication.getDetails()).getOtpCode();
    // return afterAuth;

    // before authentication
    String otpCode = ((CustomWebAuthenticationDetails) authentication.getDetails()).getOtpCode();

    return super.authenticate(authentication);
  }

}
