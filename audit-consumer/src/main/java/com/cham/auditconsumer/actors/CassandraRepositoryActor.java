package com.cham.auditconsumer.actors;

import akka.actor.UntypedActor;
import com.cham.eventsourcecomponent.pojo.AuditObj;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CassandraRepositoryActor extends UntypedActor {

    private static Logger log = LoggerFactory.getLogger(CassandraRepositoryActor.class);

    @Override
    public void onReceive(Object message) {
        if (message instanceof AuditObj) {
            log.info("Inside CassandraRepositoryActor.onReceive()..");
        }
    }

}