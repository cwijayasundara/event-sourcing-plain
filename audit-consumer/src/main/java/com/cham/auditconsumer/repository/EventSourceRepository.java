package com.cham.auditconsumer.repository;

import com.cham.eventsourcecomponent.pojo.AuditObj;

public interface EventSourceRepository {

     void saveAuditEvent(AuditObj auditObj);
}
