package com.fidelisaboke.budgetron.database.rows;

/**
 * Represents a single row of the Budget table
 */
public class BudgetRow {
    private int id;
    private String name;
    private double amount;

    public BudgetRow(String name, double amount) {
        this.name = name;
        this.amount = amount;
    }

    public BudgetRow(int id, String name, double amount){
        this.id = id;
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

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInfo(){
        return "id: " + this.getId() + "\n" +
                "Name: " + this.getName() + "\n" +
                "Amount: " + this.getAmount();
    }

}
