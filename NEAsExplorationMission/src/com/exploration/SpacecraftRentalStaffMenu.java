package com.exploration;

import org.jooq.CSVFormat;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static com.exploration.jooq.Tables.SPACECRAFTRENTALRECORDS;
import static org.jooq.impl.DSL.count;

public class SpacecraftRentalStaffMenu extends OperationMenu {

    private final MainMenu mainMenu;

    public SpacecraftRentalStaffMenu(Terminal terminal, MainMenu mainMenu) {
        super(terminal);

        this.mainMenu = mainMenu;

        initializeOperations();
    }

    private void initializeOperations() {
        this.Operations.add(new Operation("Rent a spacecraft", 1, () -> {
            this.rentSpacecraft();

            return this.getCurrentMenu();
        }));
        this.Operations.add(new Operation("Return a spacecraft", 2, () -> {
            this.returnSpacecraft();

            return this.getCurrentMenu();
        }));
        this.Operations.add(new Operation("List all spacecrafts rented out (on a mission) for a certain period", 3, () -> {
            this.listRentedSpacecrafts();

            return this.getCurrentMenu();
        }));
        this.Operations.add(new Operation("List the number of spacecrafts currently rented out by each agency", 4, () -> {
            this.listNumberOfRentedSpacecraftsByAgency();

            return this.getCurrentMenu();
        }));
        this.Operations.add(new Operation("Return to the main menu", 0, this::getMainMenu));
    }

    private void rentSpacecraft() {
        this.terminal.display("Enter the agency name: ");
        String agencyName = this.terminal.readString();

        this.terminal.display("Enter the model id: ");
        String modelId = this.terminal.readString();

        this.terminal.display("Enter the spacecraft index: ");
        int spacecraftIndex = this.terminal.readInt();

        try (Connection conn = Database.getConnection()) {
            DSLContext create = Database.getContext(conn);

            Result<Record> result = create.select()
                    .from(SPACECRAFTRENTALRECORDS)
                    .where(SPACECRAFTRENTALRECORDS.AGENCYNAME.eq(agencyName)
                            .and(SPACECRAFTRENTALRECORDS.MODELID.eq(modelId))
                            .and(SPACECRAFTRENTALRECORDS.SPACECRAFTINDEX.eq(spacecraftIndex)))
                    .fetch();


            if (result.isEmpty()) {
                // TODO
            } else {
                for (Record record : result) {
                    Date returnDate = record.get(SPACECRAFTRENTALRECORDS.RETURNDATE);

                    if (returnDate == null) {
                        // TODO
                    } else {
                        create.update(SPACECRAFTRENTALRECORDS)
                                .set(SPACECRAFTRENTALRECORDS.CHECKOUTDATE, DSL.currentDate())
                                .set(SPACECRAFTRENTALRECORDS.RETURNDATE, (Date) null)
                                .where(SPACECRAFTRENTALRECORDS.AGENCYNAME.eq(agencyName)
                                        .and(SPACECRAFTRENTALRECORDS.MODELID.eq(modelId))
                                        .and(SPACECRAFTRENTALRECORDS.SPACECRAFTINDEX.eq(spacecraftIndex)))
                                .execute();

                        this.terminal.displayLine("Spacecraft rented successfully!");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void returnSpacecraft() {
        this.terminal.display("Enter the agency name: ");
        String agencyName = this.terminal.readString();

        this.terminal.display("Enter the model id: ");
        String modelId = this.terminal.readString();

        this.terminal.display("Enter the spacecraft index: ");
        int spacecraftIndex = this.terminal.readInt();

        // TODO if not exist

        try (Connection conn = Database.getConnection()) {
            DSLContext create = Database.getContext(conn);

            Result<Record> result = create.select()
                    .from(SPACECRAFTRENTALRECORDS)
                    .where(SPACECRAFTRENTALRECORDS.AGENCYNAME.eq(agencyName)
                            .and(SPACECRAFTRENTALRECORDS.MODELID.eq(modelId))
                            .and(SPACECRAFTRENTALRECORDS.SPACECRAFTINDEX.eq(spacecraftIndex)))
                    .fetch();


            if (result.isEmpty()) {
                // TODO
            } else {
                for (Record record : result) {
                    Date returnDate = record.get(SPACECRAFTRENTALRECORDS.RETURNDATE);

                    if (returnDate == null) {
                        create.update(SPACECRAFTRENTALRECORDS)
                                .set(SPACECRAFTRENTALRECORDS.RETURNDATE, DSL.currentDate())
                                .where(SPACECRAFTRENTALRECORDS.AGENCYNAME.eq(agencyName)
                                        .and(SPACECRAFTRENTALRECORDS.MODELID.eq(modelId))
                                        .and(SPACECRAFTRENTALRECORDS.SPACECRAFTINDEX.eq(spacecraftIndex)))
                                .execute();

                        this.terminal.displayLine("Spacecraft returned successfully!");
                    } else {
                        // TODO
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void listRentedSpacecrafts() {
        this.terminal.display("Type in the starting date [DD-MM-YYYY]: ");
        String startingDate = this.terminal.readString();

        this.terminal.display("Type in the ending date [DD-MM-YYYY]: ");
        String endingDate = this.terminal.readString();

        try (Connection conn = Database.getConnection()) {
            DSLContext create = Database.getContext(conn);

            Result result = create.select(SPACECRAFTRENTALRECORDS.AGENCYNAME, SPACECRAFTRENTALRECORDS.MODELID, SPACECRAFTRENTALRECORDS.SPACECRAFTINDEX, SPACECRAFTRENTALRECORDS.CHECKOUTDATE)
                    .from(SPACECRAFTRENTALRECORDS)
                    .where(SPACECRAFTRENTALRECORDS.RETURNDATE.isNull()
                            .and(SPACECRAFTRENTALRECORDS.CHECKOUTDATE.between(getDate(startingDate), getDate(endingDate))))
                    .orderBy(SPACECRAFTRENTALRECORDS.CHECKOUTDATE.desc())
                    .fetch();

            if (result.isEmpty()) {
                this.terminal.displayError("No spacecrafts fulfils the condition.");
            } else {
                this.terminal.display(result.formatCSV(new CSVFormat().nullString("null")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void listNumberOfRentedSpacecraftsByAgency() {
        try (Connection conn = Database.getConnection()) {
            DSLContext create = Database.getContext(conn);

            Result result = create.select(SPACECRAFTRENTALRECORDS.AGENCYNAME, count().as("Count"))
                    .from(SPACECRAFTRENTALRECORDS)
                    .where(SPACECRAFTRENTALRECORDS.RETURNDATE.isNull())
                    .groupBy(SPACECRAFTRENTALRECORDS.AGENCYNAME)
                    .fetch();

            this.terminal.display(result.formatCSV(new CSVFormat().nullString("null")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private java.sql.Date getDate(String date) {
        String[] dateParts = date.split("-");

        String formattedDate = dateParts[2] + "-" + dateParts[1] + "-" + dateParts[0];

        try {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            java.util.Date parsedDate = format.parse(formattedDate);

            return new java.sql.Date(parsedDate.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void print() {
        this.terminal.displayLine();
        this.terminal.displayLine("----- Spacecraft Rental Staff Menu -----");

        super.print();
    }

    public MainMenu getMainMenu() {
        return mainMenu;
    }

}