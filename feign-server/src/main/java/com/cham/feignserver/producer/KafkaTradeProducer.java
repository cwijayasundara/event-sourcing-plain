package com.cham.feignserver.producer;

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
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class KafkaTradeProducer {

    private final TradeStream tweetStreams;

    private static Logger log = LoggerFactory.getLogger(KafkaTradeProducer.class);

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

    public KafkaTradeProducer(TradeStream tradeStream) {
        this.tweetStreams = tradeStream;
    }

    public void publishTrades(final Trade trade) {
        log.info("Sending trade to kafka topic" + trade);
        MessageChannel messageChannel = tweetStreams.outboundTrades();

        String tradeStr = gson.toJson(trade);
        AuditObj auditObj = new AuditObj(getTraceId(),appName, dtf.format(LocalDateTime.now()), tradeStr);

        messageChannel.send(MessageBuilder
                .withPayload(trade)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .build());

        auditMessageProducer.publishAuditMessages(auditObj);

    }

    private String getTraceId(){
        return tracer.currentSpan().context().traceIdString();
    }
}