package com.fidelisaboke.budgetron;

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

    }

}
