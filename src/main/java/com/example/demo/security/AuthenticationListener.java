package com.example.demo.security;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AbstractAuthenticationEvent;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;
import com.example.demo.failure.LoginFailureCountProvider;
import com.example.demo.history.HistoryLogService;
import com.example.demo.history.HistoryLogType;

@Component
public class AuthenticationListener implements ApplicationListener<AbstractAuthenticationEvent> {

  @Autowired
  private LoginFailureCountProvider loginFailureCountProvider;

  @Autowired
  private HistoryLogService historyLogService;

  @Autowired
  private HttpServletRequest request;

  @Override
  public void onApplicationEvent(AbstractAuthenticationEvent event) {

    if (event instanceof AuthenticationSuccessEvent) {
      event.getAuthentication().getPrincipal();
      loginFailureCountProvider.success(event.getAuthentication().getName());
      historyLogService.saveLoginResult(HistoryLogType.LOGIN_SUCC,
          event.getAuthentication().getName(), Hutil.getOriginalIp(request), "");
    } else if (event instanceof AuthenticationFailureBadCredentialsEvent) {
      loginFailureCountProvider.failure(event.getAuthentication().getName());
      historyLogService.saveLoginResult(HistoryLogType.LOGIN_FAIL,
          event.getAuthentication().getName(), Hutil.getOriginalIp(request),
          ((AuthenticationFailureBadCredentialsEvent) event).getException().getMessage());
    }

  }

}
