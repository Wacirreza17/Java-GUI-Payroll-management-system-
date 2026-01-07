import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DashboardPage extends JFrame implements ActionListener {
    private JLabel greetingLabel;
    private JButton logoutButton, calculateButton, saveButton;
    private JTextArea detailsArea;

    private String empId;
    private String name;
    private double salary;
    private double deduction;

    public DashboardPage(String empId, String name, double salary, double deduction) {
        super("Employee Dashboard - Payroll System");
        this.empId = empId;
        this.name = name;
        this.salary = salary;
        this.deduction = deduction;

        this.setSize(500, 450);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.getContentPane().setBackground(new Color(255, 250, 240));

        // Greeting the employee message
        greetingLabel = new JLabel("Welcome, " + name + "!");
        greetingLabel.setBounds(50, 20, 400, 30);
        greetingLabel.setFont(new Font("Arial", Font.BOLD, 22));
        greetingLabel.setForeground(new Color(70, 130, 180));
        this.add(greetingLabel);

        detailsArea = new JTextArea();
        detailsArea.setEditable(false);
        detailsArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        detailsArea.setBackground(new Color(245, 245, 245));
        detailsArea.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        updateDetailsArea();

        JScrollPane scrollPane = new JScrollPane(detailsArea);
        scrollPane.setBounds(50, 70, 400, 200);
        this.add(scrollPane);

        calculateButton = new JButton("Calculate Net Salary");
        calculateButton.setBounds(50, 290, 180, 35);
        calculateButton.setBackground(new Color(60, 179, 113));
        calculateButton.setForeground(Color.WHITE);
        calculateButton.setFont(new Font("Arial", Font.BOLD, 14));
        calculateButton.addActionListener(this);
        this.add(calculateButton);

        saveButton = new JButton("Save to File");
        saveButton.setBounds(240, 290, 120, 35);
        saveButton.setBackground(new Color(255, 165, 0));
        saveButton.setForeground(Color.WHITE);
        saveButton.setFont(new Font("Arial", Font.BOLD, 14));
        saveButton.addActionListener(this);
        this.add(saveButton);

        logoutButton = new JButton("Logout");
        logoutButton.setBounds(370, 290, 80, 35);
        logoutButton.setBackground(new Color(220, 20, 60));
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setFont(new Font("Arial", Font.BOLD, 14));
        logoutButton.addActionListener(this);
        this.add(logoutButton);

        JLabel infoLabel = new JLabel("Data is automatically saved to files", SwingConstants.CENTER);
        infoLabel.setBounds(50, 340, 400, 20);
        infoLabel.setForeground(Color.GRAY);
        this.add(infoLabel);

        this.setVisible(true);
    }

    private void updateDetailsArea() {
        double netSalary = salary - deduction;

        String details = "======================================\n" +
                "        EMPLOYEE PAYROLL DETAILS      \n" +
                "======================================\n" +
                String.format(" Employee ID:     %-20s\n", empId) +
                String.format(" Name:            %-20s\n", name) +
                String.format(" \n") +
                String.format(" Gross Salary:    $%-19.2f\n", salary) +
                String.format(" Deductions:      $%-19.2f\n", deduction) +
                String.format(" \n") +
                String.format(" -----------------------------------\n") +
                String.format(" Net Salary:      $%-19.2f\n", netSalary) +
                "======================================";

        detailsArea.setText(details);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == calculateButton) {
            double netSalary = salary - deduction;
            JOptionPane.showMessageDialog(this,
                    String.format("Net Salary Calculation:\n\n" +
                            "Gross Salary: $%.2f\n" +
                            "Deductions:   $%.2f\n" +
                            "-------------------------\n" +
                            "Net Salary:   $%.2f",
                            salary, deduction, netSalary),
                    "Salary Calculation",
                    JOptionPane.INFORMATION_MESSAGE);
        } else if (e.getSource() == saveButton) {
            try {
                String filename = "employee_" + empId + "_details.txt";
                java.io.FileWriter writer = new java.io.FileWriter(filename);
                writer.write(detailsArea.getText());
                writer.close();
                JOptionPane.showMessageDialog(this,
                        "Saved to: " + filename,
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                        "Error: " + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == logoutButton) {
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to logout?",
                    "Confirm Logout",
                    JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                new LoginPage();
                this.dispose();
            }
        }
    }
}
