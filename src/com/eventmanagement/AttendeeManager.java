package com.eventmanagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AttendeeManager {
    public static void addAttendee(Attendee attendee) {
        // Get the database connection
        Connection conn = DatabaseConnection.getConnection();

        try {
            // Insert a new attendee into the Attendee table
            String query = "INSERT INTO Attendee (attendeeName, eventId) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, attendee.getAttendeeName());
            stmt.setInt(2, attendee.getEventId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the database connection
            DatabaseConnection.closeConnection(conn);
        }
    }

    public static List<Attendee> getAttendeesByEventId(int eventId) {
        List<Attendee> attendees = new ArrayList<>();

        // Get the database connection
        Connection conn = DatabaseConnection.getConnection();

        try {
            // Retrieve attendees from the Attendee table based on the eventId
            String query = "SELECT * FROM Attendee WHERE eventId = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, eventId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Attendee attendee = new Attendee();
                attendee.setAttendeeId(rs.getInt("attendeeId"));
                attendee.setAttendeeName(rs.getString("attendeeName"));
                attendee.setEventId(rs.getInt("eventId"));
                attendees.add(attendee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the database connection
            DatabaseConnection.closeConnection(conn);
        }

        return attendees;
    }

    public static void updateAttendee(Attendee attendee) {
        // Get the database connection
        Connection conn = DatabaseConnection.getConnection();

        try {
            // Update the attendee in the Attendee table
            String query = "UPDATE Attendee SET attendeeName = ? WHERE attendeeId = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, attendee.getAttendeeName());
            stmt.setInt(2, attendee.getAttendeeId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the database connection
            DatabaseConnection.closeConnection(conn);
        }
    }

    public static void removeAttendee(int attendeeId) {
        // Get the database connection
        Connection conn = DatabaseConnection.getConnection();

        try {
            // Delete the attendee from the Attendee table
            String query = "DELETE FROM Attendee WHERE attendeeId = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, attendeeId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the database connection
            DatabaseConnection.closeConnection(conn);
        }
    }
}