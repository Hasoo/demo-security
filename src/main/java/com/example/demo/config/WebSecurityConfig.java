package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import com.example.demo.security.CustomAuthenticationProvider;
import com.example.demo.security.CustomAuthenticationSuccessHandler;
import com.example.demo.security.CustomLogoutHandler;
import com.example.demo.security.CustomWebAuthenticationDetailsSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  @Value("${nbiz.security.duplicated-session-block}")
  private boolean isDuplicatedSessionBlock;

  @Autowired
  private UserDetailsService accountService;

  @Autowired
  private CustomWebAuthenticationDetailsSource authenticationDetailsSource;

  @Autowired
  private CustomLogoutHandler customLogoutHandler;

  @Autowired
  private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

  @Autowired
  private SessionRegistry sessionRegistry;

  @Override
  public void configure(HttpSecurity http) throws Exception {
    // @formatter:off
    http
      .authorizeRequests()
        .antMatchers("/").permitAll()
        .antMatchers("/login").permitAll()
        .antMatchers("/register").permitAll()
        .antMatchers("/home/**").hasAuthority("USER")
        .anyRequest().authenticated()
        .and()
      .formLogin()
        .loginPage("/login")
        .failureUrl("/login?error=true")
        //.defaultSuccessUrl("/home", true)
        .usernameParameter("username")
        .passwordParameter("password")
        .successHandler(customAuthenticationSuccessHandler)
        .authenticationDetailsSource(authenticationDetailsSource)
        .and()
      .logout()
        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
        //.logoutSuccessUrl("/")
        .logoutSuccessHandler(customLogoutHandler)
        .and()
      .exceptionHandling()
        .accessDeniedPage("/access-denied")
        .and()
      .csrf()
        .disable()
      .sessionManagement()
        .maximumSessions(1)
        .maxSessionsPreventsLogin(isDuplicatedSessionBlock)
        .expiredUrl("/duplicated-login")
        .sessionRegistry(sessionRegistry)
        ;
    // @formatter:on
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    // @formatter:off
    web
      .ignoring()
        .antMatchers("/resources/**")
        .antMatchers("/static/**")
        .antMatchers("/css/**")
        .antMatchers("/js/**")
        .antMatchers("/images/**")
        ;
    // @formatter:on
  }

  @Bean
  public DaoAuthenticationProvider authenticationProvider() {
    CustomAuthenticationProvider authenticationProvider = new CustomAuthenticationProvider();
    authenticationProvider.setUserDetailsService(accountService);
    authenticationProvider.setPasswordEncoder(passwordEncoder());
    return authenticationProvider;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SessionRegistry sessionRegistry() {
    return new SessionRegistryImpl();
  }
}
