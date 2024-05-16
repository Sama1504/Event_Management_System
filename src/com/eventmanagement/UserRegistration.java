package com.eventmanagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class UserRegistration {
    public static int registerUser(User user) {
        int generatedId = -1; // To store the generated ID (customerId or adminId)

        // Establish a database connection
        Connection conn = DatabaseConnection.getConnection();

        try {
            // Insert a new user into the User table
            String query = "INSERT INTO User (username, password, email, userType) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS); 
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getUserType());
            stmt.executeUpdate();

            // Get the generated userId
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            int userId = -1;
            if (generatedKeys.next()) {
                userId = generatedKeys.getInt(1);
            }
            user.setUserId(userId); // Set the userId in the User object

            // Insert into the appropriate table based on userType
            if (user instanceof Customer) {
                Customer customer = (Customer) user;
                query = "INSERT INTO Customer (customerName, userId) VALUES (?, ?)";
                stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                stmt.setString(1, customer.getCustomerName());
                stmt.setInt(2, userId); 
                stmt.executeUpdate();

                // Get the generated customerId
                generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    generatedId = generatedKeys.getInt(1);
                }
            } else if (user instanceof Administrator) {
                Administrator admin = (Administrator) user;
                query = "INSERT INTO Administrator (adminName, userId) VALUES (?, ?)";
                stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                stmt.setString(1, admin.getAdminName());
                stmt.setInt(2, userId); 
                stmt.executeUpdate();

                // Get the generated adminId
                generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    generatedId = generatedKeys.getInt(1);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the database connection
            DatabaseConnection.closeConnection(conn);
        }

        return generatedId; // Return the generated customerId or adminId
    }
}