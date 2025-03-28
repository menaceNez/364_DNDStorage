CREATE TABLE Campaign (
	CampaignID INTEGER AUTO_INCREMENT NOT NULL,
    CampaignName NVARCHAR(255), -- nvarchar=2bytes, varchar=1byte
    PRIMARY KEY (CampaignID)
);

CREATE TABLE Adventure (
	AdventureID INTEGER AUTO_INCREMENT NOT NULL,
    CONSTRAINT ad_campaignID FOREIGN KEY(CampaignID) REFERENCES Campaign(CampaignID),
	-- DmID FOREIGN KEY (PlayerID) REFERENCES Player(PlayerID) -- DMID WILL BE FOREIGN KEY LINKING TO PLAYERID
    AdventureName NVARCHAR(255) NOT NULL, 
    StartDate DATE,
    EndDate DATE
);

CREATE TABLE PlaySession (
	PlaySessionID INTEGER AUTO_INCREMENT NOT NULL,
    CONSTRAINT ps_adventureID FOREIGN KEY(AdventureID) REFERENCES Adventure(AdventureID),
    StartTime DATETIME,
    EndTime DATETIME,
    Event VARCHAR(1024) -- might need larger to allow ample description
);


CREATE TABLE Player (
	PlayID int NOT NULL AUTO_INCREMENT,
    LastName varchar(255) NOT NULL,
    FirstName varchar(255),
    PRIMARY KEY (PlayID)
);

CREATE TABLE Race(
	RaceID int NOT NULL AUTO_INCREMENT, 
    RaceName varchar(255),
    PRIMARY KEY (RaceID)
);

CREATE TABLE Class(
	ClassID int NOT NULL AUTO_INCREMENT, 
    ClassName varchar(255),
    PRIMARY KEY (ClassID)
);


CREATE TABLE  Persona(
	CharID int NOT NULL AUTO_INCREMENT,
    PlayID int NOT NULL, 
    ClassID int NOT NULL, 
    RaceID int NOT NULL, 
    CharLastName varchar(255) NOT NULL,
    CharMiddName varChar(255), 
	CharFirstName varchar(255),
    Lev int NOT NULL, 
    CreationDate Date Not Null, 

    PRIMARY KEY (CharID), 
    FOREIGN KEY (PlayID) REFERENCES Player(PlayID),
    FOREIGN KEY (ClassID) REFERENCES Class(ClassID), 
    FOREIGN KEY (RaceID) REFERENCES Race(RaceID)

);
