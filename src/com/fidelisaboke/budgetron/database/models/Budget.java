package com.fidelisaboke.budgetron.database.models;

import com.fidelisaboke.budgetron.database.rows.BudgetRow;
import com.fidelisaboke.budgetron.utilities.MsgHandler;
import com.fidelisaboke.budgetron.database.DatabaseHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;

/**
 * Represents the 'budgets' table in the MySQL database.
 * It uses the generic type 'BudgetRow' which is a
 * class that represents a single entity of the budgets table
 */
public class Budget extends DatabaseHandler<BudgetRow> {
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
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double amount = rs.getDouble("amount");
                rs.close();
                return new BudgetRow(id, name, amount);
            }
            rs.close();
        } catch (SQLException e){
            String errorMsg = e.getMessage();
            MsgHandler.displayMessage("Data retrieval error", errorMsg, className, Level.SEVERE);
        }
        return null;
    }

    @Override
    public ArrayList<BudgetRow> getAll(String identifier, Object value) {
        ArrayList<BudgetRow> budgetRows = new ArrayList<>();
        try{
            ResultSet rs = super.retrieve(identifier, value);
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double amount = rs.getDouble("amount");
                budgetRows.add(new BudgetRow(id, name, amount));
            }
            rs.close();
        } catch (SQLException e){
            String errorMsg = e.getMessage();
            MsgHandler.displayMessage("Data retrieval error", errorMsg, className, Level.SEVERE);
        }
        return budgetRows;
    }

    @Override
    public ArrayList<BudgetRow> getAll() {
        ArrayList<BudgetRow> budgetRows = new ArrayList<>();
        try{
            ResultSet rs = super.retrieveAll("*");
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double amount = rs.getDouble("amount");
                budgetRows.add(new BudgetRow(id, name, amount));
            }
            rs.close();
        } catch (SQLException e){
            String errorMsg = e.getMessage();
            MsgHandler.displayMessage("Data retrieval error", errorMsg, className, Level.SEVERE);
        }
        return budgetRows;
    }
}
