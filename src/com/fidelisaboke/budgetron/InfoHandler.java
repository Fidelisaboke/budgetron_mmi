package com.fidelisaboke.budgetron;

import javax.swing.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InfoHandler {
    public static void displayError(String className, String errorMsg){
        JOptionPane.showMessageDialog(
                BudgetronFrame.getInstance(),
                errorMsg,
                "An Error Occurred", JOptionPane.ERROR_MESSAGE);
        Logger.getLogger(className).log(Level.SEVERE, errorMsg);
    }
}
