package com.exploration;


public class Main {

    public static void main(String[] args) {
        configure();

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

    private static void configure() {
        System.getProperties().setProperty("org.jooq.no-logo", "true");
    }
}

