import java.time.*;

public class Adventure {

	private int adventureId;
	private int campaignId;
	private int playerId;
	private LocalDate startDate;
	private LocalDate endDate;
	private String adventureName;

	public Adventure(int advID, int campID, int playID, LocalDate start, LocalDate end, String advName) {
		this.adventureId = advID;
		this.campaignId = campID;
		this.playerId = playID;
		this.startDate = start;
		this.endDate = end;
		this.adventureName = advName;
	}

	public int getAdvId() {
		return adventureId;
	}

	public int getCampId() {
		return campaignId;
	}

	public int getPlayerId() {
		return playerId;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public String getAdvName() {
		return adventureName;
	}

	public static void main(String[] args) {

	}
}
