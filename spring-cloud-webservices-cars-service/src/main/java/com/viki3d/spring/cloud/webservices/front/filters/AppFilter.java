package com.viki3d.spring.cloud.webservices.front.filters;

import com.viki3d.spring.cloud.webservices.services.AppPortService;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Use the {@code @ServletComponentScan} annotation for Spring Boot to activate this filter.
 */
@WebFilter("/api/v1/cars/*")
public class AppFilter implements Filter {

  @Autowired
  private AppPortService appPortService;

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
      throws IOException, ServletException {
    HttpServletResponse httpServletResponse = (HttpServletResponse) response;
    httpServletResponse.setHeader("PORT", Integer.toString(appPortService.getPort()));
    chain.doFilter(request, response);
  }
  
}