package com.demo.config.client.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class ConfigController {

    @Value("${eureka.instance.instance-id}")
    private String service;

    @Value("${config.author}")
    private String author;

    @Value("${config.email}")
    private String email;

    @Value("${config.info}")
    private String info;

    @Value("${config.version}")
    private String version;

    @GetMapping("/")
    public String index() {
        return service + "\n" +
                author + "\n" +
                email + "\n" +
                info + "\n" +
                version + "\n";
    }
}
