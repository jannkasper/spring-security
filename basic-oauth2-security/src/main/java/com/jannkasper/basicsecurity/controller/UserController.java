package com.jannkasper.basicsecurity.controller;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  @GetMapping
  public String getMainPage() {
    return "Welcome on main page";
  }

  @GetMapping("/")
  public String main(OAuth2AuthenticationToken token) {
    System.out.println(token.getPrincipal());
    return "Welcome on main page";
  }
}
