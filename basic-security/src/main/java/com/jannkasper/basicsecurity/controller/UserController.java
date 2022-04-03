package com.jannkasper.basicsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  @GetMapping
  public String getMainPage() {
    return "Welcome on main page";
  }

  @GetMapping("/account")
  public String getAccount() {
    return "Here are the account details";
  }

  @GetMapping("/balance")
  public String getBalance() {
    return "Here are the balance details";
  }

  @GetMapping("/cards")
  public String getCards() {
    return "Here are the cards details";
  }

  @GetMapping("/info")
  public String getInfo() {
    return "Here are info details";
  }
}
