package com.fidelisaboke.budgetron;

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
