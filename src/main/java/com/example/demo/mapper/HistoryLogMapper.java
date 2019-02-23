package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.example.demo.dto.HistoryLog;

@Mapper
public interface HistoryLogMapper {
  public void saveHistoryLog(HistoryLog historyLog);

  public HistoryLog findByUsername(String username);
}
