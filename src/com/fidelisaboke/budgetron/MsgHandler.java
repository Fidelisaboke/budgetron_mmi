package com.fidelisaboke.budgetron;

import javax.swing.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Handles and displays warnings, information, errors, and other program messages.
 */
public class MsgHandler {

    public static void displayMessage(String msgTitle, String msgBody, String className){
        JOptionPane.showMessageDialog(
                BudgetronFrame.getInstance(),
                msgBody,
                msgTitle,
                JOptionPane.INFORMATION_MESSAGE);
        Logger.getLogger(className).log(Level.INFO, msgBody);
    }
}
