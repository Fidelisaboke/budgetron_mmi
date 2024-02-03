import javax.swing.*;
import java.util.Scanner;

public class BudgetonFrame extends JFrame {
    private static BudgetonFrame instance;
    private final Scanner input = new Scanner(System.in);

    private BudgetonFrame(){

    }

    // Run the application
    public void start(){
        JOptionPane.showMessageDialog(this, """
                Budgetron Finance Manager
                1. Sign in
                2. Register
                3. Help
                0. Exit
                """);

        String option = input.nextLine();

        switch(option){
            case "1"->{
                System.out.println("Sign in");
            }
            case "2"->{
                System.out.println("Register");
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

    public static BudgetonFrame getInstance(){
        if(instance == null){
            instance = new BudgetonFrame();
        }

        return instance;
    }
}
