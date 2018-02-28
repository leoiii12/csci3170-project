package com.exploration;


public class Main {

    public static void main(String[] args) {
        Terminal terminal = new Terminal();

        Menu currentMenu = new MainMenu(terminal);

        while (true) {
            currentMenu.print();
            currentMenu = currentMenu.nextMenu();

            if (currentMenu == null) {
                break;
            }
        }

    }
}

