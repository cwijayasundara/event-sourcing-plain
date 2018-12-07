package com.cham.feignclientapp.controller;

import com.cham.eventsourcecomponent.auditproducer.AuditMessageProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TradeConsumerController {

    @Autowired
    private TradeFeignClient tradeFeignClient;

    private static Logger log = LoggerFactory.getLogger(TradeConsumerController.class);

    @Autowired
    private AuditMessageProducer auditMessageProducer;

    @GetMapping(value = "/api/feign/trades", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String getTradesViaFeignClient() {
        log.info("Inside TradeConsumerController.getTradesViaFeignClient..");
        String tradeResponse = tradeFeignClient.getTrades();
        // audit
        auditMessageProducer.publishAuditMessages(tradeResponse);
        return tradeResponse;
    }

}
