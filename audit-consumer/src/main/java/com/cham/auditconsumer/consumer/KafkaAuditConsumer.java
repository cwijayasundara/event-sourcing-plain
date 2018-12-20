package com.cham.auditconsumer.consumer;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import com.cham.auditconsumer.config.SpringExtension;
import com.cham.auditconsumer.stream.AuditStream;
import com.cham.eventsourcecomponent.pojo.AuditObj;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Configuration("AppConfiguration.class")
public class KafkaAuditConsumer {

    private static Logger log = LoggerFactory.getLogger(KafkaAuditConsumer.class);

    @Autowired
    private ActorSystem system;

    @StreamListener(AuditStream.INPUT)
    public void consumeAudits(@Payload AuditObj auditObj) {
        log.info("Received Audit .." + auditObj);
        getActorRef().tell(auditObj,null);
    }

    private ActorRef getActorRef(){
        return system.actorOf(SpringExtension.SPRING_EXTENSION.get(system).props("eventSourceActor"));
    }
}
