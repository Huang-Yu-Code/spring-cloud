# Spring-Cloud

## 示例环境(依赖、版本)

### Java

Version: 1.8

[下载地址](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html)

### Maven

Version: 3.8.1

[官网](https://maven.apache.org/)

[下载地址](https://maven.apache.org/download.cgi)

[仓库](https://mvnrepository.com/)

#### Spring-Boot

Version: 2.5.2

```xml

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-dependencies</artifactId>
    <type>pom</type>
    <scope>import</scope>
    <version>2.5.2</version>
</dependency>
```

#### Spring-cloud

Version: 2020.0.3

```xml

<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-dependencies</artifactId>
    <version>2020.0.3</version>
    <type>pom</type>
    <scope>import</scope>
</dependency>
```

### Docker

System: Ubuntu20.04.1

Version: 20.10.6

[官网](https://www.docker.com/)

[下载地址](https://www.docker.com/products/docker-desktop)

[文档](https://docs.docker.com/)

[Docker Hub](https://hub.docker.com/)

### docker-compose

version: 1.29.1

windows系统不需要单独安装

[下载地址](https://github.com/docker/compose/releases)

---

## Netflix

[GitHub](https://github.com/spring-cloud/spring-cloud-netflix)

[参考文档](https://spring.io/projects/spring-cloud-netflix)

### Eureka

[示例](./netflix/eureka)

### Ribbon

[示例](./netflix/ribbon)

### Hystrix

[示例](./netflix/hystrix)

### zuul

[示例](./netflix/zuul)

## Config

[GitHub](https://github.com/spring-cloud/spring-cloud-config)

[参考文档](https://docs.spring.io/spring-cloud-config/docs/current/reference/html/)

[示例](./config)

## Bus

[GitHub](https://github.com/spring-cloud/spring-cloud-bus)

[参考文档](https://docs.spring.io/spring-cloud-bus/docs/current/reference/html/)

[示例](./bus)

## Consul

[官网](https://www.consul.io/)

[下载地址](https://www.consul.io/downloads)

[GitHub](https://github.com/spring-cloud/spring-cloud-consul)

[参考文档](https://spring.io/projects/spring-cloud-consul)

[示例](./consul)

## Zookeeper

[官网](https://zookeeper.apache.org/)

[下载地址](https://zookeeper.apache.org/releases.html#download)

[GitHub](https://github.com/spring-cloud/spring-cloud-zookeeper)

[参考文档](https://docs.spring.io/spring-cloud-zookeeper/docs/current/reference/html/)

[zkui](https://github.com/DeemOpen/zkui)

[示例](./zookeeper)

## Openfeign

[GitHub](https://github.com/spring-cloud/spring-cloud-openfeign)

[参考文档](https://docs.spring.io/spring-cloud-openfeign/docs/current/reference/html/)

[示例](./openfeign)

## Gateway

[GitHub](https://github.com/spring-cloud/spring-cloud-gateway)

[参考文档](https://docs.spring.io/spring-cloud-gateway/docs/current/reference/html/)

[示例](./gateway)


## Kubernetes

[GitHub](https://github.com/spring-cloud/spring-cloud-kubernetes)

[参考文档](https://docs.spring.io/spring-cloud-kubernetes/docs/current/reference/html/)

[示例](./kubernetes)
