package com.example.demo.security;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HistoryLog {
  private String type;
  private String username;
  private String ip;
  private String memo;
  private Date resDate;
}
