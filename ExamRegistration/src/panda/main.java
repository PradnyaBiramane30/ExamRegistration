package panda;

import java.awt.*;
import javax.swing.*;
import java.sql.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class main {
    private JFrame frame;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                main window = new main();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public main() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.getContentPane().setBackground(new Color(255, 255, 255));
        frame.setBounds(100, 100, 602, 404);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("STUDENT UPDATE CENTER");
        lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 21));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(128, 30, 324, 34);
        frame.getContentPane().add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("New label");
        lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\Kasif\\Downloads\\PAN1 (1).png"));
        lblNewLabel_1.setBounds(10, 122, 126, 110);
        frame.getContentPane().add(lblNewLabel_1);

        JLabel lblNewLabel_1_1 = new JLabel("New label");
        lblNewLabel_1_1.setIcon(new ImageIcon("C:\\Users\\Kasif\\Downloads\\pan2.jpg"));
        lblNewLabel_1_1.setBounds(220, 122, 126, 110);
        frame.getContentPane().add(lblNewLabel_1_1);

        JLabel lblNewLabel_1_1_1 = new JLabel("New label");
        lblNewLabel_1_1_1.setIcon(new ImageIcon("C:\\Users\\Kasif\\Downloads\\pan3 (1).png"));
        lblNewLabel_1_1_1.setBounds(452, 122, 126, 110);
        frame.getContentPane().add(lblNewLabel_1_1_1);

        JButton btnAddStudent = new JButton("ADD STUDENT");
        btnAddStudent.setBounds(10, 270, 106, 21);
        btnAddStudent.addActionListener(e -> {
            StudentDetailsForm studentForm = new StudentDetailsForm("");
            studentForm.setVisible(true);
        });
        frame.getContentPane().add(btnAddStudent);

        JButton btnRemoveStudent = new JButton("REMOVE STUDENT");
        btnRemoveStudent.setBounds(220, 270, 126, 21);
        JButton btnRemoveStudent1 = new JButton("REMOVE STUDENT");
        btnRemoveStudent1.setBounds(220, 270, 126, 21);
        btnRemoveStudent1.addActionListener(e -> {
            RemoveStudent remover = new RemoveStudent();
            remover.setVisible(true);
        });
        frame.getContentPane().add(btnRemoveStudent1);

        frame.getContentPane().add(btnRemoveStudent1);

        JButton btnStudentInfo = new JButton("STUDENT INFO");
        btnStudentInfo.setBounds(458, 270, 107, 21);
        btnStudentInfo.addActionListener(e -> {
            StudentInfoForm studentInfoForm = new StudentInfoForm();
            studentInfoForm.setVisible(true);
        });
        frame.getContentPane().add(btnStudentInfo);
        
        JLabel lblNewLabel_2 = new JLabel("New label");
        lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\Kasif\\Downloads\\BGPP.jpg"));
        lblNewLabel_2.setBounds(0, 0, 588, 367);
        frame.getContentPane().add(lblNewLabel_2);

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

    private void viewStudentInfo() {
        String email = JOptionPane.showInputDialog(frame, "Enter the email of the student to view:");
        if (email != null && !email.isEmpty()) {
            try (Connection connection = connect()) {
                String sql = "SELECT * FROM stu WHERE email = ?";
                PreparedStatement pstmt = connection.prepareStatement(sql);
                pstmt.setString(1, email);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    String studentInfo = "Name: " + rs.getString("name") +
                            "\nRoll No: " + rs.getString("roll_no") +
                            "\nPhone: " + rs.getString("phone") +
                            "\nCourse: " + rs.getString("course") +
                            "\nFees: " + rs.getString("fees") +
                            "\nSubjects: " + rs.getString("subjects");
                    JOptionPane.showMessageDialog(frame, studentInfo);
                } else {
                    JOptionPane.showMessageDialog(frame, "Student not found.");
                }
                rs.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(frame, "Error fetching student information.");
                e.printStackTrace();
            }
        }
    }

    public void setVisible(boolean visible) {
        frame.setVisible(visible);
    }
}
