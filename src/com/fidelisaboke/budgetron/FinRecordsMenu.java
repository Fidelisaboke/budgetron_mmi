package com.fidelisaboke.budgetron;

import javax.swing.*;
import java.util.logging.Level;

public class FinRecordsMenu {
    private static FinRecordsMenu instance;
    private final String className = FinRecordsMenu.class.getName();

    private FinRecordsMenu(){

    }

    public static FinRecordsMenu getInstance(){
        if(instance == null){
            instance = new FinRecordsMenu();
        }
        return instance;
    }

    public void start(){
        String input = JOptionPane.showInputDialog(BudgetronFrame.getInstance(), """
               Financial Records Menu
               1. Personal financial records
               2. Business financial records
               """);

        switch (input){
            case "1"->{
                System.out.println("Personal financial records");
            }
            case "2"->{
                System.out.println("Business financial records");
            }
            default->{
                MsgHandler.displayMessage("Invalid Option", "Please select a valid option.", className, Level.SEVERE);
            }
        }
    }

}
