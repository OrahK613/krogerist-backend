DROP TABLE BusinessRule IF EXISTS;
DROP TABLE Coupon       IF EXISTS;
DROP TABLE CouponItem   IF EXISTS;
DROP TABLE Favorite     IF EXISTS;
DROP TABLE WeeklyAd     IF EXISTS;
DROP TABLE WeeklyAdItem IF EXISTS;
DROP TABLE YellowTag    IF EXISTS;

CREATE TABLE BusinessRule (
    businessRuleId BIGINT IDENTITY NOT NULL PRIMARY KEY,
    rank INT,
    science VARCHAR(50),
    scienceRank INT
);

CREATE TABLE Coupon (
    couponId BIGINT IDENTITY NOT NULL PRIMARY KEY,
    rank INT,
    couponOfferId VARCHAR(50),
    customerId VARCHAR(50),
    couponNum VARCHAR(50),
    score VARCHAR(50),
    baseUpc VARCHAR(50),
    itemDescription VARCHAR(250),
    details VARCHAR(250),
    couponImage VARCHAR(250),
    brand VARCHAR(50)
);

CREATE TABLE CouponItem (
    couponItemId BIGINT IDENTITY NOT NULL PRIMARY KEY,
    rank INT,
    couponOfferId VARCHAR(50),
    customerId VARCHAR(50),
    couponNum VARCHAR(50),
    baseUpc VARCHAR(50),
    itemDescription VARCHAR(250),
    itemSize VARCHAR(50),
    imageUrl VARCHAR(250),
    prodRank INT
);

CREATE TABLE Favorite (
    favoriteId BIGINT IDENTITY NOT NULL PRIMARY KEY,
    customerId VARCHAR(50),
    itemNum VARCHAR(50),
    rank INT,
    itemDescription VARCHAR(250),
    imageUrl VARCHAR(250)
);

CREATE TABLE WeeklyAd (
    weeklyAdId BIGINT IDENTITY NOT NULL PRIMARY KEY,
    customerId VARCHAR(50),
    circularItemId VARCHAR(50),
    rank INT,
    startDate VARCHAR(50),
    endDate VARCHAR(50),
    itemTitle VARCHAR(250)
);

CREATE TABLE WeeklyAdItem (
    weeklyAdItemId BIGINT IDENTITY NOT NULL PRIMARY KEY,
    prodCode VARCHAR(50),
    circularItemId VARCHAR(50),
    adIdRank INT,
    itemDescription VARCHAR(250),
    imageUrl VARCHAR(250),
    itemRank INT
);

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


