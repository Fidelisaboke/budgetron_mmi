package com.fidelisaboke.budgetron;

import javax.swing.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseHandler{
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private static final String DB_URL = "";
    private Connection conn;
    private Statement stmt;
    private PreparedStatement pst;

    private Connection connect(){
        try{
            Class.forName(DRIVER);
            return DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(
                    BudgetronFrame.getInstance(),
                    e.getMessage(),
                    "An Error Occurred", JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, e.getMessage());
            return null;
        }

    }
}
