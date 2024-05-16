package com.eventmanagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomePageUI extends JFrame implements ActionListener {
    private static final String TITLE = "Event Management System";
    private static final int WIDTH = 500;  // Increased width
    private static final int HEIGHT = 300;
    private static final Font BUTTON_FONT = new Font("Arial", Font.BOLD, 16);
    private static final Color BACKGROUND_COLOR = new Color(240, 240, 240); // Light gray
    private static final Color BUTTON_COLOR = new Color(51, 153, 255); // Light blue

    private JButton loginButton;
    private JButton registerButton;

    public WelcomePageUI() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle(TITLE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        getContentPane().setBackground(BACKGROUND_COLOR); // Set background color

        // Use a GridBagLayout for more control over component placement
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Add spacing

        // Welcome Message
        JLabel welcomeLabel = new JLabel("Welcome to the Event Management System!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Set a larger font
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Span two columns
        gbc.anchor = GridBagConstraints.CENTER; // Center the label
        mainPanel.add(welcomeLabel, gbc);

        // Login Button
        loginButton = new JButton("Login");
        loginButton.setFont(BUTTON_FONT); 
        loginButton.setBackground(BUTTON_COLOR); 
        loginButton.setForeground(Color.WHITE); // White text
        loginButton.addActionListener(this);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL; // Fill horizontal space
        mainPanel.add(loginButton, gbc);

        // Register Button
        registerButton = new JButton("Register");
        registerButton.setFont(BUTTON_FONT);
        registerButton.setBackground(BUTTON_COLOR); 
        registerButton.setForeground(Color.WHITE); // White text
        registerButton.addActionListener(this);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL; // Fill horizontal space
        mainPanel.add(registerButton, gbc);

        add(mainPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            openLoginForm();
        } else if (e.getSource() == registerButton) {
            openRegistrationForm();
        }
    }

    private void openLoginForm() {
        LoginFormUI loginForm = new LoginFormUI();
        loginForm.setVisible(true);
    }

    private void openRegistrationForm() {
        RegistrationFormUI registrationForm = new RegistrationFormUI();
        registrationForm.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            WelcomePageUI mainFrame = new WelcomePageUI();
            mainFrame.setVisible(true);
        });
    }
}