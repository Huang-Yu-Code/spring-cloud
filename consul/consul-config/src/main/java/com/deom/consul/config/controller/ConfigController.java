package com.deom.consul.config.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author codingob
 */
@RestController
@RefreshScope
public class ConfigController {
    @Value("${spring.application.name}")
    private String service;

    @Value("${server.port}")
    private String port;

    @Value("${config.name}")
    private String name;

    @Value("${config.author}")
    private String author;

    @Value("${config.version}")
    private String version;

    @GetMapping("/")
    public String index() {
        return service + "-" + port + "-" + name + "-" + author + "-" + version;
    }
}
