package com.fidelisaboke.budgetron.menus;

import com.fidelisaboke.budgetron.ui.BudgetronFrame;
import com.fidelisaboke.budgetron.utilities.MsgHandler;

import javax.swing.*;
import java.util.logging.Level;

public class HelpMenu implements BaseMenu {
    private static HelpMenu instance;
    private final String className = FinRecordsMenu.class.getName();

    private HelpMenu(){

    }

    public static HelpMenu getInstance(){
        if(instance == null){
            instance = new HelpMenu();
        }
        return instance;
    }

    @Override
    public void start(){
        MenuManager.setMenuStatus(MenuType.HELP_MENU, true);
        while(MenuManager.isInMenu(MenuType.HELP_MENU)){
            execute();
        }
    }

    @Override
    public void execute() {
        String input = JOptionPane.showInputDialog(
                BudgetronFrame.getInstance(),
                """
                        Help
                        1. About
                        2. Navigating the program
                        0. Back""",
                "Help Menu",
                JOptionPane.PLAIN_MESSAGE);

        handleOptionInput(input);
    }

    @Override
    public void handleOptionInput(String input) {
        switch(input){
            case "1"->{
                String msgAbout = """
                        This is a Java program that helps
                        you manage your finances, emulating
                        the MMI system used by mobile
                        carriers.""";
                MsgHandler.displayMessage(
                        "About",
                        msgAbout,
                        className,
                        Level.INFO
                        );
            }
            case "2"->{
                String msgNav= """
                        Navigate by entering the
                        numbers that correspond to the option
                        provided. When asked for input, use
                        the input box given.""";
                MsgHandler.displayMessage(
                        "Navigation",
                        msgNav,
                        className,
                        Level.INFO
                );
            }
            case "0"->{
                MenuManager.setMenuStatus(MenuType.HELP_MENU, false);
                BudgetronFrame.getInstance().start();
            }
            default->{
                MsgHandler.displayMessage(
                        "Invalid option",
                        "Please select a valid option.",
                        className,
                        Level.SEVERE
                );
            }
        }
    }

}
