package com.cham.eventsourcecomponent.auditproducer;


import brave.Tracer;
import com.cham.eventsourcecomponent.pojo.AuditObj;
import com.cham.eventsourcecomponent.stream.AuditStream;
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
import java.util.UUID;

@Service
public class AuditMessageProducer {

    @Autowired
    private AuditStream auditStream;

    @Autowired
    private Tracer tracer;

    @Value("${spring.application.name}")
    private String appName;

    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss:SSS");

    private static Logger log = LoggerFactory.getLogger(AuditMessageProducer.class);

    public void publishAuditMessages(final String event) {
        AuditObj auditObj = getAuditObj(event);
        log.info("Sending audit message to kafka topic " + auditObj.toString());
        MessageChannel messageChannel = auditStream.outboundAuditMessages();
            messageChannel.send(MessageBuilder
                    .withPayload(auditObj)
                    .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                    .build());
    }

    private AuditObj getAuditObj(String event) {
        return new AuditObj(UUID.randomUUID().toString(),getTraceId(),appName,dtf.format(LocalDateTime.now()),event);
    }

    private String getTraceId(){
        return tracer.currentSpan().context().traceIdString();
    }

}