package com.eventmanagement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CustomerDashboardUI extends JFrame implements ActionListener {
    private Customer customer;
    private int customerId;
    private JTabbedPane tabbedPane;
    private JPanel eventPanel, attendeePanel, requestPanel;

    private JTextField eventTitleField, attendeeNameField, eventDateField;
    private JTextArea eventDescriptionArea, requestDescriptionArea;
    private JComboBox<String> eventTypeCombo;
    private JTable eventTable, attendeeTable, requestTable;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public CustomerDashboardUI(Customer customer, int customerId) {
        this.customer = customer;
        this.customerId = customerId;
        setTitle("Customer Dashboard - " + customer.getCustomerName());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600); // Adjusted size
        setLocationRelativeTo(null);

        tabbedPane = new JTabbedPane();

        // Event Panel
        eventPanel = createEventPanel();
        tabbedPane.addTab("Events", eventPanel);

        // Attendee Panel
        attendeePanel = createAttendeePanel();
        tabbedPane.addTab("Attendees", attendeePanel);

        // Request Panel
        requestPanel = createRequestPanel();
        tabbedPane.addTab("Special Requests", requestPanel);

        add(tabbedPane, BorderLayout.CENTER);

        // Event table listener for attendee and request updates
        eventTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = eventTable.getSelectedRow();
                if (selectedRow != -1) {
                    int selectedEventId = (int) eventTable.getValueAt(selectedRow, 0);
                    populateAttendeeTable(selectedEventId);
                    populateRequestTable(selectedEventId);
                } else {
                    // Clear attendee and request tables when no event is selected
                    populateAttendeeTable(-1); // Pass -1 to indicate no event selected
                    populateRequestTable(-1);
                }
            }
        });
    }

    // --- Panel Creation Methods ---

    private JPanel createEventPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // Event Form (using GridBagLayout for better control)
        JPanel eventFormPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Padding

        // Title
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        eventFormPanel.add(new JLabel("Event Title:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 2; // Span 2 columns
        eventTitleField = new JTextField();
        eventFormPanel.add(eventTitleField, gbc);

        // Description
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        eventFormPanel.add(new JLabel("Event Description:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 2;
        eventDescriptionArea = new JTextArea(4, 20); // Set rows and columns
        eventFormPanel.add(new JScrollPane(eventDescriptionArea), gbc);

        // Type
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridwidth = 1;
        eventFormPanel.add(new JLabel("Event Type:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        eventTypeCombo = new JComboBox<>(new String[]{"Birthday", "Anniversary", "Conference", "Meeting", "Other"});
        eventFormPanel.add(eventTypeCombo, gbc);

        // Date
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        eventFormPanel.add(new JLabel("Event Date (yyyy-MM-dd):"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        eventDateField = new JTextField();
        eventFormPanel.add(eventDateField, gbc);

        // Create Event Button
        gbc.gridx = 2; // Place button in the last column
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.NONE; // Don't stretch the button
        JButton createEventButton = new JButton("Create Event");
        createEventButton.addActionListener(this);
        eventFormPanel.add(createEventButton, gbc);

        panel.add(eventFormPanel, BorderLayout.NORTH);

        // Event Table 
        eventTable = new JTable();
        eventTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Allow only single row selection
        JScrollPane eventScrollPane = new JScrollPane(eventTable);
        panel.add(eventScrollPane, BorderLayout.CENTER);
        populateEventTable();

        // Add sorting to the event table
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>((DefaultTableModel) eventTable.getModel());
        eventTable.setRowSorter(sorter);

        return panel;
    }

    private JPanel createAttendeePanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // Attendee Form
        JPanel attendeeFormPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        attendeeFormPanel.add(new JLabel("Attendee Name:"));
        attendeeNameField = new JTextField();
        attendeeFormPanel.add(attendeeNameField);
        JButton addAttendeeButton = new JButton("Add Attendee");
        addAttendeeButton.addActionListener(this);
        attendeeFormPanel.add(new JLabel());
        attendeeFormPanel.add(addAttendeeButton);
        panel.add(attendeeFormPanel, BorderLayout.NORTH);

        // Attendee Table
        attendeeTable = new JTable();
        JScrollPane attendeeScrollPane = new JScrollPane(attendeeTable);
        panel.add(attendeeScrollPane, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createRequestPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // Request Form
        JPanel requestFormPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        requestFormPanel.add(new JLabel("Request Description:"));
        requestDescriptionArea = new JTextArea(4, 20);
        requestFormPanel.add(new JScrollPane(requestDescriptionArea));
        JButton submitRequestButton = new JButton("Submit Request");
        submitRequestButton.addActionListener(this);
        requestFormPanel.add(new JLabel());
        requestFormPanel.add(submitRequestButton);
        panel.add(requestFormPanel, BorderLayout.NORTH);

        // Request Table
        requestTable = new JTable();
        JScrollPane requestScrollPane = new JScrollPane(requestTable);
        panel.add(requestScrollPane, BorderLayout.CENTER);

        return panel;
    }

    // --- Data Handling Methods ---

    private void createEvent() {
        String eventTitle = eventTitleField.getText();
        String eventDescription = eventDescriptionArea.getText();
        String eventType = eventTypeCombo.getItemAt(eventTypeCombo.getSelectedIndex());
        String eventDateString = eventDateField.getText();

        if (eventTitle.isEmpty() || eventDescription.isEmpty() || eventDateString.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Date eventDate;
        try {
            eventDate = dateFormat.parse(eventDateString);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Invalid date format. Use yyyy-MM-dd.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Event newEvent = new Event(eventTitle, eventDescription, eventType, eventDate, "Pending", this.customerId); // Set default status to pending
        EventManager.createEvent(newEvent, this.customerId);
        populateEventTable();

        // Clear form fields
        eventTitleField.setText("");
        eventDescriptionArea.setText("");
        eventDateField.setText("");
    }

    private void addAttendee() {
        String attendeeName = attendeeNameField.getText();

        int selectedRow = eventTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Select an event to add attendees.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int eventId = (int) eventTable.getValueAt(selectedRow, 0); // Get Event ID from the selected row

        if (attendeeName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter attendee name.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Attendee attendee = new Attendee(attendeeName, eventId);
        AttendeeManager.addAttendee(attendee);
        populateAttendeeTable(eventId);
        attendeeNameField.setText("");
    }

    private void submitRequest() {
        String requestDescription = requestDescriptionArea.getText();

        int selectedRow = eventTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Select an event to submit a request.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int eventId = (int) eventTable.getValueAt(selectedRow, 0);

        if (requestDescription.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter request description.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        SpecialRequest request = new SpecialRequest(requestDescription, eventId);
        RequestManager.submitRequest(request);
        populateRequestTable(eventId);
        requestDescriptionArea.setText("");
    }

    private void populateEventTable() {
        List<Event> events = EventManager.getEventsByCustomerId(customerId);

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Event ID");
        model.addColumn("Title");
        model.addColumn("Description");
        model.addColumn("Type");
        model.addColumn("Date");
        model.addColumn("Status");

        for (Event event : events) {
            model.addRow(new Object[]{
                event.getEventId(),
                event.getEventTitle(),
                event.getEventDescription(),
                event.getEventType(),
                dateFormat.format(event.getEventDate()),
                event.getEventStatus()
            });
        }
        eventTable.setModel(model);
    }

    private void populateAttendeeTable(int eventId) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Attendee ID");
        model.addColumn("Attendee Name");

        if (eventId != -1) { // Only populate if an event is selected
            List<Attendee> attendees = AttendeeManager.getAttendeesByEventId(eventId);
            for (Attendee attendee : attendees) {
                model.addRow(new Object[]{attendee.getAttendeeId(), attendee.getAttendeeName()});
            }
        }
        attendeeTable.setModel(model);
    }

    private void populateRequestTable(int eventId) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Request ID");
        model.addColumn("Description");

        if (eventId != -1) { // Only populate if an event is selected
            List<SpecialRequest> requests = RequestManager.getRequestsByEventId(eventId);
            for (SpecialRequest request : requests) {
                model.addRow(new Object[]{request.getRequestId(), request.getRequestDescription()});
            }
        }

        requestTable.setModel(model);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton button = (JButton) e.getSource();
            String buttonText = button.getText();

            switch (buttonText) {
                case "Create Event":
                    createEvent();
                    break;
                case "Add Attendee":
                    addAttendee();
                    break;
                case "Submit Request":
                    submitRequest();
                    break;
            }
        }
    }
}