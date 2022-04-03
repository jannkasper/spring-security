package com.jannkasper.basicsecurity.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    /** Configuration to secure all the requests */
//    http.authorizeRequests()
//        .anyRequest().authenticated()
//        .and()
//        .formLogin()
//        .and()
//        .httpBasic();

    /** Configuration to permit all the requests */
//    http.authorizeRequests()
//        .anyRequest().permitAll()
//        .and()
//        .formLogin()
//        .and()
//        .httpBasic();

    /** Configuration to deny all the requests */
//    http.authorizeRequests()
//        .anyRequest().denyAll()
//        .and()
//        .formLogin()
//        .and()
//        .httpBasic();

    /** Custom configurations as per our requirement */
    http.authorizeRequests()
        .antMatchers("/account").authenticated()
        .antMatchers("/balance").authenticated()
        .antMatchers("/cards").authenticated()
        .antMatchers("/").permitAll()
        .antMatchers("/info").permitAll()
        .and()
        .formLogin()
        .and()
        .httpBasic();

  }
}
