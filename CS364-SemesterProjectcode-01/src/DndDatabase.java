import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DndDatabase {

	
	private String url = "jdbc:mysql://138.49.184.47:3306/engen7981_DnDStorage?user=engen7981&password=";
	private Connection connection;
	
	public DndDatabase() {
		String password = "wdmgtz!T!M5bJLSN"; 
		url = url + password;
	}
	
	public void connect() {
		try {
			connection = DriverManager.getConnection(url);
		} catch (SQLException e) {
			System.out.println("Cannot connect!");
			System.out.println(e);
		}
	}
	
	public void disconnect() {
		try {
			connection.close();
		} catch (SQLException e) {
			System.out.println("Cannot disconnect!");
		}
	}
	
	
	public ResultSet runQuery(String query) throws SQLException {
		PreparedStatement stmt = connection.prepareStatement(query);
		ResultSet results = stmt.executeQuery();
		return results;
	}
	
	public void insertPlayer(Player e) throws SQLException {
		String sql = "INSERT INTO Player (FirstName, LastName) VALUES (?, ?)";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, e.getFirstName());
		stmt.setString(2, e.getLastName());
		//stmt.setString(3, e.getFirstName());
		//stmt.setString(4, e.getMiddleName());
		// stmt.setString(5, e.getLastName());
		int numRowsAffected = stmt.executeUpdate();
		System.out.println("Number of rows affected: " + numRowsAffected);
	}
	
	public boolean playerExists(Player play) throws SQLException {
		String query = "SELECT * FROM Player WHERE FirstName = ? AND LastName = ?";
		PreparedStatement stmt = connection.prepareStatement(query);
		String first = play.getFirstName(); 
		String last = play.getLastName();
		stmt.setString(1, first);
		stmt.setString(2, last); 
		ResultSet results = stmt.executeQuery();
		String neFir; 
		String neLas; 
		results.next(); 
			neFir = results.getString("FirstName");
			neLas = results.getString("LastName"); 
			if(neFir.equals(play.getFirstName())) {
				if(neLas.equals(play.getLastName())) {
					return true; 
				} else {
					return false; 
				}
			} else {
				return false; 
			}
		
		
	}
	
	public ResultSet playerLookupByID(Player play) throws SQLException {
		String query = "SELECT * FROM Player WHERE PlayID = ? ";
		PreparedStatement stmt = connection.prepareStatement(query);
		int id = play.getPlayID(); 
		stmt.setInt(1, id);
		ResultSet results = stmt.executeQuery();
		return results;
	}
	
	public ResultSet playerLookup(Player play) throws SQLException{
		String query = "SELECT * FROM Player WHERE FirstName = ? AND LastName = ?";
		PreparedStatement stmt = connection.prepareStatement(query);
		String first = play.getFirstName(); 
		String last = play.getLastName();
		stmt.setString(1, first);
		stmt.setString(2, last); 
		ResultSet results = stmt.executeQuery();
		return results; 
	}

	
	public Class[] getAllClasses(DndDatabase data) {
		Class[] classes = new Class[0]; 
		try {
				String query = "SELECT * FROM Class";
				ResultSet results = data.runQuery(query);
				String query2 = "SELECT count(*) FROM Class"; 
				ResultSet result = data.runQuery(query2); 
				int classnum = 0; 
				if(result.next()) {
					classnum = result.getInt(1); 
				}
				classes = new Class[classnum]; 
				int count = 0; 
				while(results.next()) {
					String name = results.getString("ClassName"); 
					int ID = results.getInt("ClassID"); 
		
					Class adding = new Class(name); 
					adding.setClassID(ID);
					classes[count] = adding; 
					count++; 
				}
					
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return classes; 
		}


	public Race[] getAllRace(DndDatabase data) {
		Race[] races = new Race[0]; 
		try {
				String query = "SELECT * FROM Race";
				ResultSet results = data.runQuery(query);
				String query2 = "SELECT count(*) FROM Race"; 
				ResultSet result = data.runQuery(query2); 
				int classnum = 0; 
				if(result.next()) {
					classnum = result.getInt(1); 
					//System.out.println(classnum);
				}
				races = new Race[classnum]; 
				int count = 0; 
				while(results.next()) {
					String name = results.getString("RaceName"); 
					int ID = results.getInt("RaceID"); 
						
					Race adding = new Race(name); 
					adding.setRaceID(ID);
					races[count] = adding; 
					count++; 
				}
	
//				for(Race ra : races) {
//					System.out.println(ra); 
//				}
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	
		return races; 
	}
	
}
