# Netflix-Eureka示例说明

## Server

```xml

<dependencies>
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
    </dependency>
    <dependency>
        <groupId>com.github.ben-manes.caffeine</groupId>
        <artifactId>caffeine</artifactId>
    </dependency>
</dependencies>
```

### 单机

1. application.yml

```yaml
server:
  port: 8761
spring:
  application:
    name: eureka-server
eureka:
  instance:
    hostname: localhost
    prefer-ip-address: true
  client:
    register-with-eureka: false
    fetch-registry: false
    service-url:
      defaultZone: http://${eureka.instance.hostname}:${server.port}/eureka/
info:
  name: ${spring.application.name}
  host: ${eureka.instance.hostname}
  port: ${server.port}
management:
  endpoints:
    web:
      exposure:
        include: '*'
```

2. Application.java

```java

@SpringBootApplication
@EnableEurekaServer
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```

### 集群

1. Windows下修改**host**文件
    - 路径:`C:\Windows\System32\drivers\etc`
    - 备份
    - 添加以下内容

```text
127.0.0.1 server1
127.0.0.1 server2 
127.0.0.1 server3
```

2. application.yml

```yaml
spring:
  config:
    activate:
      on-profile: cluster1
server:
  port: 8761
eureka:
  instance:
    hostname: server1
  client:
    fetch-registry: true
    service-url:
      defaultZone: http://server2:8762/eureka/,http://server3:8763/eureka/
---
spring:
  config:
    activate:
      on-profile: cluster2
server:
  port: 8762
eureka:
  instance:
    hostname: server2
  client:
    fetch-registry: true
    service-url:
      defaultZone: http://server1:8761/eureka/,http://server3:8763/eureka/
---
spring:
  config:
    activate:
      on-profile: cluster3
server:
  port: 8763
eureka:
  instance:
    hostname: server3
  client:
    fetch-registry: true
    service-url:
      defaultZone: http://server1:8761/eureka/,http://server2:8762/eureka/
```

3. Application.java

```java

@SpringBootApplication
@EnableEurekaServer
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```

## Client

```xml

<dependencies>
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
    </dependency>
    <dependency>
        <groupId>com.github.ben-manes.caffeine</groupId>
        <artifactId>caffeine</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-actuator</artifactId>
    </dependency>
</dependencies>
```

### Provider

1. application.yml

```yaml
server:
  port: 8081
spring:
  application:
    name: provider
eureka:
  instance:
    hostname: localhost
    prefer-ip-address: true
    instance-id: ${spring.application.name}-${eureka.instance.hostname}:${server.port}
  client:
    healthcheck:
      enabled: true
    service-url:
      defaultZone: http://localhost:8761/eureka/
info:
  name: ${spring.application.name}
  host: ${eureka.instance.hostname}
  port: ${server.port}
management:
  endpoints:
    web:
      exposure:
        include: '*'
```

2. Application.java

```java

@SpringBootApplication
@EnableEurekaClient
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

```

### Consumer

1. application.yml

```yaml
server:
  port: 8084
spring:
  application:
    name: consumer
eureka:
  instance:
    hostname: localhost
    prefer-ip-address: true
    instance-id: ${spring.application.name}-${eureka.instance.hostname}:${server.port}
    homePageUrl: http://${eureka.hostname}:${server.port}/
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka/
info:
  name: ${spring.application.name}
  host: ${eureka.instance.hostname}
  port: ${server.port}
management:
  endpoints:
    web:
      exposure:
        include: '*'
```

2. 服务调用

```java

@Configuration
public class BeanConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
```

通过服务的地址调用

```java

@RestController
public class ConsumerController {
    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/provider")
    public String getProvider() {
        return restTemplate.getForObject("http://localhost:8081/", String.class);
    }
}
```

3. 负载均衡

`@LoadBalanced`开启负载均衡

```java

@Configuration
public class BeanConfig {
    @Bean
    @LoadBalanced
    public RestTemplate loadbalancerRestTemplate() {
        return new RestTemplate();
    }
}
```

通过服务名调用(spring.application.name)

```java

@RestController
public class ConsumerController {

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/provider")
    public String getProvider() {
        return loadbalancerRestTemplate.getForObject("http://provider/", String.class);
    }
}
```

4. Application.java

```java

@SpringBootApplication
@EnableEurekaClient
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

```