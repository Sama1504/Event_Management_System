# Event Management System - Intermediate Level

This project is an Event Management System implemented in Java using Swing for the user interface and MySQL for data persistence. It provides basic functionalities for customers to create and manage events, attendees, and special requests, and for administrators to oversee and manage all events.

## Features

**For Customers:**

*   **Registration and Login:** Customers can register with the system and log in to access their dashboard.
*   **Event Creation:**  Customers can create events by providing details like title, description, type, date, and status.
*   **Attendee Management:**  Customers can add attendees to their events.
*   **Special Requests:** Customers can submit special requests related to their events.
*   **View Event Details:** Customers can view the details of their created events. 

**For Administrators:**

*   **Login:**  Administrators can log in to access their dashboard.
*   **View All Events:** Administrators can view a list of all events created by all customers.
*   **Approve/Reject Events:** Administrators have the ability to approve or reject pending events.
*   **View Event Details:** Administrators can view detailed information for any event.
*   **Refresh Event List:** The administrator dashboard can be refreshed to get the latest event data.

## Technologies Used

*   **Java:** The core programming language.
*   **Swing:** Used for building the graphical user interface (GUI).
*   **MySQL:** The database for storing user data, event information, attendees, and special requests.
*   **JDBC:** Used for connecting to the MySQL database and performing database operations.

## Database Schema

The system uses the following tables in the MySQL database:

*   **User:** Stores user information (userId, username, password, email, userType).
*   **Customer:** Stores customer-specific information (customerId, customerName, userId).
*   **Administrator:** Stores administrator-specific information (adminId, adminName, userId).
*   **Event:**  Stores event details (eventId, eventTitle, eventDescription, eventType, eventDate, eventStatus, customerId).
*   **Attendee:**  Stores attendee information (attendeeId, attendeeName, eventId).
*   **SpecialRequest:** Stores special requests related to events (requestId, requestDescription, eventId).
*    +-------+      1      +--------------+
     | User  |------<>-----| Customer    |
     +-------+             +-------------+
       |                        |
       |                        |
       1                        1
       |                        |
  +---------------+        +-------+
  | Administrator |        | Event |
  +---------------+        +-------+
                               |
                               |
                     1         N
                     |         |
           +----------+  +----------------+
           | Attendee |  | SpecialRequest |
           +----------+  +----------------+

## Getting Started

1.  **Database Setup:**
    *   Create a database named "event\_management\_system" in your MySQL server.
    *   Create the tables listed in the "Database Schema" section by running the provided SQL scripts (You can find these scripts in the project code comments).

2.  **Project Setup:**
    *   Import the project into Eclipse (or your preferred Java IDE).
    *   Configure the build path to include the JUnit and MySQL Connector/J (JDBC driver) libraries. You can download the Connector/J JAR file from the MySQL website: [https://dev.mysql.com/downloads/connector/j/](https://dev.mysql.com/downloads/connector/j/)
    *   Update the database connection details (URL, username, password) in `DatabaseConnection.java` to match your MySQL setup.

3.  **Running the Application:**
    *   Run the `WelcomePageUI.java` file as a Java application.
    *   Register as a customer or administrator, log in, and interact with the system's functionalities.
  
4.  **Demo of the Application:**
    *![image](https://github.com/Sama1504/Event_Management_System/assets/96735639/d742b35c-82e8-4809-936e-11ddb495afe8)
    
    * ![image](https://github.com/Sama1504/Event_Management_System/assets/96735639/3823554d-9f14-460b-a72c-d9144f9b4d69)
    
    * https://github.com/Sama1504/Event_Management_System/assets/96735639/3e4c23a9-488c-4a03-835c-8b4ac08339cf

    * https://github.com/Sama1504/Event_Management_System/assets/96735639/1e78c42e-5713-4943-88cd-983a96a3eac3

## Future Enhancements

This project can be further enhanced with the following features:

*   **Password Hashing:**  Implement password hashing for security.
*   **Input Validation:** Add robust input validation for all forms.
*   **Advanced UI Features:** Implement a visual calendar, user profile management, email notifications, and improved table formatting.
*   **Event Capacity:**  Add event capacity limits and handle registration accordingly.
*   **Search and Filtering:** Implement search and filtering options for events and attendees.
*   **Reporting and Analytics:**  Generate reports on event attendance, revenue, and customer behavior.

## Contributing

Contributions are welcome! If you'd like to contribute to this project, please fork the repository and submit a pull request.
