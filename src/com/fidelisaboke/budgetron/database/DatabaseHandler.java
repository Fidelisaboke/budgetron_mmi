package com.fidelisaboke.budgetron.database;

import com.fidelisaboke.budgetron.utilities.MsgHandler;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Abstract class to be extended by database models. Performs database operations
 */
public abstract class DatabaseHandler<T> {
    // Private Properties:
    private final String className = DatabaseHandler.class.getName();
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/db_budgetron";
    private Connection conn;
    private PreparedStatement pst;
    private ResultSet rs;
    private int paramIndex;
    private String errorMsg;

    // Protected Properties:
    protected String tableName;

    /**
     * Returns the name of the table (database model)
     *
     * @return The name of the table as a string
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * Establishes a connection to the database
     */
    private void connect() {
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            errorMsg = e.getMessage();
            MsgHandler.displayMessage("An error occurred", errorMsg, className, Level.SEVERE);
        }

    }

    /**
     * Closes a connection to the database
     *
     * @throws SQLException If a database access error occurs
     */
    private void close() throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }


    /**
     * Inserts a new record to the database
     * The Field class is used to access the properties of the class (generic type) of the data
     * @param data The data to be inserted.
     * @throws SQLException If a database access error occurs
     */
    public void insert(@NotNull T data) throws SQLException {
        StringBuilder columns = new StringBuilder();
        StringBuilder values = new StringBuilder();
        String tableName = this.getTableName();

        Field[] fields = data.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String columnName = field.getName();
            Object value;
            try {
                value = field.get(data);
            } catch (IllegalAccessException e) {
                errorMsg = "Error accessing field: " + columnName;
                MsgHandler.displayMessage("An error occurred", errorMsg, className, Level.SEVERE);
                throw new SQLException(errorMsg);
            }
            if (value != null) {
                if (columns.length() > 0) {
                    columns.append(", ");
                    values.append(", ");
                }
                columns.append(columnName);
                values.append("?");
            }
        }

        String sql = "INSERT INTO " + tableName + " (" + columns + ") VALUES (" + values + ")";
        Logger.getLogger(className).log(Level.INFO, "SQL Statement -> " + sql);

        try {
            this.connect();
            pst = conn.prepareStatement(sql);
            paramIndex = 1;
            for (Field field : fields) {
                field.setAccessible(true);
                Object value = field.get(data);
                if (value != null) {
                    pst.setObject(paramIndex++, value);
                }
            }
            Logger.getLogger(className).log(Level.SEVERE, "Prepared Stmt ->" + pst.toString());
            if(pst.executeUpdate()==1){
                MsgHandler.displayMessage(
                        "Insert Success",
                        "Data inserted successfully.",
                        className,
                        Level.INFO);
            }else{
                MsgHandler.displayMessage(
                        "Insert Error",
                        "An error occured. No record inserted.",
                        className,
                        Level.SEVERE
                );
            }
            this.close();
        } catch (IllegalAccessException e) {
            errorMsg = "Error accessing field while setting params: " + e.getMessage();
            MsgHandler.displayMessage("An error occurred", errorMsg, className, Level.SEVERE);
            throw new SQLException(errorMsg);
        }
    }

    /**
     * Updates an existing record in the database
     * @param id   The id of the record in the database
     * @param data The data sent to update the record in question
     * @throws SQLException If a database access error occurs
     */
    public void update(int id, @NotNull T data) throws SQLException {
        StringBuilder placeholders = new StringBuilder();
        String tableName = this.getTableName();

        Field[] fields = data.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            String columnName = field.getName();
            Object value;
            try {
                value = field.get(data);
            } catch (IllegalAccessException e) {
                errorMsg = "Error accessing field: " + columnName;
                MsgHandler.displayMessage("An error occurred", errorMsg, className, Level.SEVERE);
                throw new SQLException(errorMsg);
            }
            if (value != null && !columnName.equals("id")) {
                if (placeholders.length() > 0) {
                    placeholders.append(", ");
                }
                placeholders.append(columnName).append(" = ").append("?");
            }
        }

        String sql = "UPDATE " + tableName + " SET " + placeholders + " WHERE id = ?";
        Logger.getLogger(className).log(Level.INFO, "SQL statement -> " + sql);

        try {
            this.connect();
            pst = conn.prepareStatement(sql);
            paramIndex = 1;
            for (Field field : fields) {
                field.setAccessible(true);
                Object value = field.get(data);
                if (value != null && !field.getName().equals("id")) {
                    pst.setObject(paramIndex++, value);
                }
            }
            pst.setInt(paramIndex, id);
            Logger.getLogger(className).log(Level.SEVERE, "Prepared Stmt ->" + pst.toString());

            if(pst.executeUpdate() == 1){
                MsgHandler.displayMessage(
                        "Update Success",
                        "Data successfully updated",
                        className,
                        Level.INFO);
            }else{
                MsgHandler.displayMessage(
                        "Update Error",
                        "Failed to update. No records affected.",
                        className,
                        Level.SEVERE
                );
            };

            this.close();
        } catch (IllegalAccessException e) {
            errorMsg = "Error accessing field when setting params: " + e.getMessage();
            MsgHandler.displayMessage("An error occurred", errorMsg, className, Level.SEVERE);
            throw new SQLException(errorMsg);
        }
    }

    public void delete(String identifier, Object value) {
        String tableName = this.getTableName();
        String sql = "DELETE FROM " + tableName + " WHERE " + identifier + " = ?";
        Logger.getLogger(className).log(Level.INFO, sql);

        try{
            this.connect();
            pst = conn.prepareStatement(sql);
            paramIndex = 1;
            pst.setObject(paramIndex, value);
            Logger.getLogger(className).log(Level.SEVERE, "Prepared Stmt ->" + pst.toString());

            if(pst.executeUpdate()==1){
                MsgHandler.displayMessage(
                        "Delete Success",
                        "The record has been deleted successfully.",
                        className,
                        Level.INFO
                );
            }else{
                MsgHandler.displayMessage(
                        "Delete Failed",
                        "An error occurred when deleting the record.",
                        className,
                        Level.SEVERE
                );
            }

        } catch (SQLException e) {
            errorMsg = "Failed to delete: " + e.getMessage();
            MsgHandler.displayMessage(
                    "Delete Error",
                    errorMsg,
                    className,
                    Level.SEVERE
            );
        }
    }

    /**
     * Retrieves data from the database as a ResultSet.
     * @param identifier The field or column used to retrieve the result set
     * @param value      The value of the field that identifies the result set
     * @return ResultSet containing records from the database
     */
    protected ResultSet retrieve(String identifier, Object value) {
        String tableName = this.getTableName();
        String sql = "SELECT * FROM " + tableName + " WHERE " + identifier + " = ?";

        try {
            this.connect();
            pst = conn.prepareStatement(sql);
            paramIndex = 1;
            pst.setObject(paramIndex, value);
            rs = pst.executeQuery();

        } catch (SQLException e) {
            errorMsg = "Error fetching data: " + e.getMessage();
            MsgHandler.displayMessage("Data retrieval error", errorMsg, className, Level.SEVERE);
        }
        return rs;
    }

    /**
     * Retrieves all records of a table as a ResultSet (or values of a column)
     * @return ResultSet containing all records of a table
     */
    protected ResultSet retrieveAll(String columnName) {
        String tableName = this.getTableName();
        String sql = "SELECT " + columnName + " FROM " + tableName;
        Logger.getLogger(className).log(Level.INFO, "SQL Statement -> " + sql);

        try{
            this.connect();
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
        } catch (SQLException e){
            errorMsg = "Error fetching all records: " + e.getMessage();
            MsgHandler.displayMessage("Table data retrieval error", errorMsg, className, Level.SEVERE);
        }
        return rs;
    }

    /**
     * Returns all data of a table's column
     * @param columnName The name of the column to get data from
     * @return ArrayList of data of a specific column
     */
    public ArrayList<Object> getColumn(String columnName) {
        ArrayList<Object> columnValues = new ArrayList<>();
        try{
            ResultSet rs = this.retrieveAll(columnName);
            while(rs.next()){
                Object columnValue = rs.getObject(columnName);
                columnValues.add(columnValue);
            }
            rs.close();
        } catch(SQLException e){
            String errorMsg = e.getMessage();
            MsgHandler.displayMessage("Column data retrieval error", errorMsg, className, Level.SEVERE);
        }
        return columnValues;
    }

    /**
     * Returns an object of generic type T, representing a single record (row) of a table.
     * The generic type is specified by the models using their corresponding row classes
     * @param identifier The field or column used to specify the record to be retrieved
     * @param value The value of the field that identifies the record
     * @return A row object, or null if an error occurs or no record was found
     */
    public abstract T get(String identifier, Object value);

    /**
     * Returns objects of generic type T, representing rows of a table, specified by a field value.
     * The generic type is specified by the models using their corresponding row classes
     * @param identifier The field or column used to specify the record to be retrieved
     * @param value      Value of the field that identifies the records
     * @return A list of row objects, or null if an error occurs or no records were found
     */
    public abstract ArrayList<T> getAll(String identifier, Object value);

    /**
     * Returns all rows of a table as objects of generic type T specified by the row classes related
     * to the database models
     * @return A list of row objects, or null if an error occurs or no records were found
     */
    public abstract ArrayList<T> getAll();
}
