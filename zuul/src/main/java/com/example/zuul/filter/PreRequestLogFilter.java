package com.example.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import lombok.extern.log4j.Log4j2;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * zuul拦截
 */
@Log4j2
@Component
public class PreRequestLogFilter extends ZuulFilter {

  @Override
  public String filterType() {
    return "pre";
  }

  @Override
  public int filterOrder() {
    return 1;
  }

  @Override
  public boolean shouldFilter() {
    return true;
  }

  @Override
  public Object run() {
    RequestContext ctx = RequestContext.getCurrentContext();
    HttpServletRequest request = ctx.getRequest();
    log.info(String.format("send %S request to %S", request.getMethod(), request.getRequestURI()));
    return null;
  }
}
