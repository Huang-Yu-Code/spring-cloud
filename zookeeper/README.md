# ZooKeeper示例说明

## Config

Zookeeper服务配置中心

1. 导入依赖

```xml

<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-zookeeper-config</artifactId>
</dependency>
```

2. 配置application.yml

```yaml
server:
  port: 8085
spring:
  application:
    name: zookeeper-config
  profiles:
    active: dev
  cloud:
    zookeeper:
      connect-string: server:2181
      config:
        root: config
        profile-separator: ','
```

3. 安装好Zookeeper环境，启动Zookeeper配置中心

添加相关配置信息,通过zkui导入[示例配置文件](../docker/zkui/zookeeper-config.txt)

```text
/appconfig/hosts=foo=bar
/config/zookeeper-config,dev=config.author=codingob
/config/zookeeper-config,dev=config.center=zookeeper
/config/zookeeper-config,dev=config.info=config-info
/config/zookeeper-config,dev=config.version=1.0.0
```

## Discovery

Zookeeper服务注册中心

导入主要依赖

```xml

<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-zookeeper-discovery</artifactId>
</dependency>
```

### Provider

1. 配置application.yml

```yaml
server:
  port: 8081
spring:
  application:
    name: zookeeper-discovery-provider
  cloud:
    zookeeper:
      connect-string: server:2181
```
2. Controller、Service等

3. 启动类

```java

@SpringBootApplication
@EnableDiscoveryClient
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```

### Consumer

1. 配置application.yml

```yaml
server:
  port: 8084
spring:
  application:
    name: zookeeper-discovery-consumer
  cloud:
    zookeeper:
      connect-string: server:2181
```

2. Bean配置

```java
@Configuration
public class BeanConfig {
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
```

3. Controller、Service等

4. 启动类

```java
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```