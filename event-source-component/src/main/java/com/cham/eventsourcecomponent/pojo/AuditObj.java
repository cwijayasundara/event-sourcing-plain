package com.cham.eventsourcecomponent.pojo;

public class AuditObj {

    private String eventId;
    private String sourceSystem;
    private String receivedDateTime;
    private String eventData;

    @Override
    public String toString() {
        return "AuditObj{" +
                "eventId='" + eventId + '\'' +
                ", sourceSystem='" + sourceSystem + '\'' +
                ", receivedDateTime='" + receivedDateTime + '\'' +
                ", eventData='" + eventData + '\'' +
                '}';
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getSourceSystem() {
        return sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public String getReceivedDateTime() {
        return receivedDateTime;
    }

    public void setReceivedDateTime(String receivedDateTime) {
        this.receivedDateTime = receivedDateTime;
    }

    public String getEventData() {
        return eventData;
    }

    public void setEventData(String eventData) {
        this.eventData = eventData;
    }

    public AuditObj(String eventId, String sourceSystem, String receivedDateTime, String eventData) {
        this.eventId = eventId;
        this.sourceSystem = sourceSystem;
        this.receivedDateTime = receivedDateTime;
        this.eventData = eventData;
    }

    public AuditObj(){}

}
