package com.cham.eventsourcecomponent.pojo;

public class AuditObj {

    private String eventId;
    private String eventSource;
    private String eventDateTime;
    private String eventData;

    public AuditObj(){}

    @Override
    public String toString() {
        return "AuditObj{" +
                "eventId='" + eventId + '\'' +
                ", eventSource='" + eventSource + '\'' +
                ", eventDateTime='" + eventDateTime + '\'' +
                ", eventData='" + eventData + '\'' +
                '}';
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

    public AuditObj(String eventId, String sourceSystem, String receivedDateTime, String eventData) {
        this.eventId = eventId;
        this.eventSource = sourceSystem;
        this.eventDateTime = receivedDateTime;
        this.eventData = eventData;
    }

}
