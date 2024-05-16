package com.eventmanagement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.List;

public class AdminDashboardUI extends JFrame implements ActionListener {
    private Administrator admin;
    private JTable eventTable;
    private JButton approveButton, rejectButton, viewDetailsButton, refreshButton;
    private DefaultTableModel eventTableModel;

    public AdminDashboardUI(Administrator admin) {
        this.admin = admin;
        setTitle("Admin Dashboard - " + admin.getAdminName());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());

        // Event Table
        eventTable = new JTable();
        eventTableModel = new DefaultTableModel();
        eventTable.setModel(eventTableModel);
        eventTable.setAutoCreateRowSorter(true);
        JScrollPane eventScrollPane = new JScrollPane(eventTable);
        mainPanel.add(eventScrollPane, BorderLayout.CENTER);
        populateEventTable();

        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        approveButton = new JButton("Approve");
        rejectButton = new JButton("Reject");
        viewDetailsButton = new JButton("View Details");
        refreshButton = new JButton("Refresh");

        approveButton.addActionListener(this);
        rejectButton.addActionListener(this);
        viewDetailsButton.addActionListener(this);
        refreshButton.addActionListener(this);

        buttonPanel.add(approveButton);
        buttonPanel.add(rejectButton);
        buttonPanel.add(viewDetailsButton);
        buttonPanel.add(refreshButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(mainPanel);
    }

    private void populateEventTable() {
        eventTableModel.setRowCount(0); 

        List<Event> events = EventManager.getAllEvents();

        eventTableModel.addColumn("Event ID");
        eventTableModel.addColumn("Title");
        eventTableModel.addColumn("Description");
        eventTableModel.addColumn("Type");
        eventTableModel.addColumn("Date");
        eventTableModel.addColumn("Status");
        eventTableModel.addColumn("Customer ID");
        eventTableModel.addColumn("Customer Name");

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Date formatter

        for (Event event : events) {
            Customer customer = CustomerManager.getCustomerById(event.getCustomerId());
            eventTableModel.addRow(new Object[]{
                event.getEventId(),
                event.getEventTitle(),
                event.getEventDescription(),
                event.getEventType(),
                dateFormat.format(event.getEventDate()), // Format the date
                event.getEventStatus(),
                event.getCustomerId(),
                customer.getCustomerName()
            });
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == approveButton) {
            approveEvent();
        } else if (e.getSource() == rejectButton) {
            rejectEvent();
        } else if (e.getSource() == viewDetailsButton) {
            viewEventDetails();
        } else if (e.getSource() == refreshButton) {
            populateEventTable(); // Refresh the table data
        }
    }

    private void approveEvent() {
        int selectedRow = eventTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select an event to approve.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int eventId = (int) eventTable.getValueAt(selectedRow, 0);
        Event event = getEventById(eventId); 
        if (event != null) {
            event.setEventStatus("Approved");
            EventManager.updateEvent(event);
            populateEventTable(); // Refresh the table after updating
        }
    }

    private void rejectEvent() {
        int selectedRow = eventTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select an event to reject.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int eventId = (int) eventTable.getValueAt(selectedRow, 0);
        Event event = getEventById(eventId);
        if (event != null) {
            event.setEventStatus("Rejected");
            EventManager.updateEvent(event);
            populateEventTable(); 
        }
    }

    private void viewEventDetails() {
        int selectedRow = eventTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select an event to view details.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int eventId = (int) eventTable.getValueAt(selectedRow, 0);
        Event event = getEventById(eventId);
        if (event != null) {
            // Create and display a dialog with event details
            JOptionPane.showMessageDialog(this, 
                    "Event Details:\n" +
                    "Title: " + event.getEventTitle() + "\n" +
                    "Description: " + event.getEventDescription() + "\n" +
                    "Type: " + event.getEventType() + "\n" +
                    "Date: " + event.getEventDate() + "\n" +
                    "Status: " + event.getEventStatus() + "\n" +
                    "Customer ID: " + event.getCustomerId(), 
                    "Event Details", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // Helper method to get Event by ID (You might need to implement this in EventManager)
    private Event getEventById(int eventId) {
        for (Event event : EventManager.getAllEvents()) {
            if (event.getEventId() == eventId) {
                return event;
            }
        }
        return null;
    }

    // ... (your main method for testing) ...
}