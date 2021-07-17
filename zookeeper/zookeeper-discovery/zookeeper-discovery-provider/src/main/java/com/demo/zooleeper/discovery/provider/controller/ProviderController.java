package com.demo.zooleeper.discovery.provider.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author codingob
 */
@RestController
public class ProviderController {
    @Value("${spring.application.name}")
    private String service;

    @Value("${server.port}")
    private int port;

    @GetMapping("/")
    public String index() {
        return service + "---" + port;
    }
}
