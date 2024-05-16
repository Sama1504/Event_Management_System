package com.eventmanagement;

public class Attendee {
    private int attendeeId;
    private String attendeeName;
    private int eventId; // To associate the attendee with an event

    // Constructors
    public Attendee() {
        // Default constructor
    }

    public Attendee(String attendeeName, int eventId) {
        this.attendeeName = attendeeName;
        this.eventId = eventId;
    }

    // Getters and Setters
    public int getAttendeeId() {
        return attendeeId;
    }

    public void setAttendeeId(int attendeeId) {
        this.attendeeId = attendeeId;
    }

    public String getAttendeeName() {
        return attendeeName;
    }

    public void setAttendeeName(String attendeeName) {
        this.attendeeName = attendeeName;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    // Other methods (if needed)
    @Override
    public String toString() {
        return "Attendee{" +
               "attendeeId=" + attendeeId +
               ", attendeeName='" + attendeeName + '\'' +
               ", eventId=" + eventId +
               '}';
    }
}