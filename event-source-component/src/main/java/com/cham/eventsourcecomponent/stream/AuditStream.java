package com.cham.eventsourcecomponent.stream;


import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

@Component
public interface AuditStream {

    String OUTPUT = "audit-out";

    @Output(OUTPUT)
    MessageChannel outboundAuditMessages();

}
