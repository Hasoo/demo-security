package com.example.demo.security;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AccountController {

  @Autowired
  private AccountService accountService;

  @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
  public ModelAndView login() {
    return new ModelAndView("login");
  }

  @RequestMapping(value = "/home", method = RequestMethod.GET)
  public String home() {
    return "home";
  }

  @RequestMapping(value = "/register", method = RequestMethod.GET)
  public ModelAndView register() {
    return new ModelAndView("register", "user", new Account());
  }

  @RequestMapping(value = "/register", method = RequestMethod.POST)
  public ModelAndView createUser(@Valid Account account, BindingResult bindingResult) {
    ModelAndView modelAndView = new ModelAndView();

    Account storedAccount = accountService.findAccountByUsername(account.getUsername());
    if (null != storedAccount) {
      bindingResult.rejectValue("email", "error.user", "existed username");
    }

    if (bindingResult.hasErrors()) {
      modelAndView.setViewName("register");
    } else {
      accountService.saveAccount(account);
      modelAndView.addObject("successMessage", "success");
      modelAndView.addObject("user", new Account());
      modelAndView.setViewName("register");
    }

    return modelAndView;
  }

}
