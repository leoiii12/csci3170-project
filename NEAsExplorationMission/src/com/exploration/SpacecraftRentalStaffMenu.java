package com.exploration;

public class SpacecraftRentalStaffMenu extends OperationMenu {

    private final MainMenu mainMenu;

    public SpacecraftRentalStaffMenu(Terminal terminal, MainMenu mainMenu) {
        super(terminal);

        this.mainMenu = mainMenu;
    }

    @Override
    public void print() {
        this.terminal.displayLine();
        this.terminal.displayLine("----- Spacecraft Rental Staff Menu -----");

        super.print();
    }

    @Override
    public Menu nextMenu() {
        return getMainMenu();
    }

    public MainMenu getMainMenu() {
        return mainMenu;
    }

}
