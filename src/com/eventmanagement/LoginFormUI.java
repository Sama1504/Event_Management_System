package com.eventmanagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The login form UI for the Event Management System.
 */
public class LoginFormUI extends JFrame implements ActionListener {
    private static final String TITLE = "Login";
    private static final int WIDTH = 300;
    private static final int HEIGHT = 200;

    private JTextField userNameTextField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginFormUI() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle(TITLE);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(10, 10, 10, 10);
        mainPanel.add(new JLabel("Username:"), constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        userNameTextField = new JTextField(20);
        mainPanel.add(userNameTextField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        mainPanel.add(new JLabel("Password:"), constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        passwordField = new JPasswordField(20);
        mainPanel.add(passwordField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        loginButton = new JButton("Login");
        loginButton.addActionListener(this);
        mainPanel.add(loginButton, constraints);

        add(mainPanel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String username = userNameTextField.getText();
            char[] passwordChars = passwordField.getPassword();

            // Validate user input
            if (username.isEmpty() || passwordChars.length == 0) {
                JOptionPane.showMessageDialog(this, "Please enter a username and password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Call the UserAuthentication.authenticateUser() method
            User user = UserAuthentication.authenticateUser(username, new String(passwordChars));

            if (user != null) {
                // Successful login
                handleSuccessfulLogin(user);

                // Clear the form after successful login
                userNameTextField.setText("");
                passwordField.setText("");
            } else {
                // Failed login
                JOptionPane.showMessageDialog(this, "Invalid username or password. Please try again.", "Login Failed", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void handleSuccessfulLogin(User user) {
        if (user instanceof Customer) {
            // Get Customer ID
            int customerId = ((Customer) user).getCustomerId();

            // Open the customer dashboard
            new CustomerDashboardUI((Customer) user, customerId).setVisible(true);
        } else if (user instanceof Administrator) {
            // Open the administrator dashboard
            new AdminDashboardUI((Administrator) user).setVisible(true);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginFormUI loginForm = new LoginFormUI();
            loginForm.setVisible(true);
        });
    }
}