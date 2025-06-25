package panda;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class examregistration {
    private JFrame frame;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                examregistration window = new examregistration();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public examregistration() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setType(Window.Type.UTILITY);
        frame.getContentPane().setBackground(new Color(192, 192, 192));
        frame.setBounds(100, 100, 601, 404);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblTitle = new JLabel("EXAM REGISTRATION SYSTEM");
        lblTitle.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 27));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setBounds(86, 18, 500, 50);
        frame.getContentPane().add(lblTitle);

        JButton btnSignUp = new JButton("SIGN UP");
        btnSignUp.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
        btnSignUp.setBounds(150, 143, 300, 50);
        btnSignUp.addActionListener(e -> {
            new SignUpForm().setVisible(true);
            frame.dispose();
        });
        frame.getContentPane().add(btnSignUp);

        JButton btnLogin = new JButton("LOG IN");
        btnLogin.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
        btnLogin.setBounds(150, 232, 300, 50);
        btnLogin.addActionListener(e -> {
            new LoginForm().setVisible(true);
            frame.dispose();
        });
        frame.getContentPane().add(btnLogin);

        JLabel lblLogo = new JLabel(new ImageIcon("C:\\Users\\Kasif\\Downloads\\download (3).jpeg"));
        lblLogo.setBounds(10, 6, 105, 86);
        frame.getContentPane().add(lblLogo);

        JLabel lblSignUpIcon = new JLabel(new ImageIcon("C:\\Users\\Kasif\\Downloads\\add-user.png"));
        lblSignUpIcon.setBounds(69, 137, 71, 73);
        frame.getContentPane().add(lblSignUpIcon);

        JLabel lblLoginIcon = new JLabel(new ImageIcon("C:\\Users\\Kasif\\Downloads\\enter.png"));
        lblLoginIcon.setBounds(69, 226, 71, 73);
        frame.getContentPane().add(lblLoginIcon);

        JLabel lblBackground = new JLabel(new ImageIcon("C:\\Users\\Kasif\\Downloads\\sbup.png"));
        lblBackground.setBounds(0, -10, 586, 373);
        frame.getContentPane().add(lblBackground);
    }

    public void setVisible(boolean b) {
        frame.setVisible(b);
    }
}