package com.eventmanagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerManager {
	public static Customer getCustomerById(int customerId) {
        Customer customer = null;

        // Get the database connection
        Connection conn = DatabaseConnection.getConnection();

        try {
            // Retrieve the customer from the Customer table based on the customerId
            String query = "SELECT * FROM Customer WHERE customerId = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, customerId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                customer = new Customer();
                customer.setCustomerId(rs.getInt("customerId"));
                customer.setCustomerName(rs.getString("customerName"));
                customer.setUserId(rs.getInt("userId"));

                // Retrieve user details from the User table
                query = "SELECT * FROM User WHERE userId = ?";
                stmt = conn.prepareStatement(query);
                stmt.setInt(1, customer.getUserId());
                ResultSet userRS = stmt.executeQuery();

                if (userRS.next()) {
                    customer.setUsername(userRS.getString("username"));
                    customer.setPassword(userRS.getString("password"));
                    customer.setEmail(userRS.getString("email"));
                    customer.setUserType(userRS.getString("userType"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the database connection
            DatabaseConnection.closeConnection(conn);
        }

        return customer;
    }
}
