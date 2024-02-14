package com.fidelisaboke.budgetron.ui;

import com.fidelisaboke.budgetron.menus.*;
import com.fidelisaboke.budgetron.utilities.MsgHandler;

import javax.swing.*;
import java.util.logging.Level;

/**
 * The program's frame. It's also handles the main menu functionality
 */
public class BudgetronFrame extends JFrame implements BaseMenu {
    private static BudgetronFrame instance;
    private final String className = BudgetronFrame.class.getName();

    private BudgetronFrame(){

    }

    public static BudgetronFrame getInstance(){
        if(instance == null){
            instance = new BudgetronFrame();
        }

        return instance;
    }

    @Override
    public void start(){
        MenuManager.setMenuStatus(MenuType.MAIN_MENU, true);
        while(MenuManager.isInMenu(MenuType.MAIN_MENU)){
            execute();
        }
    }

    @Override
    public void execute(){
        String input = JOptionPane.showInputDialog(this, """
                Budgetron Finance Manager
                1. Budget
                2. Financial records
                3. Help
                0. Exit
                """, "Welcome", JOptionPane.PLAIN_MESSAGE);

        handleOptionInput(input);
    }

    @Override
    public void handleOptionInput(String input) {
        switch(input){
            case "1"->{
                BudgetMenu.getInstance().start();
                MenuManager.setMenuStatus(MenuType.MAIN_MENU, false);
            }
            case "2"->{
                FinRecordsMenu.getInstance().start();
                MenuManager.setMenuStatus(MenuType.MAIN_MENU, false);
            }
            case "3"->{
                HelpMenu.getInstance().start();
                MenuManager.setMenuStatus(MenuType.MAIN_MENU, false);
            }
            case "0"-> {
                System.out.println("Exiting...");
                System.exit(1);
            }
            default -> MsgHandler.displayMessage(
                    "Invalid Option",
                    "Invalid option. Try again.",
                    className,
                    Level.SEVERE);
        }
    }
}
