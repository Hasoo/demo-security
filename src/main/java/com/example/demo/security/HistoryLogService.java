package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HistoryLogService {

  @Autowired
  HistoryLogMapper historyLogMapper;

}
