import workers.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.*;

public class LoginPage extends JFrame implements ActionListener {
    private JLabel titleLabel, empIdLabel, passwordLabel;
    private JTextField empIdField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginPage() {
        super("Login - Payroll System");
        this.setSize(400, 300);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.getContentPane().setBackground(new Color(240, 248, 255));
        this.setIconImage(new ImageIcon("EPM.png").getImage());
        // Title
        titleLabel = new JLabel("Employee Payroll Login", SwingConstants.CENTER);
        titleLabel.setBounds(50, 20, 300, 30);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        this.add(titleLabel);

        // Employee ID
        empIdLabel = new JLabel("Employee ID:");
        empIdLabel.setBounds(50, 80, 100, 25);
        this.add(empIdLabel);

        empIdField = new JTextField();
        empIdField.setBounds(150, 80, 200, 25);
        this.add(empIdField);

        // Password
        passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 120, 100, 25);
        this.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(150, 120, 200, 25);
        this.add(passwordField);

        // Login Button
        loginButton = new JButton("Login");
        loginButton.setBounds(150, 180, 100, 30);
        loginButton.setBackground(new Color(70, 130, 180));
        loginButton.setForeground(Color.WHITE);
        loginButton.addActionListener(this);
        this.add(loginButton);
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String empId = empIdField.getText().trim();
            String password = new String(passwordField.getPassword());

            EMP.EmployeeData employee = EMP.validateLogin(empId, password);

            if (employee != null) {
                // Login done
                new DashboardPage(
                        employee.empId,
                        employee.name,
                        employee.salary,
                        employee.deduction);
                this.dispose(); // login window shutdown
            } else {
                JOptionPane.showMessageDialog(this,
                        "Invalid Employee ID or Password!",
                        "Login Failed",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
