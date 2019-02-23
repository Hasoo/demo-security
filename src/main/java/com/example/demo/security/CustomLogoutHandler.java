package com.example.demo.security;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
import com.example.demo.history.HistoryLogService;
import com.example.demo.history.HistoryLogType;

@Component
public class CustomLogoutHandler implements LogoutSuccessHandler {

  @Autowired
  HistoryLogService historyLogService;

  @Override
  public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws IOException, ServletException {

    historyLogService.saveLoginResult(HistoryLogType.LOGIN_OUT, authentication.getName(),
        Hutil.getOriginalIp(request), "");

    response.sendRedirect("/");
  }

}
