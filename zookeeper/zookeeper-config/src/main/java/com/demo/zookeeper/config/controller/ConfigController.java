package com.demo.zookeeper.config.controller;

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
    @Value("${config.center}")
    private String center;
    @Value("${config.author}")
    private String author;
    @Value("${config.info}")
    private String info;
    @Value("${config.version}")
    private String version;

    @GetMapping("/")
    public String index() {
        return service + "---" + port + "---" + center + "---" + author + "---" + info + "---" + version;
    }
}
