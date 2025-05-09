import java.sql.Date;
import java.time.LocalDate;


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
    public Date creationDate; 

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
    
    
    public Persona(int playID, String First, String Last, int lev) {
    	this.playID = playID; 
    	this.charFirstName = First; 
    	this.charMiddleName = null; 
    	this.charLastName = Last; 
    	this.lev = lev; 
    }
    
    public Persona(int plId, String First, String Midd, String Last, int lev) {
    	this.playID = plId; 
    	this.charFirstName = First; 
    	this.charMiddleName = Midd; 
    	this.charLastName = Last; 
    	this.lev = lev; 
    }
    
    public Persona(int playID, String First, String Last, int clas, int race, int lev, Date cre) {
    	this.playID = playID; 
    	this.charFirstName = First; 
    	this.charMiddleName = null; 
    	this.charLastName = Last; 
    	this.lev = lev; 
    	this.classID = clas; 
    	this.raceID = race; 
    	this.creationDate = cre; 
    }
    
    public Persona(int playID, String First, String Middle, String Last, int clas, int race, int lev, Date cre) {
    	this.playID = playID; 
    	this.charFirstName = First; 
    	this.charMiddleName = Middle; 
    	this.charLastName = Last; 
    	this.lev = lev; 
    	this.classID = clas; 
    	this.raceID = race; 
    	this.creationDate = cre; 
    }
    
    public Persona(int playID, int chaID, String First, String Middle, String Last, int clas, int race, int lev, Date cre) {
    	this.playID = playID; 
    	this.charID = chaID; 
    	this.charFirstName = First; 
    	this.charMiddleName = Middle; 
    	this.charLastName = Last; 
    	this.lev = lev; 
    	this.classID = clas; 
    	this.raceID = race; 
    	this.creationDate = cre; 
    }
    
    public Persona(int playID, String First, String Middle,  String Last, int Cla, int Rac, int level){
        this.playID = playID; 
        this.charFirstName = First; 
        this.charMiddleName = Middle; 
        this.charLastName = Last; 
        this.classID = Cla; 
        this.raceID = Rac; 
        this.lev = level; 
    } 
    
    //added 5/2 needs to be copied over
    
    public Persona(int playID, int chaID, String First, String Middle, String Last, String clas, String race, int lev, Date cre) {
    	this.playID = playID; 
    	this.charID = chaID; 
    	this.charFirstName = First; 
    	this.charMiddleName = Middle; 
    	this.charLastName = Last; 
    	this.lev = lev; 
    	this.clas = clas; 
    	this.race = race; 
    	this.creationDate = cre; 
    }
    
    public Persona(int playID,int charID, String First, String Last, String Class, String Race){
        this.playID = playID; 
        this.charFirstName = First;  
        this.charLastName = Last; 
        this.clas = Class; 
        this.race = Race; 
        this.charID = charID;
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
    
    
    public String getClaName() {
    	return this.clas; 

    }
    
    public String getRacName() {
    	return this.race; 
    	
    }
    
    public String getPFirstName() {
    	return this.charFirstName; 
    }
    
    public String getPLastName() {
    	return this.charLastName; 
    }
    
    public int getPlayID() {
    	return this.playID; 
    }
    
    public String toString() {
    	
    	//return  this.charID + " " + this.charFirstName + " " + this.charLastName + " " + this.clas + ": " + this.classID + " " + this.race + ": " + this.raceID; 
    	//return this.playID+ " " + this.charID + " " + this.charFirstName + " " + this.charLastName + " " + this.clas + " " + this.race + " level: " + this.lev + " Created: " + this.creationDate;
    	return this.charID + " " + this.playID + " " +  this.charFirstName + " " + this.charLastName + " " + this.clas + " " + this.race + " level: " + this.lev + " Created: " + this.creationDate;

    }
    
    public void setRacName(String rac) {
    	this.race = rac; 
    }
    
    public void setClaName(String cla) {
    	this.clas = cla; 
    }
    
    public Date getCal() {
    	return this.creationDate; 
    }
    
    public String getMiddName() {
    	return this.charMiddleName; 
    }
    
    public void setCharID(int chaID) {
    	this.charID = chaID; 
    }

    public int getCharID() {
    	return charID; 
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
