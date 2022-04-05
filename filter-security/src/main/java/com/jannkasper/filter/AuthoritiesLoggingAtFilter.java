package com.jannkasper.filter;


import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

public class AuthoritiesLoggingAtFilter implements Filter {

  private final Logger LOG = Logger.getLogger(AuthoritiesLoggingAfterFilter.class.getName());

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    LOG.info("Authentication Validation is in progress");
    filterChain.doFilter(servletRequest, servletResponse);
  }
}
