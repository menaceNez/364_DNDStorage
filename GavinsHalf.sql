-- CREATE TABLE Campaign (
-- 	CampaignID int AUTO_INCREMENT NOT NULL,
--     CampaignName NVARCHAR(255), -- nvarchar=2bytes, varchar=1byte
--     PRIMARY KEY (CampaignID)
-- );

-- CREATE TABLE Adventure (
-- 	AdventureID int AUTO_INCREMENT NOT NULL,
--     AdventureName NVARCHAR(255) NOT NULL, 
--     CampaignID int NOT NULL,
--     PlayID int NOT NULL,
--     StartDate DATE,
--     EndDate DATE,
--     PRIMARY KEY (AdventureID),
--     FOREIGN KEY(CampaignID) REFERENCES Campaign(CampaignID),
-- 	FOREIGN KEY (PlayID) REFERENCES Player(PlayID) -- DMID WILL BE FOREIGN KEY LINKING TO PLAYERID
-- );

-- CREATE TABLE PlaySession (
-- 	PlaySessionID INTEGER AUTO_INCREMENT NOT NULL,
--     AdventureID int NOT NULL,
--     StartTime DATETIME,
--     EndTime DATETIME,
--     Event VARCHAR(1024), -- might need larger to allow ample description
--     FOREIGN KEY(AdventureID) REFERENCES Adventure(AdventureID),
--     PRIMARY KEY (PlaySessionID)
-- );


-- CREATE TABLE Player (
-- 	PlayID int NOT NULL AUTO_INCREMENT,
--     LastName varchar(255) NOT NULL,
--     FirstName varchar(255),
--     PRIMARY KEY (PlayID)
-- );

-- CREATE TABLE Race(
-- 	RaceID int NOT NULL AUTO_INCREMENT, 
--     RaceName varchar(255),
--     PRIMARY KEY (RaceID)
-- );

-- CREATE TABLE Class(
-- 	ClassID int NOT NULL AUTO_INCREMENT, 
--     ClassName varchar(255),
--     PRIMARY KEY (ClassID)
-- );


-- CREATE TABLE  Persona(
-- 	CharID int NOT NULL AUTO_INCREMENT,
--     PlayID int NOT NULL, 
--     ClassID int NOT NULL, 
--     RaceID int NOT NULL, 
--     CharLastName varchar(255) NOT NULL,
--     CharMiddName varChar(255), 
-- 	CharFirstName varchar(255),
--     Lev int NOT NULL, 
--     CreationDate Date Not Null, 

--     PRIMARY KEY (CharID), 
--     FOREIGN KEY (PlayID) REFERENCES Player(PlayID),
--     FOREIGN KEY (ClassID) REFERENCES Class(ClassID), 
--     FOREIGN KEY (RaceID) REFERENCES Race(RaceID)

-- );

-- CREATE TABLE PersonaPlaysIn (
-- 	CharID int NOT NULL,
--     AdventureID int NOT NULL,
--     FOREIGN KEY (CharID) REFERENCES Persona(CharID),
--     FOREIGN KEY (AdventureID) REFERENCES Adventure(AdventureID)
-- );
