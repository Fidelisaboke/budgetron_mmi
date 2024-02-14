package com.fidelisaboke.budgetron.menus;

import com.fidelisaboke.budgetron.ui.BudgetronFrame;
import com.fidelisaboke.budgetron.utilities.MsgHandler;

import javax.swing.*;
import java.util.logging.Level;

public class BudgetMenu implements BaseMenu {
    private static BudgetMenu instance;
    private final String className = BudgetMenu.class.getName();
    private BudgetMenu(){}

    public static BudgetMenu getInstance(){
        if(instance == null){
            instance = new BudgetMenu();
        }
        return instance;
    }

    @Override
    public void start(){
        MenuManager.setMenuStatus(MenuType.BUDGET_MENU,true);
        while(MenuManager.isInMenu(MenuType.BUDGET_MENU)){
            execute();
        }
    }

    @Override
    public void execute(){
        String input = JOptionPane.showInputDialog(BudgetronFrame.getInstance(), """
                Manage Budget
                1. Get budgets
                2. Add budget
                3. Update budget
                4. Delete budget
                0. Back to Main Menu""", "Budget Menu", JOptionPane.PLAIN_MESSAGE);

        handleOptionInput(input);

    }

    @Override
    public void handleOptionInput(String input) {
        switch(input){
            case "1"->{
                GetBudgetsMenu.getInstance().start();
                MenuManager.setMenuStatus(MenuType.BUDGET_MENU, false);
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
                BudgetronFrame.getInstance().start();
                MenuManager.setMenuStatus(MenuType.BUDGET_MENU, false);
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



}
