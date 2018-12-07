package com.cham.feignserver.consumer;

import brave.Tracer;
import com.cham.eventsourcecomponent.auditproducer.AuditMessageProducer;
import com.cham.eventsourcecomponent.pojo.AuditObj;
import com.cham.feignserver.domain.Trade;
import com.cham.feignserver.stream.TradeStream;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class KafkaTradeConsumer {

    private static Logger log = LoggerFactory.getLogger(KafkaTradeConsumer.class);

    // audit stuff
    @Autowired
    private Tracer tracer;

    @Value("${spring.application.name}")
    private String appName;

    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    // end audit stuff

    @Autowired
    private AuditMessageProducer auditMessageProducer;

    Gson gson = new Gson();

    @StreamListener(TradeStream.INPUT)
    public void consumeTweets(@Payload Trade trade) {
        log.info("Received trade .." + trade);

        String tradeStr = gson.toJson(trade);
        AuditObj auditObj = new AuditObj(getTraceId(),appName, dtf.format(LocalDateTime.now()), tradeStr);
        auditMessageProducer.publishAuditMessages(auditObj);

    }

    private String getTraceId(){
        return tracer.currentSpan().context().traceIdString();
    }
}
