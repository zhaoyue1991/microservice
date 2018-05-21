package com.example.movie.controller;

import com.example.movie.entity.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@Log4j2
public class MovieController {

  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private DiscoveryClient discoveryClient;

  @Autowired
  private LoadBalancerClient loadBalancerClient;

  @Value("${user.serviceUrl}")
  private String userServiceUrl;

  @HystrixCommand(fallbackMethod = "findByIdFallback")
  @GetMapping("/find/{id}")
  public User findById(@PathVariable Long id) {
    return this.restTemplate.getForObject(this.userServiceUrl + id, User.class);
  }

  public User findByIdFallback(Long id) {
    return User.builder().id(-1L).name("默认用户").build();
  }

  @GetMapping("/user-instance")
  public List<ServiceInstance> showInfo() {
    return this.discoveryClient.getInstances("user");
  }

  @GetMapping("/log-instance")
  public void logUserInstance() {
    ServiceInstance serviceInstance = this.loadBalancerClient.choose("user");
    log.info("{}:{}:{}", serviceInstance.getServiceId(), serviceInstance.getHost(), serviceInstance.getPort());
  }
}
