package com.eventmanagement;

public class Administrator extends User {
    private int adminId;
    private String adminName;

    // Constructors
    public Administrator() {
        // Default constructor
    }

    public Administrator(String username, String password, String email) {
        super(username, password, email, "admin"); // Set userType as "admin"
    }

    public Administrator(int adminId, String adminName, String username, String password, String email) {
        this(username, password, email);
        this.adminId = adminId;
        this.adminName = adminName;
    }

    // Getters and Setters
    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    // Other methods (if needed)
    @Override
    public String toString() {
        return "Administrator{" +
               "adminId=" + adminId +
               ", adminName='" + adminName + '\'' +
               ", username='" + getUsername() + '\'' +
               ", email='" + getEmail() + '\'' +
               ", userType='" + getUserType() + '\'' +
               '}';
    }
}