package com.cham.eventsourcecomponent.auditproducer;


import com.cham.eventsourcecomponent.pojo.AuditObj;
import com.cham.eventsourcecomponent.stream.AuditStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

@Service
public class AuditMessageProducer {

    @Autowired
    private AuditStream auditStream;

    private static Logger log = LoggerFactory.getLogger(AuditMessageProducer.class);

    public void publishAuditMessages(final AuditObj auditObj) {
        log.info("Sending audit message to kafka topic" + auditObj);
        MessageChannel messageChannel = auditStream.outboundAuditMessages();
        messageChannel.send(MessageBuilder
                    .withPayload(auditObj)
                    .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                    .build());
    }
}