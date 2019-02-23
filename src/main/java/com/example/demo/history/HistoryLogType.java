package com.example.demo.history;

public enum HistoryLogType {
  /* @formatter:off */
    LOGIN_SUCC("인증성공")
  , LOGIN_FAIL("인증실패")
  , LOGIN_OUT("로그아웃")
  ;
  /* @formatter:on */

  private String type;

  HistoryLogType(String type) {
    this.type = type;
  }

  public String getType() {
    return type;
  }

}
