package com.cham.auditconsumer.actors;

import akka.actor.AbstractActor;
import com.cham.auditconsumer.repository.AuditCassandraRepository;
import com.cham.eventsourcecomponent.pojo.AuditObj;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.Instant;

import static java.time.temporal.ChronoUnit.NANOS;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class EventSourceActor extends AbstractActor {

    private static Logger log = LoggerFactory.getLogger(EventSourceActor.class);

    @Autowired
    private AuditCassandraRepository auditCassandraRepository;

    @Override
    public Receive createReceive() {
        return receiveBuilder().match(AuditObj.class, r ->
        {
            log.info("Inside CassandraRepositoryActor.createReceive()..");

            try {
                Instant start = Instant.now();
                auditCassandraRepository.insert(Flux.just(r)).subscribe();
                Instant end = Instant.now();
                Duration duration = Duration.between(start,end);
                log.info("Time taken to publish event to Cassandra in milli-seconds is " + duration.get(NANOS)/1000000);
            }catch(Exception ex){
                getSender().tell(new akka.actor.Status.Failure(ex), getSelf());
                throw ex;
            }
        }).build();
    }
}