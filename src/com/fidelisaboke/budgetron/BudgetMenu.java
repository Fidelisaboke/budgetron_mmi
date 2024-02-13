package com.fidelisaboke.budgetron;

import javax.swing.*;
import java.util.logging.Level;

public class BudgetMenu {
    private static BudgetMenu instance;
    private final String className = BudgetMenu.class.getName();
    private BudgetMenu(){

    }

    public static BudgetMenu getInstance(){
        if(instance == null){
            instance = new BudgetMenu();
        }
        return instance;
    }

    public void start(){
        String input = JOptionPane.showInputDialog(BudgetronFrame.getInstance(), """
                Manage Budget
                1. Display budgets
                2. Add budget
                3. Update budget
                4. Delete budget
                0. Back to Main Menu""", "Budget Menu", JOptionPane.PLAIN_MESSAGE);

        switch(input){
            case "1"->{
                System.out.println("Display budgets");
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
}
