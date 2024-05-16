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

## License

This project is licensed under the MIT License.
