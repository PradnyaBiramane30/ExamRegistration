package panda;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class StudentDetailsForm extends JFrame {
    private JTextField txtName;
    private JTextField txtRollNo;
    private JTextField txtPhone;
    private JTextField txtCourse;
    private JTextField txtFees;
    private JList<String> subjectList;
    private String email;

    public StudentDetailsForm(String email) {
        this.email = email;
        setTitle("Student Details Form");
        setBounds(100, 100, 600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        
        JLabel lblNewLabel = new JLabel("EXAM REGISTRATION");
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD | Font.ITALIC, 24));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(147, 38, 284, 37);
        getContentPane().add(lblNewLabel);

        JLabel background = new JLabel(new ImageIcon("C:\\Users\\Kasif\\Downloads\\bgp (1).jpg"));
        background.setBounds(0, 0, 586, 463);
        getContentPane().add(background);
        background.setLayout(null);

        JLabel lblName = new JLabel("Name:");
        lblName.setFont(new Font("Times New Roman", Font.ITALIC, 15));
        lblName.setBounds(50, 120, 100, 30);
        lblName.setForeground(Color.WHITE);
        background.add(lblName);

        txtName = new JTextField();
        txtName.setBounds(150, 120, 200, 30);
        background.add(txtName);

        JLabel lblRollNo = new JLabel("Roll No:");
        lblRollNo.setFont(new Font("Times New Roman", Font.ITALIC, 15));
        lblRollNo.setBounds(50, 170, 100, 30);
        lblRollNo.setForeground(Color.WHITE);
        background.add(lblRollNo);

        txtRollNo = new JTextField();
        txtRollNo.setBounds(150, 170, 200, 30);
        background.add(txtRollNo);

        JLabel lblPhone = new JLabel("Phone:");
        lblPhone.setFont(new Font("Times New Roman", Font.ITALIC, 15));
        lblPhone.setBounds(50, 220, 100, 30);
        lblPhone.setForeground(Color.WHITE);
        background.add(lblPhone);

        txtPhone = new JTextField();
        txtPhone.setBounds(150, 220, 200, 30);
        background.add(txtPhone);

        JLabel lblCourse = new JLabel("Course:");
        lblCourse.setBounds(50, 270, 100, 30);
        lblCourse.setForeground(Color.WHITE);
        background.add(lblCourse);

        txtCourse = new JTextField();
        txtCourse.setBounds(150, 270, 200, 30);
        background.add(txtCourse);

        JLabel lblFees = new JLabel("Fees:");
        lblFees.setBounds(50, 320, 100, 30);
        lblFees.setForeground(Color.WHITE);
        background.add(lblFees);

        txtFees = new JTextField();
        txtFees.setBounds(150, 320, 200, 30);
        background.add(txtFees);

        JLabel lblSubjects = new JLabel("Select Subjects:");
        lblSubjects.setBounds(400, 120, 120, 30);
        lblSubjects.setForeground(Color.WHITE);
        background.add(lblSubjects);

        String[] subjects = {"Mathematics", "Physics", "Chemistry", "Biology", "English", "Computer Science"};
        subjectList = new JList<>(subjects);
        JScrollPane scrollPane = new JScrollPane(subjectList);
        scrollPane.setBounds(400, 150, 150, 100);
        background.add(scrollPane);

        JButton btnSubmit = new JButton("Submit");
        btnSubmit.setBounds(150, 370, 100, 30);
        btnSubmit.addActionListener(e -> {
            String name = txtName.getText();
            String rollNo = txtRollNo.getText();
            String phone = txtPhone.getText();
            String course = txtCourse.getText();
            String fees = txtFees.getText();
            List<String> selectedSubjects = subjectList.getSelectedValuesList();
            submitDetails(name, rollNo, phone, course, fees, selectedSubjects);
        });
        background.add(btnSubmit);

        // Back button
        JButton btnBack = new JButton("<");
        btnBack.setBounds(20, 420, 80, 25);
        btnBack.addActionListener(e -> {
            dispose(); // Close current frame
            main window = new main(); // Open main window
            window.setVisible(true);
        });
        background.add(btnBack);
    }

    private void submitDetails(String name, String rollNo, String phone, String course, String fees, List<String> subjects) {
        // Simple validation
        if (name.isEmpty() || rollNo.isEmpty() || phone.isEmpty() || course.isEmpty() || fees.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill out all fields.");
            return;
        }

        // Try to parse fees as a numeric value
        BigDecimal feesValue;
        try {
            feesValue = new BigDecimal(fees);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid numeric value for fees.");
            return;
        }

        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/exam", "postgres", "root");

            // Proceed to insert student details directly without checking email
            String sql = "INSERT INTO stu(name, roll_no, phone, course, fees, subjects) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, name); // First placeholder
            pstmt.setString(2, rollNo); // Second placeholder
            pstmt.setString(3, phone); // Third placeholder
            pstmt.setString(4, course); // Fourth placeholder
            pstmt.setBigDecimal(5, feesValue); // Fifth placeholder
            pstmt.setString(6, String.join(", ", subjects)); // Sixth placeholder
            pstmt.executeUpdate();
            
            JOptionPane.showMessageDialog(this, "Details Submitted Successfully");

            // Cleanup
            pstmt.close();
            connection.close();
            dispose();

        } catch (Exception e) {
            // Show detailed error message
            JOptionPane.showMessageDialog(this, "Failed to Submit Details: " + e.getMessage());
            e.printStackTrace(); // Print stack trace for debugging
        }
    }
}