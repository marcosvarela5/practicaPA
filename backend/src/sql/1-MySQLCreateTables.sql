DROP TABLE Inscription;
DROP TABLE User;
DROP TABLE Trial;
DROP TABLE Province;
DROP TABLE TrialType;

CREATE TABLE User (
    id BIGINT NOT NULL AUTO_INCREMENT,
    userName VARCHAR(60) COLLATE latin1_bin NOT NULL,
    password VARCHAR(60) NOT NULL, 
    firstName VARCHAR(60) NOT NULL,
    lastName VARCHAR(60) NOT NULL, 
    email VARCHAR(60) NOT NULL,
    role TINYINT NOT NULL,
    CONSTRAINT UserPK PRIMARY KEY (id),
    CONSTRAINT UserNameUniqueKey UNIQUE (userName)
) ENGINE = InnoDB;

CREATE INDEX UserIndexByUserName ON User (userName);

CREATE TABLE Province (
  id BIGINT NOT NULL AUTO_INCREMENT,
  provinceName VARCHAR(60) NOT NULL,
  CONSTRAINT ProvincePK PRIMARY KEY (id),
  CONSTRAINT ProvinceProvinceNameUniqueKey UNIQUE (provinceName)
) ENGINE = InnoDB;

CREATE INDEX ProvinceIndexByProvinceName ON Province (provinceName);

CREATE TABLE TrialType (
  id BIGINT NOT NULL AUTO_INCREMENT,
  trialTypeName VARCHAR(60) NOT NULL,
  CONSTRAINT TrialTypePK PRIMARY KEY (id),
  CONSTRAINT TrialTypeTrialTypeNameUniqueKey UNIQUE (trialTypeName)
) ENGINE = InnoDB;

CREATE INDEX TrialTypeIndexByTrialTypeName ON TrialType (trialTypeName);

CREATE TABLE Trial (
   id BIGINT NOT NULL AUTO_INCREMENT,
   trialName VARCHAR(60) COLLATE latin1_bin NOT NULL,
   trialDescription VARCHAR(60) NOT NULL,
   dateTime DATETIME NOT NULL,
   inscriptionPrice DECIMAL(11, 2) NOT NULL,
   maxParticipants SMALLINT NOT NULL,
   place VARCHAR(60) NOT NULL,
   trialTypeId BIGINT NOT NULL,
   provinceId BIGINT NOT NULL,
   sumScores SMALLINT NOT NULL DEFAULT 0,
   numScores SMALLINT NOT NULL DEFAULT 0,
   numParticipants SMALLINT NOT NULL DEFAULT 0,
   version BIGINT NOT NULL DEFAULT 0,
   CONSTRAINT TrialPK PRIMARY KEY (id),
   CONSTRAINT TrialTrialTypeIdFK FOREIGN KEY(trialTypeId)
       REFERENCES TrialType (id),
   CONSTRAINT TrialProvinceIdFK FOREIGN KEY(provinceId)
       REFERENCES Province (id)
) ENGINE = InnoDB;

CREATE INDEX TrialIndexByTrialName ON Trial (trialName);

CREATE TABLE Inscription (
    id BIGINT NOT NULL AUTO_INCREMENT,
    userId BIGINT NOT NULL,
    trialId BIGINT NOT NULL,
    number SMALLINT NOT NULL,
    cardNumber VARCHAR(16) NOT NULL,
    numberDelivered BIT NOT NULL,
    score SMALLINT NOT NULL,
    scored BIT NOT NULL,
    dateTime DATETIME NOT NULL,
    CONSTRAINT InscriptionPK PRIMARY KEY (id),
    CONSTRAINT InscriptionUserIdFK FOREIGN KEY (userId)
        REFERENCES User (id),
    CONSTRAINT InscriptionTrialIdFK FOREIGN KEY (trialId)
        REFERENCES Trial (id)
) ENGINE = InnoDB;

CREATE INDEX InscriptionIndexByNumber ON Inscription (number);