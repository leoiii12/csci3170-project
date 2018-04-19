package com.exploration;

import org.jooq.CSVFormat;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.impl.DSL;
import org.jooq.util.mariadb.MariaDBDataType;
import org.jooq.util.mysql.MySQLDataType;

import java.sql.Connection;
import java.sql.SQLException;

import static com.exploration.jooq.Tables.*;

public class ExplorationCompaniesMenu extends OperationMenu {

    private final MainMenu mainMenu;

    public ExplorationCompaniesMenu(Terminal terminal, MainMenu mainMenu) {
        super(terminal);

        this.mainMenu = mainMenu;

        initializeOperations();
    }

    private void initializeOperations() {
        this.Operations.add(new Operation("Search for NEAs based on some criteria", 1, this::getNearEarthAsteroidSearchMenu));
        this.Operations.add(new Operation("Search for spacecrafts based on some criteria", 2, this::getSpacecraftSearchMenu));
        this.Operations.add(new Operation("A certain NEA exploration mission design", 3, () -> {
            this.listSpacecraftForMission();

            return this.getCurrentMenu();
        }));
        this.Operations.add(new Operation("The most beneficial NEA exploration mission design", 4, () -> {
            this.listMostBeneficialSpacecraftForMission();

            return this.getCurrentMenu();
        }));
        this.Operations.add(new Operation("Return to the main menu", 0, this::getMainMenu));
    }

    @Override
    public void print() {
        this.terminal.displayLine();
        this.terminal.displayLine("----- Exploration Companies Menu -----");

        super.print();
    }

    private NearEarthAsteroidSearchMenu getNearEarthAsteroidSearchMenu() {
        return new NearEarthAsteroidSearchMenu(this.terminal, this);
    }

    private SpacecraftSearchMenu getSpacecraftSearchMenu() {
        return new SpacecraftSearchMenu(this.terminal, this);
    }

