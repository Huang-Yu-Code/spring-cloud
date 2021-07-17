package com.demo.netflix.eureka.client.consumer.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class ConsumerController {

    @Value("${spring.profiles.active:default}")
    private String env;

    @Value("${eureka.instance.instance-id}")
    private String instanceId;

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private RestTemplate loadbalancerRestTemplate;

    @GetMapping("/")
    public String index() {
        return instanceId;
    }

    @GetMapping("/provider")
    public String getProvider() {
        if ("cluster".equals(env)) {
            return restTemplateLoadbalancer();
        }
        return restTemplate();
    }

    private String restTemplate() {
        return restTemplate.getForObject("http://localhost:8081/", String.class);
    }

    private String restTemplateLoadbalancer() {
        return loadbalancerRestTemplate.getForObject("http://eureka-provider/", String.class);
    }

}
