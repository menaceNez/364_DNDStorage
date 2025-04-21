
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CampaignQueries {
	private DndDatabase db;

	public CampaignQueries(DndDatabase db) {
		this.db = db;
	}

	public void insertCampaignsFromFile() {
		try (BufferedReader reader = new BufferedReader(new FileReader("CampaignNames"))) {
			String line;

			while ((line = reader.readLine()) != null) {
				Campaign cp = new Campaign(line);
				System.out.println("Actually worked! " + line + " " + cp.getCampName() + " " + cp.getCampId());
				db.insertCampaign(cp);
			}
		} catch (IOException err) {
			System.out.println("InsertCampaignsFromFile Errror: " + err);
		}
	}

	public static void main(String[] args) {
		DndDatabase db = new DndDatabase();
		db.connect();

		CampaignQueries cq = new CampaignQueries(db);
		cq.insertCampaignsFromFile();
	}
}

