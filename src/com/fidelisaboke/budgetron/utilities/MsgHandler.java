package com.fidelisaboke.budgetron.utilities;

import com.fidelisaboke.budgetron.ui.BudgetronFrame;

import javax.swing.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Handles and displays warnings, information, errors, and other program messages.
 */
public class MsgHandler {

    /**
     *
     * @param msgTitle Title of the message to be displayed
     * @param msgBody The content of the message
     * @param className The name of the class that calls this function
     * @param level The severity of the message conveyed (based on Java.util.logging.Level)
     */
    public static void displayMessage(String msgTitle, String msgBody, String className, Level level){
        int msgType = getMessageType(level);

        JOptionPane.showMessageDialog(
                BudgetronFrame.getInstance(),
                msgBody,
                msgTitle,
                msgType);
        Logger.getLogger(className).log(level, msgBody);
    }

    private static int getMessageType(Level level){
        if(level == Level.SEVERE){
            return JOptionPane.ERROR_MESSAGE;
        }else if(level == Level.INFO){
            return JOptionPane.INFORMATION_MESSAGE;
        }else if(level == Level.WARNING){
            return JOptionPane.WARNING_MESSAGE;
        }else{
            return JOptionPane.PLAIN_MESSAGE;
        }
    }
}
