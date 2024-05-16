package com.eventmanagement;

public class Customer extends User {
    private int customerId;
    private String customerName;

    // Constructors
    public Customer() {
        // Default constructor
    }

    public Customer(String username, String password, String email) {
        super(username, password, email, "customer"); // Set userType as "customer"
    }

    public Customer(int customerId, String customerName, String username, String password, String email) {
        this(username, password, email);
        this.customerId = customerId;
        this.customerName = customerName;
    }

    // Getters and Setters
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    // Other methods (if needed)
    @Override
    public String toString() {
        return "Customer{" +
               "customerId=" + customerId +
               ", customerName='" + customerName + '\'' +
               ", username='" + getUsername() + '\'' + // Access inherited attributes
               ", email='" + getEmail() + '\'' +
               ", userType='" + getUserType() + '\'' + 
               '}';
    }
}