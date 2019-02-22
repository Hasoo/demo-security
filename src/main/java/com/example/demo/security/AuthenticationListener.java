package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AbstractAuthenticationEvent;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationListener implements ApplicationListener<AbstractAuthenticationEvent> {

  @Autowired
  LoginFailureCountProvider loginFailureCountProvider;

  @Override
  public void onApplicationEvent(AbstractAuthenticationEvent event) {

    if (event instanceof AuthenticationSuccessEvent) {
      event.getAuthentication().getPrincipal();
      loginFailureCountProvider.success(event.getAuthentication().getName());
    } else if (event instanceof AuthenticationFailureBadCredentialsEvent) {
      loginFailureCountProvider.failure(event.getAuthentication().getName());
    }
  }

}
