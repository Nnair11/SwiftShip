package Guis;

import MyJDBC.Sql;
import constants.CommonConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class staffGui extends Form{
        public staffGui(){
            super("Login");

            addGuiComponent();
        }

        private void addGuiComponent(){
            JLabel loginlabel = new JLabel("SwiftShip");

            loginlabel.setBounds(110, 25, 520, 100);
            loginlabel.setForeground(CommonConstants.Text_Color);
            loginlabel.setFont(new Font("Dialog", Font.BOLD, 50));
            loginlabel.setHorizontalAlignment(SwingConstants.CENTER);

            add(loginlabel);

            JLabel usernameLabel = new JLabel("Staff Id: ");
            usernameLabel.setBounds(75, 200, 400, 50);
            usernameLabel.setForeground(CommonConstants.Text_Color);
            usernameLabel.setFont(new Font("Dialog", Font.PLAIN, 35));

            JTextField usernameField = new JTextField();
            usernameField.setBounds(75, 250, 600, 60);
            usernameField.setBackground(CommonConstants.Secondary_Color);
            usernameField.setForeground(CommonConstants.Text_Color);
            usernameField.setFont(new Font("Dialog", Font.PLAIN,  40));

            add(usernameLabel);
            add(usernameField);

            JLabel passwordLabel = new JLabel("Password: ");
            passwordLabel.setBounds(75, 350, 400, 50);
            passwordLabel.setForeground(CommonConstants.Text_Color);
            passwordLabel.setFont(new Font("Dialog", Font.PLAIN, 35));

            JPasswordField passwordField = new JPasswordField();
            passwordField.setBounds(75, 400, 600, 60);
            passwordField.setBackground(CommonConstants.Secondary_Color);
            passwordField.setForeground(CommonConstants.Text_Color);
            passwordField.setFont(new Font("Dialog", Font.PLAIN,  40));

            add(passwordLabel);
            add(passwordField);


            JButton loginButton = new JButton("Log In");
            loginButton.setFont(new Font("Dialog", Font.BOLD, 30));

            loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            loginButton.setBackground(CommonConstants.Text_Color);
            loginButton.setBounds(525, 530, 150, 50);

            loginButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String username = usernameField.getText();
                    String password = new String(passwordField.getPassword());

                    if(Sql.validateLogin(username, password)) {
                        JOptionPane.showMessageDialog(staffGui.this,
                                "Login Successfully!");

                        staffGui.this.dispose();

                        new packGui().setVisible(true);


                    } else {
                        JOptionPane.showMessageDialog(staffGui.this, "Login Failed");
                    }
                }
            });


            add(loginButton);

            JLabel registerLabel = new JLabel("Create new account");
            registerLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            registerLabel.setForeground(CommonConstants.Text_Color);

            registerLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    staffGui.this.dispose();

                    new SignupGui().setVisible(true);
                }
            });

            registerLabel.setBounds(75, 500, 200, 30);

            add(registerLabel);

            JLabel adminLabel = new JLabel("Admin Log In");
            adminLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            adminLabel.setForeground(CommonConstants.Text_Color);

            adminLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    staffGui.this.dispose();

                    new adminGui().setVisible(true);
                }
            });

            adminLabel.setBounds(75, 525, 200, 30);

            add(adminLabel);

            JLabel staffLabel = new JLabel("Customer Log In");
            staffLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            staffLabel.setForeground(CommonConstants.Text_Color);

            staffLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    staffGui.this.dispose();

                    new LoginGui().setVisible(true);
                }
            });

            staffLabel.setBounds(75, 550, 200, 30);

            add(staffLabel);
        }
}
