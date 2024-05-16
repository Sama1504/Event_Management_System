package com.eventmanagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EventManager {
    public static void createEvent(Event event, int customerId) {
        // Get the database connection
        Connection conn = DatabaseConnection.getConnection();

        try {
            // Insert a new event into the Event table
            String query = "INSERT INTO Event (eventTitle, eventDescription, eventType, eventDate, eventStatus, customerId) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, event.getEventTitle());
            stmt.setString(2, event.getEventDescription());
            stmt.setString(3, event.getEventType());
            stmt.setDate(4, new java.sql.Date(event.getEventDate().getTime()));
            stmt.setString(5, event.getEventStatus());
            stmt.setInt(6, customerId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the database connection
            DatabaseConnection.closeConnection(conn);
        }
    }
    
    public static List<Event> getEventsByCustomerId(int customerId) {
        List<Event> events = new ArrayList<>();

        // Get the database connection
        Connection conn = DatabaseConnection.getConnection();

        try {
            // Retrieve events from the Event table based on the customerId
            String query = "SELECT * FROM Event WHERE customerId = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Event event = new Event();
                event.setEventId(rs.getInt("eventId"));
                event.setEventTitle(rs.getString("eventTitle"));
                event.setEventDescription(rs.getString("eventDescription"));
                event.setEventType(rs.getString("eventType"));
                event.setEventDate(rs.getDate("eventDate"));
                event.setEventStatus(rs.getString("eventStatus"));
                event.setCustomerId(rs.getInt("customerId"));
                events.add(event);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the database connection
            DatabaseConnection.closeConnection(conn);
        }

        return events;
    }
    
    
    public static List<Event> getAllEvents() {
        List<Event> events = new ArrayList<>();

        // Get the database connection
        Connection conn = DatabaseConnection.getConnection();

        try {
            // Retrieve all events from the Event table
            String query = "SELECT * FROM Event";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Event event = new Event();
                event.setEventId(rs.getInt("eventId"));
                event.setEventTitle(rs.getString("eventTitle"));
                event.setEventDescription(rs.getString("eventDescription"));
                event.setEventType(rs.getString("eventType"));
                event.setEventDate(rs.getDate("eventDate"));
                event.setEventStatus(rs.getString("eventStatus"));
                event.setCustomerId(rs.getInt("customerId"));
                events.add(event);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the database connection
            DatabaseConnection.closeConnection(conn);
        }

        return events;
    }

    public static void updateEvent(Event event) {
        // Get the database connection
        Connection conn = DatabaseConnection.getConnection();

        try {
            // Update the event in the Event table
            String query = "UPDATE Event SET eventTitle = ?, eventDescription = ?, eventType = ?, eventDate = ?, eventStatus = ? WHERE eventId = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, event.getEventTitle());
            stmt.setString(2, event.getEventDescription());
            stmt.setString(3, event.getEventType());
            stmt.setDate(4, new java.sql.Date(event.getEventDate().getTime()));
            stmt.setString(5, event.getEventStatus());
            stmt.setInt(6, event.getEventId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the database connection
            DatabaseConnection.closeConnection(conn);
        }
    }

    public static void deleteEvent(int eventId) {
        // Get the database connection
        Connection conn = DatabaseConnection.getConnection();

        try {
            // Delete the event from the Event table
            String query = "DELETE FROM Event WHERE eventId = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, eventId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the database connection
            DatabaseConnection.closeConnection(conn);
        }
    }
}