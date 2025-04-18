
public class Campaign {
	private int campaignId;
	private String campaignName;

	public Campaign(int campaignId, String campaignName) {
		this.campaignId = campaignId;
		this.campaignName = campaignName;
	}

	public int getCampId() {
		return campaignId;
	}

	public String getCampName() {
		return campaignName;
	}
}
