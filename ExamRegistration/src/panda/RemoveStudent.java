package panda;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class RemoveStudent {
    private JFrame frame;
    private JTextField txtName;
    private JButton btnRemove;
    private JButton btnBack; // Back button
    private JLabel lblNewLabel;
    private JLabel lblNewLabel_1;

    public RemoveStudent() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Remove Student");
        frame.setBounds(100, 100, 601, 407);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblName = new JLabel("Enter Student Name:");
        lblName.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 11));
        lblName.setHorizontalAlignment(SwingConstants.CENTER);
        lblName.setBounds(74, 138, 150, 25);
        frame.getContentPane().add(lblName);

        txtName = new JTextField();
        txtName.setBounds(298, 138, 200, 25);
        frame.getContentPane().add(txtName);

        btnRemove = new JButton("Remove");
        btnRemove.setBounds(212, 195, 114, 34);
        btnRemove.addActionListener(e -> removeStudent());
        frame.getContentPane().add(btnRemove);

        btnBack = new JButton("Back");
        btnBack.setBounds(20, 315, 80, 25);
        btnBack.addActionListener(e -> {
            frame.dispose(); // Close current frame
            main window = new main(); // Open main window
            window.setVisible(true);
        });
        frame.getContentPane().add(btnBack);
        
        lblNewLabel = new JLabel("REMOVE STUDENT");
        lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 22));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(172, 34, 227, 34);
        frame.getContentPane().add(lblNewLabel);
        
        lblNewLabel_1 = new JLabel("New label");
        lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\Kasif\\Downloads\\c22648ceb5a6f1a45742c2a9890f5c34.jpg"));
        lblNewLabel_1.setBounds(0, 10, 587, 360);
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

    private void removeStudent() {
        String name = txtName.getText().trim();
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please enter a name.");
            return;
        }

        try (Connection connection = connect()) {
            String sql = "DELETE FROM stu WHERE name = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, name);
            int rowsDeleted = pstmt.executeUpdate();
            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(frame, "Student removed successfully.");
            } else {
                JOptionPane.showMessageDialog(frame, "Student not found.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Error removing student.");
            e.printStackTrace();
        }
    }

    public void setVisible(boolean visible) {
        frame.setVisible(visible);
    }
}