    private void listSpacecraftForMission() {
        this.terminal.display("Type in the NEA id: ");
        String neaId = this.terminal.readString();

        try (Connection conn = Database.getConnection()) {
            DSLContext create = Database.getContext(conn);

            Record record = null;

            record = create
                    .select()
                    .from(NEAREARTHASTEROIDS)
                    .where(NEAREARTHASTEROIDS.NEAID.eq(neaId))
                    .fetchOne();

            if (record == null) {
                throw new Exception("No NEA fulfils the condition.");
            }

            double distance = record.get(NEAREARTHASTEROIDS.DISTANCE);
            String family = record.get(NEAREARTHASTEROIDS.FAMILY);
            int minDuration = record.get(NEAREARTHASTEROIDS.MINDURATION);
            double minEnergy = record.get(NEAREARTHASTEROIDS.MINENERGY);
            String resourceType = record.get(NEAREARTHASTEROIDS.RESOURCETYPE);

            record = create
                    .select()
                    .from(RESOURCES)
                    .where(RESOURCES.TYPE.eq(resourceType))
                    .fetchOne();

            if (record == null) {
                throw new Exception("No resource fulfils the condition.");
            }

            double density = record.get(RESOURCES.DENSITY);
            double value = record.get(RESOURCES.VALUE);

            Result result = create
                    .select(SPACECRAFTS.AGENCYNAME,
                            SPACECRAFTS.MODELID,
                            SPACECRAFTRENTALRECORDS.SPACECRAFTINDEX,
                            SPACECRAFTS.DAYCHARGE.multiply(minDuration).as("Cost"),
                            SPACECRAFTS.MAXCAPACITY.cast(MariaDBDataType.BIGINT).multiply(value * density * 1000000).subtract(SPACECRAFTS.DAYCHARGE.multiply(minDuration)).as("Benefit"))
                    .from(SPACECRAFTRENTALRECORDS
                            .leftJoin(SPACECRAFTS)
                            .on(SPACECRAFTRENTALRECORDS.AGENCYNAME.eq(SPACECRAFTS.AGENCYNAME)
                                    .and(SPACECRAFTRENTALRECORDS.MODELID.eq(SPACECRAFTS.MODELID))))
                    .where(SPACECRAFTS.TYPE.eq("A")
                            .and(SPACECRAFTS.MAXTRIPTIME.ge(minDuration))
                            .and(SPACECRAFTS.MAXTRIPENERGY.ge(minEnergy))
                            .and(SPACECRAFTRENTALRECORDS.RETURNDATE.isNotNull()))
                    .orderBy(DSL.field("Benefit").desc())
                    .fetch();

            if (result.isEmpty()) {
                throw new Exception("No plan fulfils the conditions.");
            } else {
                this.terminal.display(result.formatCSV(new CSVFormat().nullString("null")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            this.terminal.displayError(e.getMessage());
        }
    }

    private void listMostBeneficialSpacecraftForMission() {
        this.terminal.display("Type in the budget: ");
        int budget = this.terminal.readInt();

        this.terminal.display("Type in the resource type: ");
        String resourceType = this.terminal.readString();

        try (Connection conn = Database.getConnection()) {
            DSLContext create = Database.getContext(conn);

            Record record = create
                    .select()
                    .from(RESOURCES)
                    .where(RESOURCES.TYPE.eq(resourceType))
                    .fetchOne();

            if (record == null) {
                throw new Exception("No resource type is matched.");
            }

            double density = record.get(RESOURCES.DENSITY);
            double value = record.get(RESOURCES.VALUE);

            Result result = create
                    .select(NEAREARTHASTEROIDS.NEAID,
                            NEAREARTHASTEROIDS.FAMILY,
                            SPACECRAFTS.AGENCYNAME,
                            SPACECRAFTS.MODELID,
                            SPACECRAFTRENTALRECORDS.SPACECRAFTINDEX,
                            NEAREARTHASTEROIDS.MINDURATION,
                            SPACECRAFTS.DAYCHARGE.multiply(NEAREARTHASTEROIDS.MINDURATION).as("Cost"),
                            SPACECRAFTS.MAXCAPACITY.cast(MySQLDataType.BIGINT).multiply(value * density * 1000000).subtract(SPACECRAFTS.DAYCHARGE.multiply(NEAREARTHASTEROIDS.MINDURATION)).as("Benefit"))
                    .from(SPACECRAFTRENTALRECORDS
                            .leftJoin(SPACECRAFTS)
                            .on(SPACECRAFTRENTALRECORDS.AGENCYNAME.eq(SPACECRAFTS.AGENCYNAME)
                                    .and(SPACECRAFTRENTALRECORDS.MODELID.eq(SPACECRAFTS.MODELID)))
                            .join(NEAREARTHASTEROIDS)
                            .on(SPACECRAFTS.MAXTRIPTIME.ge(NEAREARTHASTEROIDS.MINDURATION)
                                    .and(SPACECRAFTS.MAXTRIPENERGY.ge(NEAREARTHASTEROIDS.MINENERGY))))
                    .where(SPACECRAFTS.TYPE.eq("A")
                            .and(SPACECRAFTRENTALRECORDS.RETURNDATE.isNotNull())
                            .and(NEAREARTHASTEROIDS.RESOURCETYPE.eq(resourceType)))
                    .having(DSL.field("Cost").le(budget))
                    .orderBy(DSL.field("Benefit").desc())
                    .limit(1)
                    .fetch();

            if (result.isEmpty()) {
                throw new Exception("No plan fulfils the conditions.");
            } else {
                this.terminal.display(result.formatCSV(new CSVFormat().nullString("null")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            this.terminal.displayError(e.getMessage());
        }
    }

    private MainMenu getMainMenu() {
        return mainMenu;
    }
}

class NearEarthAsteroidSearchMenu extends OperationMenu {

    private final Menu parentMenu;

    NearEarthAsteroidSearchMenu(Terminal terminal, Menu parentMenu) {
        super(terminal);

        this.parentMenu = parentMenu;

        initializeOperations();
    }

    private void initializeOperations() {
        this.Operations.add(new Operation("ID", 1, () -> {
            this.getByID();

            return this.getParentMenu();
        }));
        this.Operations.add(new Operation("Family", 2, () -> {
            this.getByFamily();

            return this.getParentMenu();
        }));
        this.Operations.add(new Operation("Resource type", 3, () -> {
            this.getByResourceType();

            return this.getParentMenu();
        }));
    }

    @Override
    public void print() {
        this.terminal.displayLine();
        this.terminal.displayLine("Choose the search criterion: ");

        super.printOperations();
    }

    private void getByID() {
        this.terminal.display("Type in the id: ");
        String id = this.terminal.readString();

        try (Connection conn = Database.getConnection()) {
            DSLContext create = Database.getContext(conn);

            Result<Record> result = create
                    .select()
                    .from(NEAREARTHASTEROIDS)
                    .where(NEAREARTHASTEROIDS.NEAID.eq(id))
                    .fetch();

            if (result.isEmpty()) {
                this.terminal.displayError("No nea fulfils the condition.");
            } else {
                this.terminal.display(result.formatCSV(new CSVFormat().nullString("null")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            this.terminal.displayError(e.getMessage());
        }
    }

    private void getByFamily() {
        this.terminal.display("Type in the family: ");
        String family = this.terminal.readString();

        try (Connection conn = Database.getConnection()) {
            DSLContext create = Database.getContext(conn);

            Result<Record> result = create
                    .select()
                    .from(NEAREARTHASTEROIDS)
                    .where(NEAREARTHASTEROIDS.FAMILY.eq(family))
                    .fetch();

            if (result.isEmpty()) {
                this.terminal.displayError("No nea fulfils the condition.");
            } else {
                this.terminal.display(result.formatCSV(new CSVFormat().nullString("null")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            this.terminal.displayError(e.getMessage());
        }
    }

    private void getByResourceType() {
        this.terminal.display("Type in the resource type: ");
        String nid = this.terminal.readString();

        try (Connection conn = Database.getConnection()) {
            DSLContext create = Database.getContext(conn);

            Result<Record> result = create
                    .select()
                    .from(NEAREARTHASTEROIDS)
                    .where(NEAREARTHASTEROIDS.RESOURCETYPE.eq(nid))
                    .fetch();

            if (result.isEmpty()) {
                this.terminal.displayError("No nea fulfils the condition.");
            } else {
                this.terminal.display(result.formatCSV(new CSVFormat().nullString("null")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            this.terminal.displayError(e.getMessage());
        }
    }

    private Menu getParentMenu() {
        return parentMenu;
    }

}

class SpacecraftSearchMenu extends OperationMenu {

    private final Menu parentMenu;

    SpacecraftSearchMenu(Terminal terminal, Menu parentMenu) {
        super(terminal);

        this.parentMenu = parentMenu;

        initializeOperations();
    }

    private void initializeOperations() {
        this.Operations.add(new Operation("Agency name", 1, () -> {
            this.getByAgencyName();

            return this.getParentMenu();
        }));
        this.Operations.add(new Operation("Type", 2, () -> {
            this.getByType();

            return this.getParentMenu();
        }));
        this.Operations.add(new Operation("Least energy", 3, () -> {
            this.getByLeastEnergy();

            return this.getParentMenu();
        }));
        this.Operations.add(new Operation("Least working time", 4, () -> {
            this.getByLeastWorkingTime();

            return this.getParentMenu();
        }));
        this.Operations.add(new Operation("Least capacity", 5, () -> {
            this.getByLeastCapacity();

            return this.getParentMenu();
        }));
    }

    @Override
    public void print() {
        this.terminal.displayLine();
        this.terminal.displayLine("Choose the search criterion: ");

        super.printOperations();
    }

    private void getByAgencyName() {
        this.terminal.display("Type in the agency name: ");
        String agencyName = this.terminal.readString();

        try (Connection conn = Database.getConnection()) {
            DSLContext create = Database.getContext(conn);

            Result<Record> result = create.select()
                    .from(SPACECRAFTS)
                    .where(SPACECRAFTS.AGENCYNAME.eq(agencyName))
                    .fetch();

            if (result.isEmpty()) {
                this.terminal.displayError("No spacecraft fulfils the condition.");
            } else {
                this.terminal.display(result.formatCSV(new CSVFormat().nullString("null")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            this.terminal.displayError(e.getMessage());
        }
    }

    private void getByType() {
        this.terminal.display("Type in the type: ");
        String type = this.terminal.readString();

        try (Connection conn = Database.getConnection()) {
            DSLContext create = Database.getContext(conn);

            Result<Record> result = create.select()
                    .from(SPACECRAFTS)
                    .where(SPACECRAFTS.TYPE.eq(type))
                    .fetch();

            if (result.isEmpty()) {
                this.terminal.displayError("No spacecraft fulfils the condition.");
            } else {
                this.terminal.display(result.formatCSV(new CSVFormat().nullString("null")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            this.terminal.displayError(e.getMessage());
        }
    }

    private void getByLeastEnergy() {
        this.terminal.display("Type in the least energy: ");
        double leastEnergy = this.terminal.readDouble();

        try (Connection conn = Database.getConnection()) {
            DSLContext create = Database.getContext(conn);

            Result<Record> result = create.select()
                    .from(SPACECRAFTS)
                    .where(SPACECRAFTS.MAXTRIPENERGY.ge(leastEnergy))
                    .fetch();

            if (result.isEmpty()) {
                this.terminal.displayError("No spacecraft fulfils the condition.");
            } else {
                this.terminal.display(result.formatCSV(new CSVFormat().nullString("null")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            this.terminal.displayError(e.getMessage());
        }
    }

    private void getByLeastWorkingTime() {
        this.terminal.display("Type in the least working time: ");
        int leastWorkingTime = this.terminal.readInt();

        try (Connection conn = Database.getConnection()) {
            DSLContext create = Database.getContext(conn);

            Result<Record> result = create.select()
                    .from(SPACECRAFTS)
                    .where(SPACECRAFTS.MAXTRIPTIME.ge(leastWorkingTime))
                    .fetch();

            if (result.isEmpty()) {
                this.terminal.displayError("No spacecraft fulfils the condition.");
            } else {
                this.terminal.display(result.formatCSV(new CSVFormat().nullString("null")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            this.terminal.displayError(e.getMessage());
        }
    }

    private void getByLeastCapacity() {
        this.terminal.display("Type in the least capacity: ");
        int leastCapacity = this.terminal.readInt();

        try (Connection conn = Database.getConnection()) {
            DSLContext create = Database.getContext(conn);

            Result<Record> result = create.select()
                    .from(SPACECRAFTS)
                    .where(SPACECRAFTS.MAXCAPACITY.ge(leastCapacity))
                    .fetch();

            if (result.isEmpty()) {
                this.terminal.displayError("No spacecraft fulfils the condition.");
            } else {
                this.terminal.display(result.formatCSV(new CSVFormat().nullString("null")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            this.terminal.displayError(e.getMessage());
        }
    }

    private Menu getParentMenu() {
        return parentMenu;
    }

}
