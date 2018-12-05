
Pls view this in raw mode..

Sleuth producers:

You need to include the below dependencies in your .pom

<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-sleuth</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-sleuth-zipkin</artifactId>
</dependency>
<dependency>
    <groupId>io.micrometer</groupId>
    <artifactId>micrometer-registry-prometheus</artifactId>
    <scope>runtime</scope>
</dependency>

In application.yml

spring:
  application:
    name: tradeservice
  sleuth:
    sampler:
      percentage: 1.0
  zipkin:
    base-url: http://zipkin/    
    
Zipkin server

You need to add the below dependencies in the .pom.

<dependency>
    <groupId>io.zipkin.java</groupId>
	<artifactId>zipkin-server</artifactId>
	<version>2.11.8</version>
</dependency>
<dependency>
    <groupId>io.zipkin.java</groupId>
    <artifactId>zipkin-autoconfigure-ui</artifactId>
    <version>2.11.6</version>
    <scope>runtime</scope>
</dependency>

In application.yml of the Zipkin server

spring:
  application:
    name: zipkin

management:
  metrics:
    web:
      server:
        auto-time-requests: false

Logs:
2018-11-07 13:38:12.969  INFO [tradeclient,94b1a90c4336833c,94b1a90c4336833c,false] 38863 --- [ctor-http-nio-2] c.c.f.c.TradeConsumerController          : Inside TradeConsumerController.getTradesViaFeignClient..
2018-11-07 13:38:13.383  INFO [tradeservice,94b1a90c4336833c,d458054744d08c05,false] 38859 --- [ctor-http-nio-2] c.c.f.controller.TradeServiceController  : Inside TradeServiceController.getTradeStream..
2018-11-07 13:38:13.383  INFO [tradeservice,94b1a90c4336833c,d458054744d08c05,false] 38859 --- [ctor-http-nio-2] c.c.f.producer.KafkaTradeProducer        : Sending trade to kafka topicTrade{tradeId=swap1234, tradeName='SWAP', amount='1000'}
2018-11-07 13:38:13.455  INFO [tradeservice,94b1a90c4336833c,a11df97425697a08,false] 38859 --- [container-0-C-1] c.c.f.consumer.KafkaTradeConsumer        : Received trade ..Trade{tradeId=swap1234, tradeName='SWAP', amount='1000'}


