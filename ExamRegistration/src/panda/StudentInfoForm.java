package panda;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class StudentInfoForm {
    private JFrame frame;
    private JTextField txtStudentId;
    private JTextArea txtAreaResult;
    private JLabel lblNewLabel;
    private JLabel lblNewLabel_1;

    public StudentInfoForm() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Student Info");
        frame.setBounds(100, 100, 608, 438);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblStudentId = new JLabel("Enter Student ID:");
        lblStudentId.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblStudentId.setBounds(20, 83, 150, 25);
        frame.getContentPane().add(lblStudentId);

        txtStudentId = new JTextField();
        txtStudentId.setBounds(214, 85, 180, 23);
        frame.getContentPane().add(txtStudentId);
        txtStudentId.setColumns(10);

        JButton btnSearch = new JButton("Search");
        btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnSearch.setBounds(470, 83, 80, 25);
        btnSearch.addActionListener(e -> searchStudent());
        frame.getContentPane().add(btnSearch);

        txtAreaResult = new JTextArea();
        txtAreaResult.setBounds(110, 136, 350, 150);
        txtAreaResult.setEditable(false);
        frame.getContentPane().add(txtAreaResult);

        JButton btnBack = new JButton("<");
        btnBack.setBounds(20, 347, 80, 25);
        btnBack.addActionListener(e -> {
            frame.dispose(); // Close current frame
            main window = new main(); // Open main window
            window.setVisible(true);
        });
        frame.getContentPane().add(btnBack);
        
        lblNewLabel = new JLabel("STUDENT INFO");
        lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 22));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(145, 10, 294, 41);
        frame.getContentPane().add(lblNewLabel);
        
        lblNewLabel_1 = new JLabel("New label");
        lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\Kasif\\Downloads\\pngtree-countdown-to-the-college-entrance-examination-image_790187.jpg"));
        lblNewLabel_1.setBounds(0, 0, 594, 391);
        frame.getContentPane().add(lblNewLabel_1);
    }

    private Connection connect() {
        try {
            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/exam", "postgres", "root");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Database connection failed.");
            e.printStackTrace();
            return null;
        }
    }

    private void searchStudent() {
        String studentId = txtStudentId.getText().trim();
        if (studentId.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please enter a Student ID.");
            return;
        }

        try (Connection connection = connect()) {
            String sql = "SELECT * FROM stu WHERE roll_no = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, studentId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String studentInfo = "Name: " + rs.getString("name") +
                        "\nRoll No: " + rs.getString("roll_no") +
                        "\nPhone: " + rs.getString("phone") +
                        "\nCourse: " + rs.getString("course") +
                        "\nFees: " + rs.getString("fees") +
                        "\nSubjects: " + rs.getString("subjects");
                txtAreaResult.setText(studentInfo);
            } else {
                txtAreaResult.setText("Student not found.");
            }
            rs.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Error fetching student information.");
            e.printStackTrace();
        }
    }

    public void setVisible(boolean visible) {
        frame.setVisible(visible);
    }
}
