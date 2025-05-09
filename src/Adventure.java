import java.sql.Date;
import java.time.*;

public class Adventure {

	private int campaignId;
	private int playerId;
	private java.sql.Date startDate;
	private java.sql.Date endDate;
	private String adventureName;
	private Persona persona;
	private int adventureID;
	private Campaign campaign;

	// Constructor for PlayerPlaysIn
	public Adventure(int campID, int playID, java.sql.Date start, String advName, Persona persona) {
		this.campaignId = campID;
		this.playerId = playID;
		this.startDate = start;
		this.adventureName = advName;
		this.persona = persona;
	}

	// for a basic adventure:
	public Adventure(int adventureID , int playID, java.sql.Date start, String advName, Campaign campaign) {
		this.playerId = playID;
		this.startDate = start;
		this.adventureName = advName;
		this.adventureID = adventureID;	
		this.campaign = campaign;
	}

	public int getCampId() {
		return campaignId;
	}

	public int getPlayerId() {
		return playerId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public Date getEndDate() {
		return endDate;
	}
	
	public Date setEndDate(Date date) {
		long milli = System.currentTimeMillis();
		date.setTime(milli);

		return date;
	}

	public String getAdvName() {
		return adventureName;
	}
	
	public Persona setPersona(Persona persona) {
		return this.persona = persona;
	}

	public Persona getPersona() {
		return persona;
	}
	
	public int getAdventureID() {
		return this.adventureID;
	}
	
	@Override
	public String toString() {
		if(this.persona != null) {
			return persona.charFirstName+""+persona.charLastName+" "+persona.clas+" "+persona.race;	
		}

		return this.adventureName+" "+this.campaign.getCampName();

	}

}
