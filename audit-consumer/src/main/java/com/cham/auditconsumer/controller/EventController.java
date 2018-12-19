package com.cham.auditconsumer.controller;

import com.cham.auditconsumer.repository.AuditCassandraRepository;
import com.cham.eventsourcecomponent.pojo.AuditObj;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
public class EventController {

    @Autowired
    private AuditCassandraRepository auditCassandraRepository;

    private static Logger log = LoggerFactory.getLogger(EventController.class);

    @GetMapping(value = "events/all", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<AuditObj> getAllEvents(){
        return auditCassandraRepository.findAll();
    }

    @RequestMapping(value = "events/{eventId}",produces = MediaType.TEXT_EVENT_STREAM_VALUE, method = RequestMethod.GET )
    public Flux<AuditObj> getEventsById(@PathVariable String eventId){
        log.info("Inside EventController.getEventsById().." + eventId);
        return auditCassandraRepository.getAuditObjByEventId(eventId);
    }
}
