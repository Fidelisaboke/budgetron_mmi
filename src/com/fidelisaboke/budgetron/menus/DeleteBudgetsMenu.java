package com.fidelisaboke.budgetron.menus;

import com.fidelisaboke.budgetron.database.models.Budget;
import com.fidelisaboke.budgetron.ui.BudgetronFrame;

import javax.swing.*;

public class DeleteBudgetsMenu implements BaseMenu{
    private static DeleteBudgetsMenu instance;

    private DeleteBudgetsMenu() {}

    public static DeleteBudgetsMenu getInstance() {
        if(instance == null){
            instance = new DeleteBudgetsMenu();
        }

        return instance;
    }
    @Override
    public void start() {
        MenuManager.setMenuStatus(MenuType.DELETE_BUDGETS_MENU, true);
        while(MenuManager.isInMenu(MenuType.DELETE_BUDGETS_MENU)){
            execute();
        }
    }

    @Override
    public void execute() {
        String input = JOptionPane.showInputDialog(
                BudgetronFrame.getInstance(),
                """
                        Delete budgets
                        Enter the name of the budget to delete
                        (Type "0" to go back):""",
                "Delete Budgets",
                JOptionPane.PLAIN_MESSAGE
        );

        handleOptionInput(input);
    }

    @Override
    public void handleOptionInput(String input) {
        if(input.equals("0")){
            MenuManager.setMenuStatus(MenuType.DELETE_BUDGETS_MENU, false);
            BudgetMenu.getInstance().start();
        }else{
            Budget.getInstance().delete("name", input);
        }
    }
}
