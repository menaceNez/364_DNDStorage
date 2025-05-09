
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CampaignQueries {
	private DndDatabase db;
	private Connection connect;

	public CampaignQueries(DndDatabase db) {
		this.db = db;
		this.connect = db.connect();
	}

	public void insertCampaignsFromFile() {
		try (BufferedReader reader = new BufferedReader(new FileReader("CampaignNames"))) {
			String line;

			while ((line = reader.readLine()) != null) {
				Campaign cp = new Campaign(line);
				System.out.println("Actually worked! " + line + " " + cp.getCampName() + " " + cp.getCampId());
				insertCampaign(cp);
			}
		} catch (IOException err) {
			System.out.println("InsertCampaignsFromFile Errror: " + err);
		}
	}

	public void insertCampaign(Campaign campaign) {
		try {
			String sql = "SELECT * FROM Campaign";
			PreparedStatement stmt = connect.prepareStatement(sql);
			stmt.execute();
		} catch (SQLException error) {
			System.out.println(error);
		}
	}

	public ArrayList<Campaign> getAllCampaigns() {
		try {
			ArrayList<Campaign> camps = new ArrayList<>();
			String sql = "SELECT * FROM Campaign";
			PreparedStatement stmt = connect.prepareStatement(sql);
			ResultSet set = stmt.executeQuery();
			int i = 0;

			while (set.next()) {
				Campaign cmp = new Campaign(set.getString(2));

				cmp.setCampId(set.getInt(1));
				camps.add(cmp);

				i++;
			}

			return camps;
		} catch (SQLException error) {
			System.out.println("Error in getAllCampaigns(): " + error.toString());
			return null;
		}
	}

	public void closeConnection() {
		try {
			connect.close();
		} catch (SQLException error) {
			System.out.println("Error closing connection :" + error.toString());
		}
	}

//	public static void main(String[] args) {
//		DndDatabase db = new DndDatabase();
//
//		CampaignQueries cq = new CampaignQueries(db);
//		cq.insertCampaignsFromFile();
//	}
}
