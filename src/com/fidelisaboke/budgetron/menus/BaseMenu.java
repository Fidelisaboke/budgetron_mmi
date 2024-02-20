package com.fidelisaboke.budgetron.menus;

public interface BaseMenu {
    /**
     * Begins monitoring the menu, checking whether it is active or not
     */
    void start();

    /**
     * Runs the menu, once it's activated (either by an option or by running the program)
     */
    void execute();

    /**
     * Handles the input received from the menu, normally to control options
     */
    void handleOptionInput(String input);
}
