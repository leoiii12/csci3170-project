package com.exploration;

public class ExplorationCompaniesMenu extends OperationMenu {

    private final MainMenu mainMenu;

    public ExplorationCompaniesMenu(Terminal terminal, MainMenu mainMenu) {
        super(terminal);

        this.mainMenu = mainMenu;
    }

    @Override
    public void print() {
        this.terminal.displayLine();
        this.terminal.displayLine("----- Exploration Companies Menu -----");

        super.print();
    }

    @Override
    public Menu nextMenu() {
        return null;
    }

}
