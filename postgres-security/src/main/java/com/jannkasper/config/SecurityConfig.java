package com.jannkasper.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.util.Collections;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  /** SecurityCustomerService authentication */
  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http.cors()
        .configurationSource(new CorsConfigurationSource() {
          @Override
          public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
            CorsConfiguration config = new CorsConfiguration();
            config.setAllowedOrigins(Collections.singletonList("http://localhost:3000/"));
            config.setAllowedMethods(Collections.singletonList("*"));
            config.setAllowCredentials(true);
            config.setAllowedHeaders(Collections.singletonList("*"));
            config.setMaxAge(3600L);
            return config;
          }
        })
        .and()
        .csrf()
        .ignoringAntMatchers("/contact")
        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
        .and()
        .authorizeRequests()
        .antMatchers("/account").authenticated()
        .antMatchers("/balance").authenticated()
        .antMatchers("/cards").authenticated()
        .antMatchers("/user").authenticated()
        .antMatchers("/info").permitAll()
        .and()
        .httpBasic();
  }

//  /** SecurityCustomerService authentication */
//  @Override
//  protected void configure(HttpSecurity http) throws Exception {
//    http.authorizeRequests()
//        .antMatchers("/account").authenticated()
//        .antMatchers("/balance").authenticated()
//        .antMatchers("/cards").authenticated()
//        .antMatchers("/").permitAll()
//        .antMatchers("/info").permitAll()
//        .and()
//        .formLogin()
//        .and()
//        .httpBasic();
//  }


  /** Configured authentication users */
//  @Override
//  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//    auth.inMemoryAuthentication()
//        .withUser("admin").password("12345").authorities("admin")
//       .and()
//       .withUser("user").password("12345").authorities("read")
//       .and()
//       .passwordEncoder(NoOpPasswordEncoder.getInstance());
//  }

//   @Override protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//     InMemoryUserDetailsManager userDetailsService = new InMemoryUserDetailsManager();
//     UserDetails user = User.withUsername("admin").password("12345").authorities("admin").build();
//     UserDetails user1 = User.withUsername("user").password("12345").authorities("read").build();
//     userDetailsService.createUser(user);
//     userDetailsService.createUser(user1);
//     auth.userDetailsService(userDetailsService);
//   }

  @Bean
  public PasswordEncoder passwordEncoder() {
//    return new StandardPasswordEncoder();
//    return new Pbkdf2PasswordEncoder();
    return new BCryptPasswordEncoder();
//    return NoOpPasswordEncoder.getInstance();
  }
}
