package com.fidelisaboke.budgetron;

import javax.swing.*;
import java.util.logging.Level;

public class BudgetronFrame extends JFrame {
    public static boolean inMainMenu;
    private static BudgetronFrame instance;
    private String errorMsg;
    private final String className = BudgetronFrame.class.getName();

    private BudgetronFrame(){

    }

    public static BudgetronFrame getInstance(){
        if(instance == null){
            instance = new BudgetronFrame();
        }

        return instance;
    }

    public void start(){
        inMainMenu = true;
        while(inMainMenu){
            String input = JOptionPane.showInputDialog(this, """
                Budgetron Finance Manager
                1. Budget
                2. Financial records
                3. Help
                0. Exit
                """, "Welcome", JOptionPane.PLAIN_MESSAGE);

            switch(input){
                case "1"->{
                    BudgetMenu.getInstance().start();
                    inMainMenu = false;
                }
                case "2"->{
                    FinRecordsMenu.getInstance().start();
                    inMainMenu = false;
                }
                case "3"->{
                    HelpMenu.getInstance().start();
                    inMainMenu = false;
                }
                case "0"-> {
                    System.out.println("Exiting...");
                    System.exit(1);
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
}
