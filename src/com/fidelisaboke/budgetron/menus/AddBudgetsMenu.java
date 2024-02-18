package com.fidelisaboke.budgetron.menus;

import com.fidelisaboke.budgetron.database.models.Budget;
import com.fidelisaboke.budgetron.database.rows.BudgetRow;
import com.fidelisaboke.budgetron.ui.BudgetronFrame;
import com.fidelisaboke.budgetron.utilities.MsgHandler;

import javax.swing.*;
import java.sql.SQLException;
import java.util.logging.Level;

public class AddBudgetsMenu implements BaseMenu{
    private final String className = AddBudgetsMenu.class.getName();
    @Override
    public void start() {
        MenuManager.setMenuStatus(MenuType.ADD_BUDGETS_MENU, true);
        while(MenuManager.isInMenu(MenuType.ADD_BUDGETS_MENU)){
            execute();
        }
    }

    @Override
    public void execute() {
        String input = JOptionPane.showInputDialog(
                BudgetronFrame.getInstance(),
                """
                        Add Budget
                        Enter a name for the budget
                        Or enter '0' to go back""",
                "Budget Name",
                JOptionPane.PLAIN_MESSAGE);

        handleOptionInput(input);
    }

    @Override
    public void handleOptionInput(String input) {
        if (input.equals("0")) {
            MenuManager.setMenuStatus(MenuType.ADD_BUDGETS_MENU, false);
            BudgetMenu.getInstance().start();
        } else {
            String budgetName = input;
            input = JOptionPane.showInputDialog(
                    BudgetronFrame.getInstance(),
                    """
                            Enter amount or 'x' to go back to budget menu
                            Format: 0000.00""",
                    "Budget Amount",
                    JOptionPane.PLAIN_MESSAGE
            );
            handleBudgetAmountInput(budgetName, input);
        }
    }

    public void handleBudgetAmountInput(String budgetName, String input){
        if(input.equals("x")){
            MenuManager.setMenuStatus(MenuType.ADD_BUDGETS_MENU, false);
            BudgetMenu.getInstance().start();
        } else{
            try{
                double budgetAmount =  Double.parseDouble(input);
                Budget.getInstance().insert(new BudgetRow(budgetName, budgetAmount));
            } catch (NumberFormatException e){
                String errorMsg = "Invalid amount: " + e.getMessage();
                MsgHandler.displayMessage(
                        "An error occurred",
                        errorMsg,
                        className,
                        Level.SEVERE
                );
            } catch (SQLException e){
                String errorMsg = "Failed to insert budget: " + e.getMessage();
                MsgHandler.displayMessage(
                        "Budget Insert Error",
                        errorMsg,
                        className,
                        Level.SEVERE
                );
            }
        }
    }
}
