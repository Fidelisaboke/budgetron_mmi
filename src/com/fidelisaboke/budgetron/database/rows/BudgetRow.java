package com.fidelisaboke.budgetron.database.rows;

/**
 * Represents a single row of the Budget table
 */
public class BudgetRow {
    private String name;
    private double amount;

    public BudgetRow(String name, double amount) {
        this.name = name;
        this.amount = amount;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getInfo(){
        return "Name: " + this.getName() + "\n" +
                "Amount: " + this.getAmount();
    }

}
