USE db21;

CREATE TABLE Resource (
    RType CHAR(2) PRIMARY KEY,
    Density REAL,
    Value REAL
);

CREATE TABLE NEA (
    NID CHAR(10) PRIMARY KEY,
    Distance REAL,
    Family CHAR(6),
    Duration INTEGER(3),
    Energy REAL,
    Rtype CHAR(2) DEFAULT NULL,
    FOREIGN KEY (Rtype) REFERENCES Resource(RType)
);

CREATE TABLE Spacecraft_Model (
    Agency CHAR(4),
    MID CHAR(4),
    Num INTEGER(2),
    Charge INTEGER(5),
    Duration INTEGER(3),
    Energy REAL,
    Capacity INTEGER(2),
    Type CHAR(1),
    PRIMARY KEY (Agency, MID)
);

CREATE TABLE RentalRecord (
    Agency CHAR(4),
    MID CHAR(4),
    SNum INTEGER(2),
    CheckoutDate DATE,
    ReturnDate DATE DEFAULT NULL,
    PRIMARY KEY (Agency, MID, SNum),
    FOREIGN KEY (Agency, MID)
    REFERENCES Spacecraft_Model(Agency, MID)
);
