package com.exploration;

public class MainMenu extends OperationMenu {

    MainMenu(Terminal terminal) {
        super(terminal);

        this.Operations.add(new Operation("Operations for administrator", 1, this::getAdministratorMenu));
        this.Operations.add(new Operation("Operations for exploration companies (rental customers)", 2, this::getExplorationCompaniesMenu));
        this.Operations.add(new Operation("Operations for spacecraft rental staff", 3, this::getSpacecraftRentalStaffMenu));
        this.Operations.add(new Operation("Exit this program", 0, () -> null));
    }

    @Override
    public void print() {
        this.terminal.displayLine();
        this.terminal.displayLine("----- Main Menu -----");

        super.print();
    }

    private Menu getAdministratorMenu() {
        return new AdministratorMenu(this.terminal, this);
    }

    private Menu getExplorationCompaniesMenu() {
        return new ExplorationCompaniesMenu(this.terminal, this);
    }

    private Menu getSpacecraftRentalStaffMenu() {
        return new SpacecraftRentalStaffMenu(this.terminal, this);
    }

}
