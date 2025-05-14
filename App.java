import Guis.LoginGui;
import Guis.SignupGui;
import MyJDBC.Sql;

import javax.swing.*;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LoginGui().setVisible(true);

                //System.out.println(Sql.checkUser("username"));

                //System.out.println(Sql.register("username1234", "password"));

                //System.out.println(Sql.validateLogin("username1234", "password"));
            }
        });
    }
}