package com.exploration;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.jooq.DSLContext;
import org.jooq.Query;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.exploration.jooq.Tables.*;

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

        sqls.add("CREATE TABLE Resources (\n" +
                "    Type CHAR(2) PRIMARY KEY,\n" +
                "    Density REAL,\n" +
                "    Value REAL\n" +
                ");");
        sqls.add("CREATE TABLE NearEarthAsteroids (\n" +
                "    NeaId CHAR(10) PRIMARY KEY,\n" +
                "    Distance REAL,\n" +
                "    Family CHAR(6),\n" +
                "    MinDuration INTEGER(3),\n" +
                "    MinEnergy REAL,\n" +
                "    ResourceType CHAR(2) DEFAULT NULL,\n" +
                "    FOREIGN KEY (ResourceType) REFERENCES Resources(Type)\n" +
                ");");
        sqls.add("CREATE TABLE Spacecrafts (\n" +
                "    AgencyName CHAR(4),\n" +
                "    ModelId CHAR(4),\n" +
                "    Count INTEGER(2),\n" +
                "    DayCharge INTEGER(5),\n" +
                "    MaxTripTime INTEGER(3),\n" +
                "    MaxTripEnergy REAL,\n" +
                "    MaxCapacity INTEGER(2),\n" +
                "    Type CHAR(1),\n" +
                "    PRIMARY KEY (AgencyName, ModelId)\n" +
                ");");
        sqls.add("CREATE TABLE SpacecraftRentalRecords (\n" +
                "    AgencyName CHAR(4),\n" +
                "    ModelId CHAR(4),\n" +
                "    SpacecraftIndex INTEGER(2),\n" +
                "    CheckoutDate DATE,\n" +
                "    ReturnDate DATE DEFAULT NULL,\n" +
                "    PRIMARY KEY (AgencyName, ModelId, SpacecraftIndex),\n" +
                "    FOREIGN KEY (AgencyName, ModelId)\n" +
                "    REFERENCES Spacecrafts(AgencyName, ModelId)\n" +
                ");");

        try {
            Database.executeSqls(sqls);

            this.terminal.displayLine("Done. Created all tables, Resources, NearEarthAsteroids, Spacecrafts, SpacecraftRentalRecords.");
        } catch (SQLException e) {
            this.terminal.displayError("Failed. " + e.getMessage());
        }
    }

    private void deleteAllTables() {
        this.terminal.displayLine("Processing...");

        List<String> sqls = new ArrayList<>();

        sqls.add("DROP TABLE SpacecraftRentalRecords;");
        sqls.add("DROP TABLE Spacecrafts;");
        sqls.add("DROP TABLE NearEarthAsteroids;");
        sqls.add("DROP TABLE Resources;");

        try {
            Database.executeSqls(sqls);

            this.terminal.displayLine("Done. Deleted all tables.");
        } catch (SQLException e) {
            this.terminal.displayError("Failed. " + e.getMessage());
        }
    }

    private void loadData() {
        this.terminal.display("Type in the Source Data Folder Path: ");

        String dataFolder = this.terminal.readString();

        List<Loader> loaders = new ArrayList<>();
        loaders.add(new Loader(dataFolder, "resources.txt", new ResourcesQueryBuilder()));
        loaders.add(new Loader(dataFolder, "neas.txt", new NearEarthAsteroidQueryBuilder()));
        loaders.add(new Loader(dataFolder, "spacecrafts.txt", new SpacecraftQueryBuilder()));
        loaders.add(new Loader(dataFolder, "rentalrecords.txt", new SpacecraftRentalRecordQueryBuilder()));

        try {
            for (Loader loader : loaders) {
                loader.load();

                this.terminal.displayLine("Loaded " + loader.toString() + ".");
            }

            this.terminal.displayLine("Done All.");
        } catch (IOException e) {
            this.terminal.displayError("Failed. Some files cannot be accessed.");
        }
    }

    private void showNumOfRecordsInAllTables() {
        try (Connection conn = Database.getConnection()) {
            DSLContext create = Database.getContext(conn);

            int count;

            count = create
                    .selectCount()
                    .from(RESOURCES)
                    .fetchOne(0, int.class);
            this.terminal.displayLine("Resources: " + count);

            count = create
                    .selectCount()
                    .from(NEAREARTHASTEROIDS)
                    .fetchOne(0, int.class);
            this.terminal.displayLine("NearEarthAsteroids: " + count);

            count = create
                    .selectCount()
                    .from(SPACECRAFTS)
                    .fetchOne(0, int.class);
            this.terminal.displayLine("Spacecrafts: " + count);

            count = create
                    .selectCount()
                    .from(SPACECRAFTRENTALRECORDS)
                    .fetchOne(0, int.class);
            this.terminal.displayLine("SpacecraftRentalRecords: " + count);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public MainMenu getMainMenu() {
        return mainMenu;
    }
}

class Loader {

    private final String dataFolder;
    private final String fileName;
    private final QueryBuilder queryBuilder;

    Loader(String dataFolder, String fileName, QueryBuilder queryBuilder) {
        this.dataFolder = dataFolder;
        this.fileName = fileName;
        this.queryBuilder = queryBuilder;
    }

    @Override
    public String toString() {
        return fileName;
    }

    protected void load() throws IOException {
        try (Connection conn = Database.getConnection()) {
            DSLContext create = Database.getContext(conn);

            Iterable<CSVRecord> records = getCsvRecords(this.dataFolder, this.fileName);

            List<Query> queries = new ArrayList<>();

            for (CSVRecord record : records) {
                Map<String, String> map = record.toMap();

                queries.add(this.queryBuilder.from(create, map));
            }

            create.batch(queries).execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Iterable<CSVRecord> getCsvRecords(String dataFolder, String fileName) throws IOException {
        FileReader fileReader = new FileReader(String.format("%s/%s", dataFolder, fileName));
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        StringBuilder stringBuilder = new StringBuilder();
        while (true) {
            String line = bufferedReader.readLine();

            if (line == null) {
                bufferedReader.close();

                break;
            }

            stringBuilder.append(line).append("\n");
        }

        String csv = stringBuilder.toString().replaceAll("\t", ",");

        return CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(new StringReader(csv));
    }

}

interface QueryBuilder {

    Query from(DSLContext create, Map<String, String> map);

}

class ResourcesQueryBuilder implements QueryBuilder {

    @Override
    public Query from(DSLContext create, Map<String, String> map) {
        String type = map.get("Type");
        Double density = Double.parseDouble(map.get("Density"));
        Double value = Double.parseDouble(map.get("Value"));

        return create
                .insertInto(RESOURCES, RESOURCES.TYPE, RESOURCES.DENSITY, RESOURCES.VALUE)
                .values(type, density, value);
    }

}

class NearEarthAsteroidQueryBuilder implements QueryBuilder {

    @Override
    public Query from(DSLContext create, Map<String, String> map) {
        String neaId = map.get("NID");
        Double distance = Double.parseDouble(map.get("Distance(AU)"));
        String family = map.get("Family");
        Integer minDuration = Integer.parseInt(map.get("Duration(days)"));
        Double minEnergy = Double.parseDouble(map.get("Energy(km/s)"));
        String resourceType = map.get("Resources").equals("null") ? null : map.get("Resources");

        return create
                .insertInto(NEAREARTHASTEROIDS, NEAREARTHASTEROIDS.NEAID, NEAREARTHASTEROIDS.DISTANCE, NEAREARTHASTEROIDS.FAMILY, NEAREARTHASTEROIDS.MINDURATION, NEAREARTHASTEROIDS.MINENERGY, NEAREARTHASTEROIDS.RESOURCETYPE)
                .values(neaId, distance, family, minDuration, minEnergy, resourceType);
    }
}

class SpacecraftQueryBuilder implements QueryBuilder {

    @Override
    public Query from(DSLContext create, Map<String, String> map) {
        String agencyName = map.get("Agency");
        String modelId = map.get("MID");
        Integer count = Integer.parseInt(map.get("Num"));
        Integer dayCharge = Integer.parseInt(map.get("Charge($/day)"));
        Integer maxTripTime = Integer.parseInt(map.get("T(days)"));
        Double maxTripEnergy = Double.parseDouble(map.get("Energy(km/s)"));
        Integer maxCapacity = map.get("Capacity(m3)").equals("null") ? null : Integer.parseInt(map.get("Capacity(m3)"));
        String type = map.get("Type");

        return create
                .insertInto(SPACECRAFTS, SPACECRAFTS.AGENCYNAME, SPACECRAFTS.MODELID, SPACECRAFTS.COUNT, SPACECRAFTS.DAYCHARGE, SPACECRAFTS.MAXTRIPTIME, SPACECRAFTS.MAXTRIPENERGY, SPACECRAFTS.MAXCAPACITY, SPACECRAFTS.TYPE)
                .values(agencyName, modelId, count, dayCharge, maxTripTime, maxTripEnergy, maxCapacity, type);
    }

}

class SpacecraftRentalRecordQueryBuilder implements QueryBuilder {

    @Override
    public Query from(DSLContext create, Map<String, String> map) {
        String agencyName = map.get("Agency");
        String modelId = map.get("MID");
        Integer spacecraftIndex = Integer.parseInt(map.get("SNum"));
        Date checkoutDate = this.getDate(map.get("Checkout Date"));
        Date returnDate = map.get("Return").equals("null") ? null : this.getDate(map.get("Return"));

        return create
                .insertInto(SPACECRAFTRENTALRECORDS, SPACECRAFTRENTALRECORDS.AGENCYNAME, SPACECRAFTRENTALRECORDS.MODELID, SPACECRAFTRENTALRECORDS.SPACECRAFTINDEX, SPACECRAFTRENTALRECORDS.CHECKOUTDATE, SPACECRAFTRENTALRECORDS.RETURNDATE)
                .values(agencyName, modelId, spacecraftIndex, checkoutDate, returnDate);
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

}