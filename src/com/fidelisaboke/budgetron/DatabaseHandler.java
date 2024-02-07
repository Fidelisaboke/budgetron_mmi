package com.fidelisaboke.budgetron;

import java.lang.reflect.Field;
import java.sql.*;

/**
 * Abstract class to be extended by database models. Performs database operations
 */
public abstract class DatabaseHandler<T>{
    // Private Properties:
    private final String className = DatabaseHandler.class.getName();
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/db_budgetron";
    private Connection conn;
    private PreparedStatement pst;
    private int paramIndex;
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
            errorMsg = e.getMessage();
            MsgHandler.displayMessage("An error occurred", errorMsg, className);
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
                MsgHandler.displayMessage("An error occurred", errorMsg, className);
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
            this.connect();
            pst = conn.prepareStatement(sql);
            paramIndex = 1;
            for(Field field : fields){
                field.setAccessible(true);
                Object value = field.get(data);
                if(value != null){
                    pst.setObject(paramIndex++, value);
                }
            }
            pst.executeUpdate();
            MsgHandler.displayMessage("Insert Success", "Data inserted successfully.", className);
            this.close();
        } catch(IllegalAccessException e){
            errorMsg = "Error accessing field while setting params: " + e.getMessage();
            MsgHandler.displayMessage("An error occurred", errorMsg, className);
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
