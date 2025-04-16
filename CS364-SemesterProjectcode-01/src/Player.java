public class Player {

    private String FirstName; 
    private String LastName; 
    private int PlayID; 

    public Player(String first, String last){
        this.FirstName = first; 
        this.LastName = last;  
    }

    public String getFirstName(){
        return FirstName; 
    }

    public String getLastName(){
        return LastName; 
    }

    public void setFirstName(String first){
        this.FirstName = first; 
    }

    public void setLastName(String last){
        this.LastName = last; 
    }

    public int getPlayID(){
        return this.PlayID; 
    }

    public void setPlayID(int ID){
        this.PlayID = ID; 

    }

    public String toString() {
    	return this.PlayID +": " + this.FirstName + " "+ this.LastName; 
    }

}
