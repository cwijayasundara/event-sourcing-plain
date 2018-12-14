package com.cham.auditconsumer.repository;

import com.cham.eventsourcecomponent.pojo.AuditObj;
import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class EventSourceHazelcastImpl implements EventSourceRepository{

    private ClientConfig clientConfig = new ClientConfig();

    private HazelcastInstance client = HazelcastClient.newHazelcastClient(clientConfig);

    private static Logger log = LoggerFactory.getLogger(EventSourceHazelcastImpl.class);


    private String mapName = "audit-map";

    public void saveAuditEvent(AuditObj auditObj){
        log.info("Inside EventSourceHazelcastImpl.saveAuditEvent()..");
        IMap<String, AuditObj> serverAuditMap = client.getMap(mapName);
        serverAuditMap.put(UUID.randomUUID().toString(), auditObj);
    }

}
