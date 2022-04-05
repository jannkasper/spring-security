package com.jannkasper.filter;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.util.StringUtils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.logging.Logger;

public class RequestValidationBeforeFilter implements Filter {

  private final Logger LOG = Logger.getLogger(AuthoritiesLoggingAfterFilter.class.getName());

  public static final String AUTHENTICATION_SCHEME_BASIC = "Basic";
  private Charset credentialsCharset = StandardCharsets.UTF_8;

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    HttpServletRequest req = (HttpServletRequest) servletRequest;
    HttpServletResponse res = (HttpServletResponse) servletResponse;
    String header = req.getHeader(HttpHeaders.AUTHORIZATION);

    if (header != null) {
      header = header.trim();
      if (StringUtils.startsWithIgnoreCase(header, AUTHENTICATION_SCHEME_BASIC)) {
        byte[] base64token = header.substring(6).getBytes(StandardCharsets.UTF_8);
        byte[] decoded;

        try {
          decoded = Base64.getDecoder().decode(base64token);
          String token = new String(decoded, getCredentialsCharset(req));
          int delim = token.indexOf(":");
          if(delim == -1) {
            throw new BadCredentialsException("Invalid basic authentication token");
          }
          String email = token.substring(0, delim);
          if (email.toLowerCase().contains("test")) {
            res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
          }
          LOG.info("User "+email+" is successfully authenticated");
        } catch (IllegalArgumentException e) {
          throw new BadCredentialsException("Failed to decode basic authentication token");
        }
      }
    }

    filterChain.doFilter(servletRequest, servletResponse);

  }

  protected Charset getCredentialsCharset(HttpServletRequest request) {
    return getCredentialsCharset();
  }

  public Charset getCredentialsCharset() {
    return this.credentialsCharset;
  }
}
