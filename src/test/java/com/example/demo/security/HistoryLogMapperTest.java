package com.example.demo.security;

import static org.junit.Assert.assertThat;
import java.util.Date;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.beans.HasProperty;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class HistoryLogMapperTest {

  @Autowired
  private HistoryLogMapper historyLogMapper;

  @Test
  public void historyLogTest() {

    final String type = "LOGIN";
    final String username = "test";
    final String ip = "127.0.0.1";
    final String memo = "tesst memo";
    final Date resDate = new Date();

    historyLogMapper.saveHistoryLog(HistoryLog.builder().type(type).username(username).ip(ip)
        .memo(memo).resDate(resDate).build());

    HistoryLog historyLog = historyLogMapper.findAll();

    assertThat(historyLog, Matchers.hasProperty("type", Matchers.equalTo(type)));
    assertThat(historyLog, Matchers.hasProperty("username", Matchers.equalTo(username)));
    assertThat(historyLog, Matchers.hasProperty("ip", Matchers.equalTo(ip)));
    assertThat(historyLog, Matchers.hasProperty("memo", Matchers.equalTo(memo)));
    assertThat(historyLog, Matchers.hasProperty("resDate", Matchers.equalTo(resDate)));
  }

}
