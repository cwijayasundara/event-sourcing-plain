package com.cham.feignserver.controller;

import com.cham.feignserver.domain.Trade;
import com.cham.feignserver.producer.KafkaTradeProducer;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TradeServiceController {

    private static Logger log = LoggerFactory.getLogger(TradeServiceController.class);

    @Autowired
    private KafkaTradeProducer kafkaTradeProducer;

    @Autowired
    Gson gson;

    @GetMapping(value = "/api/stream/trades", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getTradeStream() {
        log.info("Inside TradeServiceController.getTradeStream..");
        Trade trade = new Trade("swap1234", "SWAP", 1000);
        kafkaTradeProducer.publishTrades(trade);
        String tradeStr = gson.toJson(trade);
        log.info("Returned value from the controller is " + tradeStr);
        return ResponseEntity.ok().body(tradeStr);
    }
}
