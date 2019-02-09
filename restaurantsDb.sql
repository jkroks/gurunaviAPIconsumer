create database restaurant;
use restaurant;


CREATE TABLE restaurant (
        ID VARCHAR(100) NOT NULL,
        RESTAURANT VARCHAR(500) NOT NULL,
        RESTTYPE VARCHAR(500) NOT NULL,
        WEBPAGE VARCHAR(5000),
        IMAGE VARCHAR(5000),
        AREA  VARCHAR(500),
        PREFACTURE VARCHAR(500),
        BUDGET INT,
        LATITUDE DOUBLE,
        LONGITUDE DOUBLE,
        PRIMARY KEY (ID)
    );
