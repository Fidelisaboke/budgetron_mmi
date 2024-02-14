package com.fidelisaboke.budgetron.menus;

public class HelpMenu implements BaseMenu {
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

    @Override
    public void start(){

    }

    @Override
    public void execute() {

    }

    @Override
    public void handleOptionInput(String input) {

    }

}
