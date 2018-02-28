package com.exploration;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdministratorMenu extends OperationMenu {

    private final MainMenu mainMenu;

    AdministratorMenu(Terminal terminal, MainMenu mainMenu) {
        super(terminal);

        this.mainMenu = mainMenu;

        initializeOperations();
    }

    private void initializeOperations() {
        this.Operations.add(new Operation("Create all tables", 1, () -> {
            this.createAllTables();

            return this.getCurrentMenu();
        }));
        this.Operations.add(new Operation("Delete all tables", 2, () -> {
            this.deleteAllTables();

            return this.getCurrentMenu();
        }));
        this.Operations.add(new Operation("Load data from a dataset", 3, () -> {
            this.loadData();

            return this.getCurrentMenu();
        }));
        this.Operations.add(new Operation("Show number of records in each table", 4, () -> {
            this.showNumOfRecordsInAllTables();

            return this.getCurrentMenu();
        }));
        this.Operations.add(new Operation("Return to the main menu", 0, this::getMainMenu));
    }

    @Override
    public void print() {
        this.terminal.displayLine();
        this.terminal.displayLine("----- Administrator Menu -----");

        super.print();
    }

    private void createAllTables() {
        this.terminal.displayLine("Processing...");

        List<String> sqls = new ArrayList<>();

        sqls.add("CREATE TABLE Resource (\n" +
                "    RType CHAR(2) PRIMARY KEY,\n" +
                "    Density REAL,\n" +
                "    Value REAL\n" +
                ");");
        sqls.add("CREATE TABLE NEA (\n" +
                "    NID CHAR(10) PRIMARY KEY,\n" +
                "    Distance REAL,\n" +
                "    Family CHAR(6),\n" +
                "    Duration INTEGER(3),\n" +
                "    Energy REAL,\n" +
                "    Rtype CHAR(2) DEFAULT NULL,\n" +
                "    FOREIGN KEY (Rtype) REFERENCES Resource(RType)\n" +
                ");");
        sqls.add("CREATE TABLE Spacecraft_Model (\n" +
                "    Agency CHAR(4),\n" +
                "    MID CHAR(4),\n" +
                "    Num INTEGER(2),\n" +
                "    Charge INTEGER(5),\n" +
                "    Duration INTEGER(3),\n" +
                "    Energy REAL,\n" +
                "    Capacity INTEGER(2),\n" +
                "    Type CHAR(1),\n" +
                "    PRIMARY KEY (Agency, MID)\n" +
                ");");
        sqls.add("CREATE TABLE RentalRecord (\n" +
                "    Agency CHAR(4),\n" +
                "    MID CHAR(4),\n" +
                "    SNum INTEGER(2),\n" +
                "    CheckoutDate DATE,\n" +
                "    ReturnDate DATE DEFAULT NULL,\n" +
                "    PRIMARY KEY (Agency, MID, SNum),\n" +
                "    FOREIGN KEY (Agency, MID)\n" +
                "    REFERENCES Spacecraft_Model(Agency, MID)\n" +
                ");");

        try {
            Database.executeSqls(sqls);

            this.terminal.displayLine("Done. Created all tables.");
        } catch (SQLException e) {
            this.terminal.displayLine("Failed." + e.getMessage());
        }
    }

    private void deleteAllTables() {
        this.terminal.displayLine("Processing...");

        List<String> sqls = new ArrayList<>();

        sqls.add("DROP TABLE RentalRecord;");
        sqls.add("DROP TABLE Spacecraft_Model;");
        sqls.add("DROP TABLE NEA;");
        sqls.add("DROP TABLE Resource;");

        try {
            Database.executeSqls(sqls);

            this.terminal.displayLine("Done. Deleted all tables.");
        } catch (SQLException e) {
            this.terminal.displayLine("Failed." + e.getMessage());
        }
    }

    private void loadData() {

    }

    private void showNumOfRecordsInAllTables() {

    }

    public MainMenu getMainMenu() {
        return mainMenu;
    }
}
