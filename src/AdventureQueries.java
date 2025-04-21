import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class AdventureQueries {
	private DndDatabase db;
	private Connection connection;

	public AdventureQueries(DndDatabase db) {
		this.db = db;
		connection = db.connect();
		System.out.println("Should have connected)");
	}

	public void insertAdventuresFromFile() {
		try (BufferedReader reader = new BufferedReader(new FileReader("CampaignNames"))) {

			long millis = System.currentTimeMillis();
			Date startDate = new Date(millis);
			System.out.println("DID THIS PRINT? \n" + startDate);
			String AdventureName = "";
			String line = "";
			int CampaignId = 1;
			int playId = 45;

//			while ((line = reader.readLine()) != null) {
//				
//				System.out.println(line);
//			}
		} catch (IOException err) {
			System.out.println("InsertCampaignsFromFile Errror: " + err);
		}
	}

	public boolean insertAdventure(Adventure adventure) throws SQLException {
		String query = "SELECT * FROM Adventure";
		PreparedStatement stmt = connection.prepareStatement(query);
		ResultSet res = stmt.executeQuery();
		while(res.next()) {
			System.out.println(res.getString("AdventureName") + " " + res.getInt("PlayID"));
		}
		
//		try {
//			String sql = "INSERT INTO Adventure (AdventureName, CampaignID, EndDate, PlayID, StartDate) VALUES (?, ?, ?, ?, ?)";
//			PreparedStatement stmt = connection.prepareStatement(sql);
//
//			System.out.println(adventure.toString());
//
//			stmt.setString(1, adventure.getAdvName());
//			stmt.setInt(2, adventure.getCampId());
//			stmt.setDate(3, adventure.getStartDate());
//			stmt.setInt(4, adventure.getPlayerId());
//			stmt.setDate(5, adventure.getEndDate());
//
//			int rowsAffected = stmt.executeUpdate();
//			System.out.println("Rows Affected: " + rowsAffected);
//
//		} catch (SQLException err) {
//			System.out.println(err);
//
//			return false;
//		}
		return true;
	}

	public static void main(String[] args) {
		AdventureQueries adv = new AdventureQueries(new DndDatabase());
//		adv.insertAdventure(new Adventure(0, 0, 0, null, null, null));
		long startDate = System.currentTimeMillis();
		try {
			adv.insertAdventure(new Adventure(1,45, new Date(startDate), new Date(startDate), "Adventure one: :o"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
}
