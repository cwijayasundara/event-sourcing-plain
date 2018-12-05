package com.cham.feignclientapp.controller;

import brave.Tracer;
import com.cham.eventsourcecomponent.auditproducer.AuditMessageProducer;
import com.cham.eventsourcecomponent.pojo.AuditObj;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
public class TradeConsumerController {

    @Autowired
    private TradeFeignClient tradeFeignClient;

    @Autowired
    private Tracer tracer;

    @Value("${spring.application.name}")
    private String appName;

    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    private static Logger log = LoggerFactory.getLogger(TradeConsumerController.class);

    private AuditMessageProducer auditMessageProducer;

    public TradeConsumerController(){
        auditMessageProducer = new AuditMessageProducer();
    }

    @GetMapping(value = "/api/feign/trades", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String getTradesViaFeignClient() {
        log.info("Inside TradeConsumerController.getTradesViaFeignClient..");
        String tradeResponse = tradeFeignClient.getTrades();
        AuditObj auditObj = new AuditObj(getTraceId(),appName, dtf.format(LocalDateTime.now()), tradeResponse);
        auditMessageProducer.publishAuditMessages(auditObj);
        return tradeResponse;
    }

    private String getTraceId(){
        return tracer.currentSpan().context().traceIdString();
    }

}
