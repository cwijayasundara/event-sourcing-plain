package com.cham.auditconsumer.consumer;

import com.cham.auditconsumer.repository.AuditCassandraRepository;
import com.cham.auditconsumer.stream.AuditStream;
import com.cham.eventsourcecomponent.pojo.AuditObj;
import com.sun.tools.javac.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class KafkaAuditConsumer {

    private static Logger log = LoggerFactory.getLogger(KafkaAuditConsumer.class);

    @Autowired
    private AuditCassandraRepository auditCassandraRepository;

    @StreamListener(AuditStream.INPUT)
    public void consumeAudits(@Payload AuditObj auditObj) {
        log.info("Received Audit .." + auditObj);
        auditCassandraRepository.insert(List.of(auditObj)).subscribe();
    }
}
