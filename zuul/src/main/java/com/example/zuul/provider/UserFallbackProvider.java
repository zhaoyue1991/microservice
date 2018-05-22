package com.example.zuul.provider;

import lombok.extern.log4j.Log4j2;

import org.springframework.cloud.netflix.zuul.filters.route.ZuulFallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * zuul回退
 */
@Log4j2
@Component
public class UserFallbackProvider implements ZuulFallbackProvider {

  @Override
  public String getRoute() {
    return "user";
  }

  @Override
  public ClientHttpResponse fallbackResponse() {
    return new ClientHttpResponse() {
      @Override
      public HttpStatus getStatusCode() throws IOException {
        return HttpStatus.OK;
      }

      @Override
      public int getRawStatusCode() throws IOException {
        return this.getStatusCode().value();
      }

      @Override
      public String getStatusText() throws IOException {
        return this.getStatusCode().getReasonPhrase();
      }

      @Override
      public void close() {

      }

      @Override
      public InputStream getBody() throws IOException {
        return new ByteArrayInputStream("用户微服务不可用，请稍后重试!".getBytes("utf-8"));
      }

      @Override
      public HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return headers;
      }
    };
  }
}
