package com.eventmanagement;

import java.util.Date;

public class Event {
    private int eventId;
    private String eventTitle;
    private String eventDescription;
    private String eventType;
    private Date eventDate;
    private String eventStatus; // e.g., "pending", "approved", "cancelled"
    private int customerId; 

    // Constructors
    public Event() {
        // Default constructor
    }

    public Event(String eventTitle, String eventDescription, String eventType, Date eventDate, String eventStatus, int customerId) {
        this.eventTitle = eventTitle;
        this.eventDescription = eventDescription;
        this.eventType = eventType;
        this.eventDate = eventDate;
        this.eventStatus = eventStatus;
        this.customerId = customerId;
    }

    // Getters and Setters
    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getEventTitle() {
        return eventTitle;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventStatus() {
        return eventStatus;
    }

    public void setEventStatus(String eventStatus) {
        this.eventStatus = eventStatus;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    // Other methods (if needed)
    @Override
    public String toString() {
        return "Event{" +
               "eventId=" + eventId +
               ", eventTitle='" + eventTitle + '\'' +
               ", eventDescription='" + eventDescription + '\'' +
               ", eventType='" + eventType + '\'' +
               ", eventDate=" + eventDate +
               ", eventStatus='" + eventStatus + '\'' +
               ", customerId=" + customerId +
               '}';
    }
}