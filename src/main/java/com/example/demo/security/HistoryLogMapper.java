package com.example.demo.security;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HistoryLogMapper {
  public void saveHistoryLog(HistoryLog historyLog);

  public HistoryLog findAll();
}
