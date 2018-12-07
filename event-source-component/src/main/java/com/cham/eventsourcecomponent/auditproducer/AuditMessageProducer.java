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

@Service
public class AuditMessageProducer {

    @Autowired
    private AuditStream auditStream;

    @Autowired
    private Tracer tracer;

    @Value("${spring.application.name}")
    private String appName;

    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    private static Logger log = LoggerFactory.getLogger(AuditMessageProducer.class);

    public void publishAuditMessages(final String event) {
        log.info("Publishing the audit event..");
        AuditObj auditObj = getAuditObj(event);
        log.info("Sending audit message to kafka topic " + auditObj.toString());
        MessageChannel messageChannel = auditStream.outboundAuditMessages();
        messageChannel.send(MessageBuilder
                    .withPayload(auditObj)
                    .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                    .build());
    }

    private AuditObj getAuditObj(String event) {
        AuditObj auditObj = new AuditObj();
        auditObj.setEventId(getTraceId());
        auditObj.setEventSource(appName);
        auditObj.setEventDateTime(dtf.format(LocalDateTime.now()));
        auditObj.setEventData(event);
        return auditObj;
    }

    private String getTraceId(){
        return tracer.currentSpan().context().traceIdString();
    }

}