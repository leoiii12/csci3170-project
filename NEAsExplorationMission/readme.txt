Group 21
1155077469 CHOI Man Kin
1155079044 TSANG Chi Long
make all && make run

.
├── Makefile
├── NEAsExplorationMission.iml
├── data
│   ├── neas.txt
│   ├── rentalrecords.txt
│   ├── resources.txt
│   └── spacecrafts.txt
├── lib
│   ├── annotations-java8.jar
│   ├── commons-csv-1.5.jar
│   ├── jooq-3.10.5.jar
│   └── mariadb-java-client-2.2.2.jar
├── readme.txt
└── src
    └── com
        └── exploration
            ├── AdministratorMenu.java
            ├── Database.java ********** Database configurations
            ├── ExplorationCompaniesMenu.java
            ├── Main.java ********** Main entry
            ├── MainMenu.java
            ├── Menu.java
            ├── Operation.java
            ├── OperationMenu.java
            ├── SpacecraftRentalStaffMenu.java
            ├── Terminal.java
            └── jooq ********** The strongly typed database sources
                ├── Db21.java
                ├── DefaultCatalog.java
                ├── Indexes.java
                ├── Keys.java
                ├── Tables.java
                └── tables
                    ├── Nearearthasteroids.java
                    ├── Resources.java
                    ├── Spacecraftrentalrecords.java
                    ├── Spacecrafts.java
                    └── records
                        ├── NearearthasteroidsRecord.java
                        ├── ResourcesRecord.java
                        ├── SpacecraftrentalrecordsRecord.java
                        └── SpacecraftsRecord.java
