package com.fidelisaboke.budgetron;

import javax.swing.*;
import java.util.Scanner;

public class BudgetronFrame extends JFrame {
    private static BudgetronFrame instance;
    private final Scanner input = new Scanner(System.in);

    private BudgetronFrame(){

    }

    // Runs the application
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
                    System.out.println("Budget");
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

    public static BudgetronFrame getInstance(){
        if(instance == null){
            instance = new BudgetronFrame();
        }

        return instance;
    }
}
