package com.fidelisaboke.budgetron;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.Arrays;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Abstract class to be extended by database models. Performs database operations
 */
public abstract class DatabaseHandler<T>{
    // Private Properties:
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/db_books";
    private Connection conn;
    private PreparedStatement pst;
    private int paramIndex = 1;
    private String errorMsg;

    // Protected Properties:
    protected String tableName;

    /**
     * Returns the name of the table (database model)
     * @return The name of the table as a string
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * Establishes a connection to the database
     */
    public void connect(){
        try{
            Class.forName(DRIVER);
            conn =  DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(
                    BudgetronFrame.getInstance(),
                    e.getMessage(),
                    "An Error Occurred", JOptionPane.ERROR_MESSAGE);
            errorMsg = e.getMessage();
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, errorMsg);
        }

    }

    /**
     * Closes a connection to the database
     * @throws SQLException If a database access error occurs
     */
    public void close() throws SQLException{
        if(conn != null){
            conn.close();
        }
    }


    // Method that inserts a new record to the DB
    protected void insert(T data) throws SQLException{
       StringBuilder columns = new StringBuilder();
       StringBuilder values = new StringBuilder();
       String tableName = this.getTableName();

        Field[] fields = data.getClass().getDeclaredFields();
        for(Field field: fields){
            field.setAccessible(true);
            String columnName = field.getName();
            Object value;
            try{
                value = field.get(data);
            } catch(IllegalAccessException e){
                errorMsg = "Error accessing field: " + columnName;
                Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, errorMsg);
                throw new SQLException(errorMsg);
            }
            if(value != null){
                if(columns.length() > 0){
                    columns.append(", ");
                    values.append(", ");
                }
                columns.append(columnName);
                values.append("?");
            }
        }

        String sql = "INSERT INTO " + tableName + " (" + columns + ") VALUES ("+ values +")";
        try{
            pst = conn.prepareStatement(sql);
            for(Field field : fields){
                field.setAccessible(true);
                Object value = field.get(data);
                if(value != null){
                    pst.setObject(paramIndex++, value);
                }
            }
            pst.executeUpdate();
        } catch(IllegalAccessException e){
            errorMsg = "Error accessing field while setting params: " + e.getMessage();
            Logger.getLogger(DatabaseHandler.class.getName()).log(Level.SEVERE, errorMsg);
            throw new SQLException(errorMsg);
        }
    }

    // Method that updates the DB record
    protected void update(){

    }

    // Method that deletes the DB record
    protected void delete(int id){

    }

    // Method that retrieves a record from the database
    protected void retrieve(int id){

    }

    //
    protected void retrieveAll(){

    }
}
