package com.fidelisaboke.budgetron.menus;

import com.fidelisaboke.budgetron.utilities.MsgHandler;
import com.fidelisaboke.budgetron.ui.BudgetronFrame;

import javax.swing.*;
import java.util.logging.Level;

public class FinRecordsMenu implements BaseMenu {
    private static FinRecordsMenu instance;
    private final String className = FinRecordsMenu.class.getName();

    private FinRecordsMenu(){}

    public static FinRecordsMenu getInstance(){
        if(instance == null){
            instance = new FinRecordsMenu();
        }
        return instance;
    }

    @Override
    public void start(){
        MenuManager.setMenuStatus(MenuType.FIN_RECORDS_MENU, true);
        while(MenuManager.isInMenu(MenuType.FIN_RECORDS_MENU)){
            execute();
        }
    }

    @Override
    public void execute() {
        String input = JOptionPane.showInputDialog(BudgetronFrame.getInstance(), """
                Financial Records Menu
                1. Personal financial records
                2. Business financial records
                0. Back to main menu
                """, "Financial Records", JOptionPane.PLAIN_MESSAGE);

        handleOptionInput(input);
    }

    @Override
    public void handleOptionInput(String input) {
        switch (input) {
            /* Todo: Implement functionality for handling financial records
            - Synchronize with the budget management functionality
            - Create database entities for managing financial records
             */
            case "1" -> System.out.println("Personal financial records");
            case "2" -> System.out.println("Business financial records");
            case "0" -> {
                MenuManager.setMenuStatus(MenuType.FIN_RECORDS_MENU, false);
                BudgetronFrame.getInstance().start();
            }
            default -> MsgHandler.displayMessage("Invalid Option", "Please select a valid option.", className, Level.SEVERE);

        }
    }
}
