public class Persona {
    public int charID; 
    public int playID; 
    public int classID; 
    public String clas; 
    public int raceID; 
    public String race; 
    public int lev; 
    public String charFirstName; 
    public String charMiddleName; 
    public String charLastName; 
    public String creationDate; 

    public Persona(int playID, String First, String Middle,  String Last, String Class, String Race, int level){
        this.playID = playID; 
        this.charFirstName = First; 
        this.charMiddleName = Middle; 
        this.charLastName = Last; 
        this.clas = Class; 
        this.race = Race; 
        this.lev = level; 
    } 

    public Persona(int playID, String First, String Last, String Class, String Race){
        this.playID = playID; 
        this.charFirstName = First;  
        this.charLastName = Last; 
        this.clas = Class; 
        this.race = Race; 
        this.lev = 1; 
    }




    public int getClassID(){
        return classID; 
    }

    public void setClassID(int classID){
        this.classID = classID; 
    }

    public int getRaceID(){
        return raceID; 
    }

    public void setRaceID(int raceID){
        this.raceID = raceID; 
    }

    public int getLev(){
        return lev; 
    }

    public void setLev(int level){
        this.lev = level; 
    }

    public void increaseLev(){
        this.lev = this.lev + 1; 
    }


}


/**
 * -- 	CharID int NOT NULL AUTO_INCREMENT,
--     PlayID int NOT NULL, 
--     C int NOT NULL, 
--     RaceID int NOT NULL, 
--     CharLastName varchar(255) NOT NULL,
--     CharMiddName varChar(255), 
-- 	CharFirstName varchar(255),
--     Lev int NOT NULL, 
--     CreationDate Date Not Null, 
 * 
 * 
 * 
 * 
 * 
 */