
public class Campaign {
	private int campaignId;
	private String campaignName;

	public Campaign(String campaignName) {
		this.campaignName = campaignName;
	}

	public int getCampId() {
		return campaignId;
	}

	public String getCampName() {
		return campaignName;
	}
	
	public boolean setCampId(int n) {
		if(n < 0) { return false; } 
		this.campaignId = n;
		return true;
	}
	
	public String toString() {
		return this.campaignId+" "+this.campaignName;
	}
}
