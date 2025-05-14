package Guis;

import MyJDBC.Sql;
import constants.CommonConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SignupGui extends Form {
    public SignupGui() {
        super("Sign Up");

        addGuiComponent();
    }

    private void addGuiComponent() {
        JLabel signupLabel = new JLabel("Sign Up");

        signupLabel.setBounds(110, 25, 520, 100);
        signupLabel.setForeground(CommonConstants.Text_Color);
        signupLabel.setFont(new Font("Dialog", Font.BOLD, 50));
        signupLabel.setHorizontalAlignment(SwingConstants.CENTER);

        add(signupLabel);

        JLabel usernameLabel = new JLabel("Username: ");
        usernameLabel.setBounds(75, 150, 400, 50);
        usernameLabel.setForeground(CommonConstants.Text_Color);
        usernameLabel.setFont(new Font("Dialog", Font.PLAIN, 35));

        JTextField usernameField = new JTextField();
        usernameField.setBounds(75, 200, 600, 60);
        usernameField.setBackground(CommonConstants.Secondary_Color);
        usernameField.setForeground(CommonConstants.Text_Color);
        usernameField.setFont(new Font("Dialog", Font.PLAIN, 40));

        add(usernameLabel);
        add(usernameField);

        JLabel passwordLabel = new JLabel("Password: ");
        passwordLabel.setBounds(75, 275, 400, 50);
        passwordLabel.setForeground(CommonConstants.Text_Color);
        passwordLabel.setFont(new Font("Dialog", Font.PLAIN, 35));

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(75, 325, 600, 60);
        passwordField.setBackground(CommonConstants.Secondary_Color);
        passwordField.setForeground(CommonConstants.Text_Color);
        passwordField.setFont(new Font("Dialog", Font.PLAIN, 40));

        add(passwordLabel);
        add(passwordField);

        JLabel repasswordLabel = new JLabel("Re-Enter Password: ");
        repasswordLabel.setBounds(75, 400, 400, 50);
        repasswordLabel.setForeground(CommonConstants.Text_Color);
        repasswordLabel.setFont(new Font("Dialog", Font.PLAIN, 35));

        JPasswordField repasswordField = new JPasswordField();
        repasswordField.setBounds(75, 450, 600, 60);
        repasswordField.setBackground(CommonConstants.Secondary_Color);
        repasswordField.setForeground(CommonConstants.Text_Color);
        repasswordField.setFont(new Font("Dialog", Font.PLAIN, 40));

        add(repasswordLabel);
        add(repasswordField);


        JButton singupButton = new JButton("Sign Up");
        singupButton.setFont(new Font("Dialog", Font.BOLD, 30));
        singupButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        singupButton.setBackground(CommonConstants.Text_Color);
        singupButton.setBounds(525, 580, 150, 50);
        singupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String repassword = new String(repasswordField.getPassword());

                if (validateUserInput(username, password, repassword)) {
                    if (Sql.register(username, password)) {
                        SignupGui.this.dispose();

                        LoginGui loginGui = new LoginGui();
                        loginGui.setVisible(true);

                        JOptionPane.showMessageDialog(loginGui, "Sign Up Successfully!");
                    } else {
                        JOptionPane.showMessageDialog(SignupGui.this,
                                "Username is already taken");
                    }
                } else {
                    JOptionPane.showMessageDialog(SignupGui.this,
                            "Username must be at least 6 characters long\n" +
                                    "and Passwords must match");
                }
            }
        });


        add(singupButton);

        JLabel registerLabel = new JLabel("Already have an account?");
        registerLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        registerLabel.setForeground(CommonConstants.Text_Color);

        registerLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                SignupGui.this.dispose();

                new LoginGui().setVisible(true);
            }
        });

        registerLabel.setBounds(75, 550, 200, 30);

        add(registerLabel);

        JLabel adminLabel = new JLabel("Admin Log In");
        adminLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        adminLabel.setForeground(CommonConstants.Text_Color);

        adminLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                SignupGui.this.dispose();

                new adminGui().setVisible(true);
            }
        });

        adminLabel.setBounds(75, 575, 200, 30);

        add(adminLabel);

        JLabel staffLabel = new JLabel("Staff Log In");
        staffLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        staffLabel.setForeground(CommonConstants.Text_Color);

        staffLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                SignupGui.this.dispose();

                new staffGui().setVisible(true);
            }
        });

        staffLabel.setBounds(75, 600, 200, 30);

        add(staffLabel);
    }

    private boolean validateUserInput(String username, String password, String repassword){
        if(username.length() == 0 || password.length() == 0 || repassword.length() == 0)
            return false;

        if(username.length() < 6) return false;

        if(!password.equals(repassword)) return false;

        return true;
    }
}
