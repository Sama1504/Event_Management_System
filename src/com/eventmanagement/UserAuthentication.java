package com.eventmanagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserAuthentication {
    public static User authenticateUser(String username, String password) {
        User user = null;

        // Establish a database connection
        Connection conn = DatabaseConnection.getConnection();

        try {
            // Retrieve the user from the User table based on the provided username and password
            String query = "SELECT * FROM User WHERE username = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String userType = rs.getString("userType");
                if (userType.equals("Customer")) {
                    // Create a Customer object
                    user = new Customer();
                    user.setUserId(rs.getInt("userId"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setEmail(rs.getString("email"));
                    user.setUserType(userType);

                    // Retrieve customer-specific details
                    query = "SELECT * FROM Customer WHERE userId = ?";
                    stmt = conn.prepareStatement(query);
                    stmt.setInt(1, user.getUserId());
                    ResultSet customerRS = stmt.executeQuery();

                    if (customerRS.next()) {
                        Customer customer = (Customer) user;
                        customer.setCustomerId(customerRS.getInt("customerId"));
                        customer.setCustomerName(customerRS.getString("customerName"));
                    }
                } else if (userType.equals("Administrator")) {
                    // Create an Administrator object
                    user = new Administrator();
                    user.setUserId(rs.getInt("userId"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setEmail(rs.getString("email"));
                    user.setUserType(userType);

                    // Retrieve administrator-specific details
                    query = "SELECT * FROM Administrator WHERE userId = ?";
                    stmt = conn.prepareStatement(query);
                    stmt.setInt(1, user.getUserId());
                    ResultSet adminRS = stmt.executeQuery();

                    if (adminRS.next()) {
                        Administrator admin = (Administrator) user;
                        admin.setAdminId(adminRS.getInt("adminId"));
                        admin.setAdminName(adminRS.getString("adminName"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the database connection
            DatabaseConnection.closeConnection(conn);
        }

        return user;
    }
}