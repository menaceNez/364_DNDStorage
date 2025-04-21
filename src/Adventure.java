import java.sql.Date;
import java.time.*;

public class Adventure {

	private int campaignId;
	private int playerId;
	private java.sql.Date startDate;
	private java.sql.Date endDate;
	private String adventureName;

	public Adventure(int campID, int playID, java.sql.Date start, java.sql.Date end, String advName) {
		this.campaignId = campID;
		this.playerId = playID;
		this.startDate = start;
		this.endDate = end;
		this.adventureName = advName;
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

	public String getAdvName() {
		return adventureName;
	}
	
	@Override
	public String toString() {
		return "{ "+campaignId +" "+ playerId +" "+ this.startDate +" "+ this.endDate +" "+ this.adventureName +" }";
	}

	public static void main(String[] args) {

	}
}
