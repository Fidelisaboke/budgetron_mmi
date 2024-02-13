package com.fidelisaboke.budgetron;

public class HelpMenu {
    private static HelpMenu instance;
    private final String className = FinRecordsMenu.class.getName();

    private HelpMenu(){

    }

    public static HelpMenu getInstance(){
        if(instance == null){
            instance = new HelpMenu();
        }
        return instance;
    }

    public void start(){

    }

}
