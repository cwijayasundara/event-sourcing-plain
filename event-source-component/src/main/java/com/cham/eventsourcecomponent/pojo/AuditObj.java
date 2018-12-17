package com.cham.eventsourcecomponent.pojo;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

@Table(value = "event_source")
public class AuditObj {

    @PrimaryKeyColumn(value = "id", type = PrimaryKeyType.PARTITIONED )
    private String id;

    @Column(value = "eventId")
    private String eventId;

    @Column(value = "eventSource")
    private String eventSource;

    @Column(value = "eventDateTime")
    private String eventDateTime;

    @Column(value = "eventData")
    private String eventData;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public AuditObj(){}

    public AuditObj(String id, String eventId, String sourceSystem, String receivedDateTime, String eventData) {
        this.id = id;
        this.eventId = eventId;
        this.eventSource = sourceSystem;
        this.eventDateTime = receivedDateTime;
        this.eventData = eventData;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getEventSource() {
        return eventSource;
    }

    public void setEventSource(String eventSource) {
        this.eventSource = eventSource;
    }

    public String getEventDateTime() {
        return eventDateTime;
    }

    public void setEventDateTime(String eventDateTime) {
        this.eventDateTime = eventDateTime;
    }

    public String getEventData() {
        return eventData;
    }

    public void setEventData(String eventData) {
        this.eventData = eventData;
    }

    @Override
    public String toString() {
        return "AuditObj{" +
                "id='" + id + '\'' +
                ", eventId='" + eventId + '\'' +
                ", eventSource='" + eventSource + '\'' +
                ", eventDateTime='" + eventDateTime + '\'' +
                ", eventData='" + eventData + '\'' +
                '}';
    }

}
