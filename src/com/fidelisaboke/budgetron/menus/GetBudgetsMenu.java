package com.fidelisaboke.budgetron.menus;

import com.fidelisaboke.budgetron.database.models.Budget;
import com.fidelisaboke.budgetron.database.rows.BudgetRow;
import com.fidelisaboke.budgetron.ui.BudgetronFrame;

import javax.swing.*;
import java.util.ArrayList;

public class GetBudgetsMenu implements BaseMenu {
    private static GetBudgetsMenu instance;

    private GetBudgetsMenu(){}

    public static GetBudgetsMenu getInstance(){
        if(instance == null){
            instance = new GetBudgetsMenu();
        }

        return instance;
    }
    @Override
    public void start() {
        MenuManager.setMenuStatus(MenuType.GET_BUDGETS_MENU, false);
        while(MenuManager.isInMenu(MenuType.GET_BUDGETS_MENU)){
            execute();
        }
    }

    @Override
    public void execute() {
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

    @Override
    public void handleOptionInput(String input) {

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
