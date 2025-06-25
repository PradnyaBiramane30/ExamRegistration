package panda;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginForm extends JFrame {
    private JTextField txtEmail;
    private JPasswordField txtPassword;

    public LoginForm() {
        setTitle("Log In");
        setBounds(100, 100, 400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setForeground(new Color(255, 255, 255));
        lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
        lblEmail.setBounds(50, 50, 100, 30);
        getContentPane().add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(150, 50, 200, 30);
        getContentPane().add(txtEmail);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setForeground(new Color(255, 255, 255));
        lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
        lblPassword.setBounds(50, 100, 100, 30);
        getContentPane().add(lblPassword);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(150, 100, 200, 30);
        getContentPane().add(txtPassword);

        JButton btnLogin = new JButton("Log In");
        btnLogin.setBounds(150, 150, 100, 30);
        btnLogin.addActionListener(e -> {
            String email = txtEmail.getText();
            String password = new String(txtPassword.getPassword());
            if (login(email, password)) {
                JOptionPane.showMessageDialog(null, "Login Successful");

                // Navigate to the main page upon successful login
                main mainPage = new main();
                mainPage.setVisible(true);

                dispose(); // Close the LoginForm
            } else {
                JOptionPane.showMessageDialog(null, "Login Failed");
            }
        });
        getContentPane().add(btnLogin);
        
        JLabel lblNewLabel = new JLabel("New label");
        lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Kasif\\Downloads\\abstract-square-interface-modern-background-concept-fingerprint-digital-scanning-visual-security-system-authentication-login-vector.jpg"));
        lblNewLabel.setBounds(0, 0, 386, 263);
        getContentPane().add(lblNewLabel);
    }

    private boolean login(String email, String password) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/exam", "postgres", "root");
            String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            boolean result = rs.next();
            rs.close();
            pstmt.close();
            connection.close();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
