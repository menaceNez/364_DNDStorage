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