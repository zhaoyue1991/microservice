package com.example.movie.controller;

import com.example.movie.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class UserController {

  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private DiscoveryClient discoveryClient;

  @Value("${user.serviceUrl}")
  private String userServiceUrl;

  @GetMapping("/{id}")
  public User fingById(@PathVariable Long id) {
    return this.restTemplate.getForObject(this.userServiceUrl + id, User.class);
  }

  @GetMapping("/user-instance")
  public List<ServiceInstance> showInfo() {
    return this.discoveryClient.getInstances("user");
  }
}
