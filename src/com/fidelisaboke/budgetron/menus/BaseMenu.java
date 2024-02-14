package com.fidelisaboke.budgetron.menus;

public interface BaseMenu {
    /**
     * Begins monitoring the menu, checking whether it is active or not
     */
    public void start();

    /**
     * Runs the menu, once it's activated (either by an option or by running the program)
     */
    public void execute();

    /**
     * Handles the input received from the menu, normally to control options
     */
    public void handleOptionInput(String input);
}
