
public class Race {

    public int raceID; 
    public String raceName; 

    public  Race(String race){
        this.raceName = race; 
    }

    public String getRaceName(){
        return raceName; 
    }

    public void setRaceName(String race){
        this.raceName = race; 
    }

    public void setRaceID(int ID){
        this.raceID = ID; 
    }

    public int getRaceID(){
        return raceID; 
    }
    
    public String toString() {
    	return raceName; 
    }
    

}
