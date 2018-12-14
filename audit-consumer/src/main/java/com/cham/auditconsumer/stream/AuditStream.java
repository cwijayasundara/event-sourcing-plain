package com.cham.auditconsumer.stream;


import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

@Component
public interface AuditStream {

    String INPUT = "audit-out";

    @Input(INPUT)
    SubscribableChannel inboundAuditStream();
}
