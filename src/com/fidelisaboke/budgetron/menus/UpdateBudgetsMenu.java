package com.fidelisaboke.budgetron.menus;

import com.fidelisaboke.budgetron.database.models.Budget;
import com.fidelisaboke.budgetron.database.rows.BudgetRow;
import com.fidelisaboke.budgetron.ui.BudgetronFrame;
import com.fidelisaboke.budgetron.utilities.MsgHandler;

import javax.swing.*;
import java.sql.SQLException;
import java.util.logging.Level;

public class UpdateBudgetsMenu implements BaseMenu{
    private final String className = UpdateBudgetsMenu.class.getName();

    private static UpdateBudgetsMenu instance;

    private UpdateBudgetsMenu() {}

    public static UpdateBudgetsMenu getInstance() {
        if(instance == null){
            instance = new UpdateBudgetsMenu();
        }

        return instance;
    }
    @Override
    public void start() {
        MenuManager.setMenuStatus(MenuType.UPDATE_BUDGETS_MENU, true);
        while(MenuManager.isInMenu(MenuType.UPDATE_BUDGETS_MENU)){
            execute();
        }
    }

    @Override
    public void execute() {
        String input = JOptionPane.showInputDialog(
                BudgetronFrame.getInstance(),
                """
                        Update Budgets
                        Enter the name of the budget to update
                        (Type "0" to go back):
                        """,
                "Update Budgets",
                JOptionPane.PLAIN_MESSAGE
        );

        handleOptionInput(input);
    }

    @Override
    public void handleOptionInput(String input) {
        BudgetRow budgetRow = Budget.getInstance().get("name", input);

        if(input.equals("0")){
            MenuManager.setMenuStatus(MenuType.UPDATE_BUDGETS_MENU, false);
            BudgetMenu.getInstance().start();
        } else if(budgetRow == null){
            MsgHandler.displayMessage(
                    "An error occurred",
                    "Budget does not exist.",
                    className,
                    Level.SEVERE
            );
        }else{
            StringBuilder updateMsg = new StringBuilder();
            String updateOptions = """
                    1. Update name
                    2. Update budget amount
                    0. Back
                    """;

            updateMsg.append(budgetRow.getInfo()).append("\n").append(updateOptions);
            do {
                input = JOptionPane.showInputDialog(
                        BudgetronFrame.getInstance(),
                        updateMsg,
                        "Update budget",
                        JOptionPane.PLAIN_MESSAGE
                );

                handleUpdateInput(input, budgetRow);

            } while (!input.equals("0"));
        }
    }

    private void handleUpdateInput(String input, BudgetRow budgetRow){
        switch(input){
            case "1" -> {
                String budgetName = JOptionPane.showInputDialog(
                        BudgetronFrame.getInstance(),
                        "Enter new name:",
                        "Update name",
                        JOptionPane.PLAIN_MESSAGE
                );

                budgetRow.setName(budgetName);
                System.out.println(budgetRow.getName());
                runBudgetUpdate(budgetRow.getId(), budgetRow);
            }

            case "2" -> {
                String budgetAmount = JOptionPane.showInputDialog(
                        BudgetronFrame.getInstance(),
                        "Enter new amount:",
                        "Update budget amount",
                        JOptionPane.PLAIN_MESSAGE
                );
                try{
                    budgetRow.setAmount(Double.parseDouble(budgetAmount));
                } catch (NumberFormatException e){
                    String errorMsg = "Invalid amount: " + e.getMessage();
                    MsgHandler.displayMessage(
                            "An error occurred",
                            errorMsg,
                            className,
                            Level.SEVERE
                    );
                }

                runBudgetUpdate(budgetRow.getId(), budgetRow);
            }

            case "0" -> this.start();
        }
    }

    private void runBudgetUpdate(int budgetId, BudgetRow budgetRow){
        try{
            Budget.getInstance().update(budgetId, budgetRow);
        } catch (SQLException e){
            String errorMsg = e.getMessage();
            MsgHandler.displayMessage(
                    "An error occurred",
                    errorMsg,
                    className,
                    Level.SEVERE
            );
        }
    }
}