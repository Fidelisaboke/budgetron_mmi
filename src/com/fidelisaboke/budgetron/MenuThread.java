package com.fidelisaboke.budgetron;

import java.util.Scanner;

public class MenuThread extends Thread{
    Scanner input;
    public MenuThread(Scanner input){
        this.input = input;
    }
}
