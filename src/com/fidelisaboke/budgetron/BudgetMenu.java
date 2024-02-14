package com.fidelisaboke.budgetron;

import javax.swing.*;
import java.util.ArrayList;
import java.util.logging.Level;

public class BudgetMenu {
    private static BudgetMenu instance;
    private final String className = BudgetMenu.class.getName();
    public static boolean inBudgetMenu;
    private BudgetMenu(){

    }

    public static BudgetMenu getInstance(){
        if(instance == null){
            instance = new BudgetMenu();
        }
        return instance;
    }

    public void start(){
        inBudgetMenu = true;
        while(inBudgetMenu){
            executeBudgetMenu();
        }
    }

    private void executeBudgetMenu(){
        String input = JOptionPane.showInputDialog(BudgetronFrame.getInstance(), """
                Manage Budget
                1. Get budgets
                2. Add budget
                3. Update budget
                4. Delete budget
                0. Back to Main Menu""", "Budget Menu", JOptionPane.PLAIN_MESSAGE);

        switch(input){
            case "1"->{
                getBudgets();
            }
            case "2"->{
                System.out.println("Add budget");
            }

            case "3"->{
                System.out.println("Update budget");
            }

            case "4"->{
                System.out.println("Delete budget");
            }

            case "0"->{
                inBudgetMenu = false;
                BudgetronFrame.inMainMenu = true;
                BudgetronFrame.getInstance().start();
            }

            default->{
                MsgHandler.displayMessage(
                        "Invalid Option",
                        "Invalid option. Try again.",
                        className,
                        Level.SEVERE);
            }

        }
    }

    private void getBudgets(){
        String input = JOptionPane.showInputDialog(
                BudgetronFrame.getInstance(),
                """
                Get Budgets
                1. All budgets
                2. Enter budget to get...
                0. Back""",
                "Display Budgets",
                JOptionPane.PLAIN_MESSAGE);

        switch (input){
            case "1"->{
                displayAllBudgets();
            }
            case "2"->{
                String budgetName = JOptionPane.showInputDialog(
                        BudgetronFrame.getInstance(),
                        """
                        Enter budget name:""",
                        "Enter budget name",
                        JOptionPane.PLAIN_MESSAGE
                );

                BudgetRow budgetRow = Budget.getInstance().get("name", budgetName);
                String budgetRecord = budgetRow.getInfo();

                JOptionPane.showMessageDialog(
                        BudgetronFrame.getInstance(),
                        budgetRecord,
                        "Budget Info",
                        JOptionPane.PLAIN_MESSAGE
                );
            }
            case "0"->{

            }
        }
    }

    // Function that displays all budgets on a message dialog
    private void displayAllBudgets(){
        StringBuilder budgetData = new StringBuilder();
        ArrayList<BudgetRow> budgetRows = Budget.getInstance().getAll();
        for(BudgetRow budgetRow: budgetRows){
            budgetData.append(budgetRow.getInfo()).append("\n\n");
        }
        JOptionPane.showMessageDialog(
                BudgetronFrame.getInstance(),
                budgetData,
                "All Budgets",
                JOptionPane.PLAIN_MESSAGE
        );
    }

}
