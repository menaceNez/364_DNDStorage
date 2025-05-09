import java.sql.Date;

public class PlaySession {
	private int adventureID;
	private java.sql.Date startDate;
	private java.sql.Date endDate;

	public PlaySession(int advID, Date startDate, Date endDate) {
		this.adventureID = advID;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public int getAdventureID() {
		return adventureID;
	}

	public Date getStartDate() {
		return startDate;
	}

	public Date getEndDate() {
		return endDate;
	}
}
