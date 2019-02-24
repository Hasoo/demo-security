package com.example.demo.security;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import java.io.IOException;
import java.net.MalformedURLException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.htmlunit.MockMvcWebConnection;
import org.springframework.test.web.servlet.htmlunit.webdriver.WebConnectionHtmlUnitDriver;
import com.example.demo.mapper.AccountMapper;
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.SilentCssErrorHandler;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LoginHtmlTest {
  @Autowired
  private MockMvc mvc;

  @Autowired
  private AccountMapper accountMapper;

  // private WebClient client;

  private WebConnectionHtmlUnitDriver driver;

  @Before
  public void setUp() {
    // client = new WebClient(BrowserVersion.CHROME);
    driver = new WebConnectionHtmlUnitDriver(BrowserVersion.CHROME);
    driver.setWebConnection(new MockMvcWebConnection(mvc, new WebClient(), ""));
    driver.getWebClient().setCssErrorHandler(new SilentCssErrorHandler());

    accountMapper.updateFailureCount("test", 0);
  }

  @After
  public void setDown() {
    // client.close();
    driver.close();

    accountMapper.updateFailureCount("test", 0);
  }

  @Test
  public void titleTest() {
    driver.get("http://localhost:8080");
    assertThat(driver.getTitle(), is("Login"));
  }

  @Test
  public void loginTest()
      throws FailingHttpStatusCodeException, MalformedURLException, IOException {

    for (int i = 0; i < 3; i++) {
      HtmlPage page = driver.getWebClient().getPage("http://localhost:8080");
      HtmlForm form = page.getFormByName("login_form");
      form.getInputByName("username").type("test");
      form.getInputByName("password").type("t");
      form.getInputByName("otpcode").type("123");
      page = form.getButtonByName("log_in").click();
      if (2 == i) {
        assertTrue(page.asText().contains("blocked because failure count is exceeded"));
      } else {
        assertTrue(page.asText().contains("Bad credentials"));
      }
    }
  }

}
