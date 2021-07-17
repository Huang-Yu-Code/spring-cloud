# Consul示例说明

## Config

导入依赖

```xml

<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-consul-config</artifactId>
</dependency>
```

bootstrap.yml

```yaml
server:
  port: 8084
spring:
  application:
    name: consul-config
  cloud:
    consul:
      host: server
      port: 8500
      discovery:
        prefer-ip-address: true
        service-name: ${spring.application.name}-${server.port}
      config:
        enabled: true
        format: yaml
        prefixes: config
        defaultContext: application
        profileSeparator: ','
        data-key: data
```

```java

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
```

## Discovery

导入依赖

```xml

<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-consul-discovery</artifactId>
</dependency>
```

### Provider

### Consumer