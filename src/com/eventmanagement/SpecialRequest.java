package com.eventmanagement;

public class SpecialRequest {
    private int requestId;
    private String requestDescription;
    private int eventId;

    // Default constructor
    public SpecialRequest() {
    }

    public SpecialRequest(String requestDescription, int eventId) {
        this.requestDescription = requestDescription;
        this.eventId = eventId;
    }

    // Getters and Setters
    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public String getRequestDescription() {
        return requestDescription;
    }

    public void setRequestDescription(String requestDescription) {
        this.requestDescription = requestDescription;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }
}