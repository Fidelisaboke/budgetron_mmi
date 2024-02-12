package com.fidelisaboke.budgetron;

import javax.swing.*;
import java.sql.SQLException;
import java.util.logging.Level;

public class BudgetronFrame extends JFrame {
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
        while(true){
            String input = JOptionPane.showInputDialog(this, """
                Budgetron Finance Manager
                1. Budget
                2. Financial records
                3. Help
                0. Exit
                """, "Welcome", JOptionPane.PLAIN_MESSAGE);

            switch(input){
                case "1"->{
                }
                case "2"->{
                    System.out.println("Financial Records");
                }
                case "3"->{
                    System.out.println("Help");
                }
                case "0"-> {
                    System.out.println("Exiting...");
                    System.exit(1);
                }
                default->{
                    System.out.println("Invalid option. Try again");
                }
            }
        }
    }
}
