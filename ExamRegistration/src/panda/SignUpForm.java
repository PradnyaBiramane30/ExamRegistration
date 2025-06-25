package panda;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class SignUpForm extends JFrame {
    private JTextField txtName;
    private JTextField txtEmail;
    private JPasswordField txtPassword;

    public SignUpForm() {
        setTitle("Sign Up");
        setBounds(100, 100, 400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        JLabel lblName = new JLabel("Name:");
        lblName.setForeground(new Color(255, 255, 255));
        lblName.setHorizontalAlignment(SwingConstants.CENTER);
        lblName.setBounds(50, 30, 100, 30);
        getContentPane().add(lblName);

        txtName = new JTextField();
        txtName.setBounds(150, 30, 200, 30);
        getContentPane().add(txtName);

        JLabel lblEmail = ew JLabel("Email:");
        lblEmail.setForeground(new Color(255, 255, 255));
        lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
        lblEmail.setBounds(50, 80, 100, 30);
        getContentPane().add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(150, 80, 200, 30);
        getContentPane().add(txtEmail);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setForeground(new Color(255, 255, 255));
        lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
        lblPassword.setBounds(50, 130, 100, 30);
        getContentPane().add(lblPassword);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(150, 130, 200, 30);
        getContentPane().add(txtPassword);

        JButton btnSubmit = new JButton("Submit");
        btnSubmit.setBounds(150, 180, 100, 30);
        btnSubmit.addActionListener(e -> {
            String name = txtName.getText();
            String email = txtEmail.getText();
            String password = new String(txtPassword.getPassword());
            if (signUp(name, email, password)) {
                JOptionPane.showMessageDialog(null, "Registration Successful");
                new examregistration().setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Registration Failed");
            }
        });
        getContentPane().add(btnSubmit);
        
        JLabel lblNewLabel = new JLabel("New label");
        lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Kasif\\Downloads\\realistic-style-technology-particle-background_23-2148426704.jpg"));
        lblNewLabel.setBounds(0, 0, 386, 263);
        getContentPane().add(lblNewLabel);
    }

    private boolean signUp(String name, String email, String password) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/exam", "postgres", "root");
            String sql = "INSERT INTO users (name, email, password) VALUES (?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setString(3, password);
            pstmt.executeUpdate();
            pstmt.close();
            connection.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
