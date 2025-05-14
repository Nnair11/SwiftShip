package MyJDBC;

import constants.CommonConstants;

import java.sql.*;

public class Sql {

    public static boolean register (String username, String password){
        try{
            // first check if the username already exists in the database
            if(!checkUser(username)){
                // connect to the database
                Connection connection = DriverManager.getConnection(CommonConstants.db_url,
                        CommonConstants.db_username, CommonConstants.db_password);

                // create insert query
                PreparedStatement insertUser = connection.prepareStatement(
                        "INSERT INTO " + CommonConstants.db_tblname + "(username, password)" +
                                "VALUES(?, ?)"
                );

                // insert parameters in the insert query
                insertUser.setString(1, username);
                insertUser.setString(2, password);

                // update db with new user
                insertUser.executeUpdate();
                return true;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public static boolean checkUser(String username){
        try {
            Connection connection = DriverManager.getConnection(CommonConstants.db_url,
                    CommonConstants.db_username, CommonConstants.db_password);
            PreparedStatement checkUserExists = connection.prepareStatement("Select * from " +
                    CommonConstants.db_tblname + " WHERE username = ?"
            );
            checkUserExists.setString(1, username);

            ResultSet resultSet = checkUserExists.executeQuery();

            if(!resultSet.isBeforeFirst()){
                return false;
            }

        }catch (SQLException e){
            e.printStackTrace();
        }

        return true;
    }
    public static boolean validateLogin(String username, String password){
        try{
            Connection connection = DriverManager.getConnection(CommonConstants.db_url,
                    CommonConstants.db_username, CommonConstants.db_password);

            // create select query
            PreparedStatement validateUser = connection.prepareStatement(
                    "SELECT * FROM " + CommonConstants.db_tblname +
                            " WHERE username = ? AND password = ?"

            );
            validateUser.setString(1, username);
            validateUser.setString(2, password);

            ResultSet resultSet = validateUser.executeQuery();

            if(!resultSet.isBeforeFirst()){
                return false;
            }

        }catch(SQLException e){
            e.printStackTrace();
        }

        return true;
    }

}
