USE db21;

CREATE TABLE Resources (
    Type CHAR(2) PRIMARY KEY,
    Density REAL,
    Value REAL
);

CREATE TABLE NearEarthAsteroids (
    NeaId CHAR(10) PRIMARY KEY,
    Distance REAL,
    Family CHAR(6),
    MinDuration INTEGER(3),
    MinEnergy REAL,
    ResourceType CHAR(2) DEFAULT NULL,
    FOREIGN KEY (ResourceType) REFERENCES Resource(Type)
);

CREATE TABLE Spacecrafts (
    AgencyName CHAR(4),
    ModelId CHAR(4),
    Count INTEGER(2),
    DayCharge INTEGER(5),
    MaxTripTime INTEGER(3),
    MaxTripEnergy REAL,
    MaxCapacity INTEGER(2),
    Type CHAR(1),
    PRIMARY KEY (AgencyName, ModelId)
);

CREATE TABLE SpacecraftRentalRecords (
    AgencyName CHAR(4),
    ModelId CHAR(4),
    SpacecraftIndex INTEGER(2),
    CheckoutDate DATE,
    ReturnDate DATE DEFAULT NULL,
    PRIMARY KEY (AgencyName, ModelId, SpacecraftIndex),
    FOREIGN KEY (AgencyName, ModelId)
    REFERENCES Spacecraft_Model(AgencyName, ModelId)
);
