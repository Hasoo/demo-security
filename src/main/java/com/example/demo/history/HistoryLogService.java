package com.example.demo.history;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.dto.HistoryLog;
import com.example.demo.mapper.HistoryLogMapper;

@Service
public class HistoryLogService {

  @Autowired
  HistoryLogMapper historyLogMapper;

  public void saveLoginResult(HistoryLogType historyLogType, String username, String ip,
      String memo) {
    // @formatter:off
    historyLogMapper.saveHistoryLog(
        HistoryLog.builder()
          .type(historyLogType.getType())
          .username(username)
          .ip(ip)
          .memo(memo)
          .resDate(new Date())
        .build()
        );
    // @formatter:on
  }

}
