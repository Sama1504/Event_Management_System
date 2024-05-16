package com.eventmanagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrationFormUI extends JFrame implements ActionListener {
    private JTextField usernameField, emailField, nameField;
    private JPasswordField passwordField;
    private JRadioButton customerRadioButton, adminRadioButton;
    private JButton registerButton;

    public RegistrationFormUI() {
        setTitle("Registration");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        // Use GridBagLayout for more flexible layout
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Add padding

        // Username
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(new JLabel("Username:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        usernameField = new JTextField();
        panel.add(usernameField, gbc);

        // Email
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(new JLabel("Email:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        emailField = new JTextField();
        panel.add(emailField, gbc);

        // Password
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(new JLabel("Password:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        passwordField = new JPasswordField();
        panel.add(passwordField, gbc);

        // Name
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(new JLabel("Name:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        nameField = new JTextField();
        panel.add(nameField, gbc);

        // User Type
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(new JLabel("User Type:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JPanel userTypePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        customerRadioButton = new JRadioButton("Customer");
        customerRadioButton.setSelected(true); // Set Customer as default
        adminRadioButton = new JRadioButton("Administrator");
        ButtonGroup userTypeGroup = new ButtonGroup();
        userTypeGroup.add(customerRadioButton);
        userTypeGroup.add(adminRadioButton);
        userTypePanel.add(customerRadioButton);
        userTypePanel.add(adminRadioButton);
        panel.add(userTypePanel, gbc);

        // Register Button
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.EAST; // Align button to the right
        registerButton = new JButton("Register");
        registerButton.addActionListener(this);
        panel.add(registerButton, gbc);

        add(panel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == registerButton) {
            String username = usernameField.getText();
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());
            String name = nameField.getText();
            String userType = customerRadioButton.isSelected() ? "Customer" : "Administrator";

            // Input Validation (Example)
            if (username.isEmpty() || email.isEmpty() || password.isEmpty() || name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Create a User object
            User user;
            if (userType.equals("Customer")) {
                user = new Customer();
                ((Customer) user).setCustomerName(name);
            } else {
                user = new Administrator();
                ((Administrator) user).setAdminName(name);
            }
            user.setUsername(username);
            user.setPassword(password);
            user.setEmail(email);
            user.setUserType(userType);

            // Call the UserRegistration.registerUser() method
            int generatedId = UserRegistration.registerUser(user);

            if (generatedId != -1) {
                // Registration successful
                if (user instanceof Customer) {
                    JOptionPane.showMessageDialog(this, "Registration successful! Your Customer ID is: " + generatedId);
                } else {
                    JOptionPane.showMessageDialog(this, "Registration successful! Your Admin ID is: " + generatedId);
                }

                // Clear the form 
                usernameField.setText("");
                emailField.setText("");
                passwordField.setText("");
                nameField.setText("");
                customerRadioButton.setSelected(true); 
            } else {
                // Handle registration error
                JOptionPane.showMessageDialog(this, "Registration failed. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}