package com.cham.auditconsumer.controller;

import com.cham.auditconsumer.repository.AuditCassandraRepository;
import com.cham.eventsourcecomponent.pojo.AuditObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class EventController {

    @Autowired
    private AuditCassandraRepository auditCassandraRepository;

    @GetMapping(value = "events/all", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<AuditObj> getAllEvents(){
        return auditCassandraRepository.findAll();
    }
}
