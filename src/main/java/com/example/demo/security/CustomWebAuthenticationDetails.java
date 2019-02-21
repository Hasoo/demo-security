package com.example.demo.security;

import javax.servlet.http.HttpServletRequest;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

public class CustomWebAuthenticationDetails extends WebAuthenticationDetails {
  private static final long serialVersionUID = 1L;

  private String otpCode;

  public CustomWebAuthenticationDetails(HttpServletRequest request) {
    super(request);
    otpCode = request.getParameter("otpcode");
  }

  public String getOtpCode() {
    return otpCode;
  }
}
