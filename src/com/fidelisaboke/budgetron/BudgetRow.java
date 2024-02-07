package com.fidelisaboke.budgetron;

/**
 * Represents a single row of the Budget table
 */
public class BudgetRow {
    private String name;
    private float amount;

    public BudgetRow(String name, float amount) {
        this.name = name;
        this.amount = amount;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

}
