import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

/*
 * Creates an Adventure by allowing the player to:
 *  select a campaign
 *  View players and related characters
 *  	This allows user to see what chara ids they have
 *  player will select charaIDS and a campaign -> input a name for adventure -> hit create adventure
 *  should add functionality to delete adventure, add and remove characters from adventure (PUT IN VIEW ADVENTURES)
 *  
 *  
 *  
 * 
 */
public class AdventureQueries {
	private DndDatabase db;
	private Connection connect;

	public AdventureQueries(DndDatabase db) {
		this.db = db;
		this.connect = db.connect();
	}

	public void insertAdventuresFromFile() {
		try (BufferedReader reader = new BufferedReader(new FileReader("CampaignNames"))) {

			long millis = System.currentTimeMillis();
			Date startDate = new Date(millis);
//			System.out.println("DID THIS PRINT? \n" + startDate);
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

	public void insertCampaignsFromFile() {
		try (BufferedReader reader = new BufferedReader(new FileReader("CampaignNames"))) {
			String line;

			while ((line = reader.readLine()) != null) {
				Campaign cp = new Campaign(line);
//				System.out.println("Actually worked! " + line + " " + cp.getCampName() + " " + cp.getCampId());
				insertCampaign(cp);
			}
		} catch (IOException err) {
			System.out.println("InsertCampaignsFromFile Errror: " + err);
		}
	}

	public void insertCampaign(Campaign campaign) {
		try {
			String sql = "INSERT INTO Campaign (CampaignName) VALUES (?)";
			PreparedStatement stmt = connect.prepareStatement(sql);
			stmt.setString(1, campaign.getCampName());

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

			while (set.next()) {
				Campaign cmp = new Campaign(set.getString(2));

				cmp.setCampId(set.getInt(1));
				camps.add(cmp);
			}

			return camps;
		} catch (SQLException error) {
			System.out.println("Error in getAllCampaigns(): " + error.toString());
			return null;
		}
	}

	public ArrayList<Player> getAllPlayer() {
		try {
			String sql = "SELECT * FROM Player";
			PreparedStatement stmt = connect.prepareStatement(sql);
			ResultSet set = stmt.executeQuery();
			ArrayList<Player> retList = new ArrayList<>();

			while (set.next()) {
				Player pl = new Player(set.getString(3), set.getString(2));
				pl.setPlayID(set.getInt(1));
				retList.add(pl);
			}

			return retList;
		} catch (SQLException error) {
			System.out.println(error);

			return null;
		}
	}

	public ArrayList<Persona> getPlayerCharacter(String playerID) {
		try {
			String sql = "SELECT * FROM Persona WHERE Persona.PlayID = ?";
			PreparedStatement stmt = connect.prepareStatement(sql);
			stmt.setInt(1, Integer.parseInt(playerID));

			ResultSet result = stmt.executeQuery();

			ArrayList<Persona> characters = new ArrayList<>();

			while (result.next()) {
				int charID = result.getInt(1);
				int playID = result.getInt(2);
				int classID = result.getInt(3);
				int raceID = result.getInt(4);
				String lastName = result.getString(5);
				String middleName = result.getString(6);
				String firstName = result.getString(7);
				int level = result.getInt(8);
				java.sql.Date date = result.getDate(9);

//				System.out.println(charID + " " + playID + " " + firstName + " " + lastName + " " + date);
				Persona created = new Persona(playID, firstName, "", lastName, classID, raceID, level, date);
				created.setCharID(charID);

				characters.add(created);
			}

			return characters;
		} catch (SQLException error) {
			System.out.println(error.toString());
			return null;
		}
	}

	public int insertAdventure(int campID, int playID, String advName) {
		try {
			String sql = "INSERT INTO Adventure (CampaignID, PlayID, AdventureName, StartDate) VALUES (?,?,?,?)";

			PreparedStatement stmt = connect.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			stmt.setInt(1, campID);
			stmt.setInt(2, playID);
			stmt.setString(3, advName);
			stmt.setDate(4, new Date(System.currentTimeMillis()));

			int rows = stmt.executeUpdate();
			if (rows == 0) {
				System.out.println("insertAdventure@AdventureQueries failed to insert");
			}

			ResultSet genKeys = stmt.getGeneratedKeys();
			if (genKeys.next()) {
				long id = genKeys.getInt(1);

//				System.out.println("GOT AN ID inside insertAdventure: " + id);
				return (int) id;
			}
			return -1;
		} catch (SQLException error) {
			System.out.println("[Error in createAdventure@AdventureQueries]: " + error);
			return -1;
		}
	}

	public boolean validatePersonaID(int PID1) {
		try {

			String sql = "SELECT count(*) AS Total FROM Persona WHERE CharID = ?";

			PreparedStatement stmt = connect.prepareStatement(sql);

			stmt.setInt(1, PID1);

			ResultSet res = stmt.executeQuery();

			int count = -1;
			if (res.next()) {
				count = res.getInt("Total");
			}

			if (count == 1) {
				return true;
			} else {
				System.out.println("Did not return valid count " + count);
				return false;
			}
		} catch (SQLException error) {
			System.out.println("[Error in validatePersonaIDS@AdventureQueries]: " + error);
			return false;
		}

	}

	public boolean validateDmID(int playerID) {
		try {

			String sql = "SELECT count(*) AS Total FROM Player WHERE PlayID = ?";

			PreparedStatement stmt = connect.prepareStatement(sql);

			stmt.setInt(1, playerID);

			ResultSet res = stmt.executeQuery();

			int count = -1;
			if (res.next()) {
				count = res.getInt("Total");
			}

			if (count == 1) {
				return true;
			}

			return false;
		} catch (SQLException error) {
			System.out.println("[Error in validatePersonaIDS@AdventureQueries]: " + error);
			return false;
		}

	}

	// checks to see if the adventure name already exists
	public boolean validateAdventureName(String advName) {
		try {
			String sql = "SELECT count(*) AS Total FROM Adventure WHERE AdventureName = ?";
			PreparedStatement stmt = connect.prepareStatement(sql);

			stmt.setString(1, advName);
			ResultSet res = stmt.executeQuery();

			int count = -1;
			if (res.next()) {
				count = res.getInt("Total");
			}

			if (count > 0) {
				return true;
			}

			return false;
		} catch (SQLException error) {
			System.out.println("ValidateAventureName@AventureQueries Error: " + error);
			return false;
		}
	}

	public ArrayList<Adventure> getAdventurePersonas(int advID) {
		// get the personas associated with a adventure, should query each persona to
		// get full info in db
		try {
			String sql = "SELECT * FROM PersonaPlaysIn " + "JOIN Persona ON PersonaPlaysIn.CharID = Persona.CharID "
					+ "JOIN Adventure ON Adventure.AdventureID = PersonaPlaysIn.AdventureID "
					+ "JOIN Class ON Class.ClassID = Persona.ClassID " + "JOIN Race ON Race.RaceID = Persona.RaceID "
					+ "WHERE PersonaPlaysIn.AdventureID = ?";
			PreparedStatement stmt = connect.prepareStatement(sql);
			stmt.setInt(1, advID);

			ResultSet res = stmt.executeQuery();
			ArrayList<Adventure> adventures = new ArrayList<>();

			while (res.next()) {

				Adventure adv = new Adventure(res.getInt("CampaignID"), res.getInt("PlayID"), 
						res.getDate("StartDate"), res.getString("AdventureName"),
						new Persona(res.getInt("PlayID"), res.getInt("CharID"), res.getString("CharFirstName"), res.getString("CharLastName"),
								res.getString("ClassName"), res.getString("RaceName")));
				adventures.add(adv);
			}

			return adventures;
		} catch (SQLException error) {
			System.out.println("Error in getAdventurePersonas@AdventureQueries: " + error);
			return null;
		}
	}

	public void insertPersonaPlaysIn(int advID, int personaID) {
		try {
			String sql = "INSERT INTO PersonaPlaysIn (AdventureID, CharID) VALUES (?,?)";
			PreparedStatement stmt = connect.prepareStatement(sql);

			stmt.setInt(1, advID);
			stmt.setInt(2, personaID);

			stmt.executeUpdate();
		} catch (SQLException error) {
			System.out.println("Error in insertPersonaPlaysIn@AdventureQueries " + error);
		}
	}

	public ArrayList<Adventure> getAdventures() {
		try {
			String sql = "SELECT * FROM Adventure JOIN Campaign ON Adventure.CampaignID = Campaign.CampaignID";
			PreparedStatement stmt = connect.prepareStatement(sql);

			ResultSet res = stmt.executeQuery();
			ArrayList<Adventure> adventures = new ArrayList<>();

			while (res.next()) {
				Adventure adventure = new Adventure(
						res.getInt("AdventureID"), res.getInt("PlayID"), res.getDate("StartDate"),  
						res.getString("AdventureName"), new Campaign(res.getString("CampaignName"))
					);
				adventures.add(adventure);
			}

			return adventures;
		} catch (SQLException error) {
			System.out.println("Error in getAdventures@AdventureQueries " + error);
			return null;
		}
	}

	public boolean deletePersonaPlaysIn(int advID, int personaID) {
		System.out.println("AdvID: "+advID+" peronsaID: "+personaID);
		try {
			String sql = "DELETE FROM PersonaPlaysIn WHERE CharID = ? AND AdventureID = ?";
			PreparedStatement stmt = connect.prepareStatement(sql);

			stmt.setInt(1, personaID);
			stmt.setInt(2, advID);
			
			int rows = stmt.executeUpdate();
//			System.out.println("Rows affectd: " + rows);

			return true;
		} catch (SQLException error) {
			System.out.println("Error in deletePersonaPlaysIn@AdventureQueries: " + error);
			return false;
		}
	}

	public boolean updateAdventureName(String name, int advID) {
		try {
			String sql = "UPDATE Adventure SET AdventureName = ? WHERE AdventureID = ?";
			PreparedStatement stmt = connect.prepareStatement(sql);

			stmt.setString(1, name);
			stmt.setInt(2, advID);

			if (stmt.executeUpdate() == 0)
				return false; // if nothing changes

			return true;
		} catch (SQLException error) {
			System.out.println("Error in updateAdventureName@AdventureQueries: " + error);
			return false;
		}
	}
	
	public int insertEvent(int advID, String event) {
		try {
			String sql = "INSERT INTO PlaySession (AdventureID, StartTime, Event) VALUES (?,?,?)";
			PreparedStatement stmt = connect.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			stmt.setInt(1, advID);
			stmt.setDate(2, new Date(System.currentTimeMillis()));
			stmt.setString(3, event);

			stmt.executeUpdate();

			ResultSet set = stmt.getGeneratedKeys();

			int key;
			while(set.next()) {
				return key = (int) set.getInt(1);
			}

			return -1;
		}
		catch(SQLException error) {
			System.out.println("Error in insertEvent@AdventureQueries: "+ error);
			return -1;
		}
	}
	
	public ArrayList<Event> getEvents(int advID) {
		try {
			String sql = "SELECT * FROM PlaySession WHERE PlaySession.AdventureID = ?";
			
			PreparedStatement stmt = connect.prepareStatement(sql);
			
			stmt.setInt(1, advID);
			
			ResultSet res = stmt.executeQuery();
			ArrayList<Event> events = new ArrayList<>();

			while(res.next()) {
				events.add(new Event(res.getInt("PlaySessionID"), res.getString("Event")));	
			}

			return events;
		}
		catch(SQLException error) {
			System.out.println("Error in getEvents@AdventureQuereis "+error);
			return null;
		}	
	}
	
	public boolean deleteEvent(int eventID) {
		try {
			String sql = "DELETE FROM PlaySession WHERE PlaySession.PlaySessionID = ?";
			
			PreparedStatement stmt = connect.prepareStatement(sql);
			stmt.setInt(1, eventID);

			int rows = stmt.executeUpdate();
			if (rows == 0) {
				return false;
			}
			
			return true;
		}
		catch(SQLException error) {
			System.out.println("Error in deleteEvent@AdventureQueries: "+ error);
			return false;
		}
	}

	public void closeConnection() {
		try {
			connect.close();
		} catch (SQLException error) {
			System.out.println("Error closing connection :" + error.toString());
		}
	}

}
