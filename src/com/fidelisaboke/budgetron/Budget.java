package com.fidelisaboke.budgetron;

/**
 * Represents the 'budgets' table in the MySQL database.
 * It uses the generic type 'BudgetRow' which is a
 * class that represents a single entity of the budgets table
 */
public class Budget extends DatabaseHandler<BudgetRow>{
    private static Budget instance;
    private Budget(){
        this.tableName = "budgets";
    }

    public static Budget getInstance(){
        if(instance == null){
            instance = new Budget();
        }
        return instance;
    }
}
