package com.fidelisaboke.budgetron.menus;

import java.util.HashMap;
import java.util.Map;

public class MenuManager {
    private static final Map<MenuType, Boolean> menuStatus = new HashMap<>();


    // Initial values set to 'false', ensuring all menus are 'inactive' on running the program
    static{
        for(MenuType type: MenuType.values()){
            menuStatus.put(type, false);
        }
    }

    public static boolean isInMenu(MenuType type){
        return menuStatus.get(type);
    }

    public static void setMenuStatus(MenuType type, boolean status){
        menuStatus.put(type, status);
    }

}
