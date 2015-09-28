DROP TABLE YellowTag IF EXISTS;
DROP TABLE BusinessRule IF EXISTS;

CREATE TABLE YellowTag  (
    yellowTagId BIGINT IDENTITY NOT NULL PRIMARY KEY,
    rank INT,
    customerId VARCHAR(50),
    upc VARCHAR(50),
    basePrice VARCHAR(50),
    promoPrice VARCHAR(50),
    itemDescription VARCHAR(250),
    imageUrl VARCHAR(250)
);

CREATE TABLE BusinessRule (
    businessRuleId BIGINT IDENTITY NOT NULL PRIMARY KEY,
    rank INT,
    science VARCHAR(50),
    scienceRank INT
)
