package com.fidelisaboke.budgetron;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;

/**
 * Represents the 'budgets' table in the MySQL database.
 * It uses the generic type 'BudgetRow' which is a
 * class that represents a single entity of the budgets table
 */
public class Budget extends DatabaseHandler<BudgetRow>{
    private static Budget instance;
    private final String className = Budget.class.getName();
    private Budget(){
        this.tableName = "budgets";
    }

    public static Budget getInstance(){
        if(instance == null){
            instance = new Budget();
        }
        return instance;
    }

    @Override
    public BudgetRow get(String identifier, Object value) {
        try{
            ResultSet rs = super.retrieve(identifier, value);
            if(rs.next()){
                String name = rs.getString("name");
                double amount = rs.getDouble("amount");
                return new BudgetRow(name, amount);
            }
            rs.close();
        } catch (SQLException e){
            String errorMsg = e.getMessage();
            MsgHandler.displayMessage("Data retrieval error", errorMsg, className, Level.SEVERE);
        }
        return null;
    }

    @Override
    public BudgetRow getAll(String identifier, Object value) {
        return null;
    }

    @Override
    public BudgetRow getAll() {
        return null;
    }
}
