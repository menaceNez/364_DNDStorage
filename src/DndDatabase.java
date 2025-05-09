import java.sql.Connection;
import java.sql.Date;
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
	
	public Connection connect() {
		try {
			return connection = DriverManager.getConnection(url);
		} catch (SQLException e) {
			System.out.println(e);
			return null;
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

	
	public void insertPersona(Persona a) throws SQLException {
		String sql = "INSERT INTO Persona (PlayID, CharFirstName, CharMiddName, CharLastName, ClassID, RaceID, Lev, CreationDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setInt(1,  a.getPlayID());
		stmt.setString(2, a.getPFirstName());
		stmt.setString(3, a.getMiddName());
		stmt.setString(4, a.getPLastName());
		stmt.setInt(5, a.getClassID());
		stmt.setInt(6, a.getRaceID());
		stmt.setInt(7, a.getLev());
		stmt.setDate(8, a.getCal());
		int numRowsAffected = stmt.executeUpdate();
		System.out.println("Number of rows affected: " + numRowsAffected);
	}
	
	public void updatePersona(Persona cur, Persona upinfo) throws SQLException{
		String sql = "UPDATE Persona SET CharFirstName = ?, CharMiddName = ?, CharLastName = ?, Lev = ? WHERE CharID = ?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		int chaId = cur.getCharID(); 
		stmt.setString(1, upinfo.getPFirstName());
		stmt.setString(2, upinfo.getMiddName());
		stmt.setString(3, upinfo.getPLastName());
		stmt.setInt(4, upinfo.getLev());
		stmt.setInt(5, chaId);
		int numRowsAffected = stmt.executeUpdate();
		System.out.println("Number of rows affected: " + numRowsAffected);
	}
	
	public void getAndSetCharID(Persona a) throws SQLException{
		String query = "SELECT CharID FROM Persona "
				+ "WHERE charFirstName = ? AND charLastName = ?" ;
		PreparedStatement stmt = connection.prepareStatement(query);
		String first = a.getPFirstName(); 
		String last = a.getPLastName(); 
		stmt.setString(1, first);
		stmt.setString(2, last);
		ResultSet result = stmt.executeQuery(); 
		result.next(); 
		int charID = result.getInt("CharID"); 
		a.setCharID(charID); 
	}
	
	
	public Persona[] findPlayPersonas(Player p) throws SQLException {
		int plID = p.getPlayID(); 
		String query2 = "SELECT count(*) FROM Persona WHERE PlayID = ?"; 
		PreparedStatement stmt2 = connection.prepareStatement(query2);
		stmt2.setInt(1, plID);
		ResultSet result = stmt2.executeQuery(); 
		result.next(); 
		int numPers = result.getInt(1); 
		Persona[] pers = new Persona[numPers]; 
		String query = "SELECT * FROM Persona WHERE PlayID = ?" ;
		PreparedStatement stmt = connection.prepareStatement(query);
		stmt.setInt(1, plID);
		result = stmt.executeQuery(); 
		int i = 0; 
		while(result.next() && i < numPers) {
			String first = result.getString("CharFirstName"); 
			String midd = result.getString("CharMiddName"); 
			String last = result.getString("CharLastName"); 
			int claId = result.getInt("ClassID"); 
			int racId = result.getInt("RaceID"); 
			int chaId = result.getInt("CharID"); 
			int lev = result.getInt("Lev"); 
			Date da = result.getDate("CreationDate"); 
			Persona adding = new Persona(plID, chaId, first, midd, last, claId, racId, lev, da); 
			getAndsetClasName(adding); 
			getAndsetRaceName(adding); 
			pers[i] = adding; 
			i++; 
		}
		return pers; 
	}
	
	public boolean getAndsetClassID(Persona pers) throws SQLException {
		String query = "SELECT ClassID FROM Class WHERE ClassName = ?" ;
		PreparedStatement stmt = connection.prepareStatement(query);
		String cla = pers.getClaName(); 
		stmt.setString(1, cla);
		ResultSet result = stmt.executeQuery(); 
		result.next(); 
		int claID =  result.getInt("ClassID"); 
		pers.setClassID(claID); 
		return true; 
	}
	
	public boolean getAndsetRaceID(Persona pers) throws SQLException {
		String query = "SELECT RaceID FROM Race WHERE RaceName = ?" ;
		PreparedStatement stmt = connection.prepareStatement(query);
		String rac = pers.getRacName(); 
		stmt.setString(1, rac);
		ResultSet result = stmt.executeQuery(); 
		result.next(); 
		int racID =  result.getInt("RaceID"); 
		pers.setRaceID(racID); 
		return true; 
	}
	
	public boolean getAndsetRaceName(Persona pers) throws SQLException {
		String query = "SELECT RaceName FROM Race WHERE RaceID = ?" ;
		PreparedStatement stmt = connection.prepareStatement(query);
		int rac = pers.getRaceID(); 
		stmt.setInt(1, rac);
		ResultSet result = stmt.executeQuery(); 
		result.next(); 
		String racID =  result.getString("RaceName"); 
		pers.setRacName(racID);
		return true; 
	}
	
	
	public boolean getAndsetClasName(Persona pers) throws SQLException {
		String query = "SELECT ClassName FROM Class WHERE ClassID = ?" ;
		PreparedStatement stmt = connection.prepareStatement(query);
		int cla = pers.getClassID(); 
		stmt.setInt(1, cla);
		ResultSet result = stmt.executeQuery(); 
		result.next(); 
		String claID =  result.getString("ClassName"); 
		pers.setClaName(claID);
		return true; 
	}
	
	/*
	 * things between need to be copied
	 */
		
		
		
	public int countFind(int racFir, int racSec, int claFir, int claSec, int levFir, int levSec) {
		int numPers = 0; 
		String query = "SELECT count(*)"
				+ "	FROM Persona JOIN (SELECT RaceID AS RacID, RaceName AS RacName"
				+ "							FROM Race) AS IndividRace"
				+ "                            ON Persona.RaceID = RacID"
				+ "                            JOIN (SELECT ClassID AS ClaID, ClassName AS ClaNam"
				+ "									FROM Class) AS Clas"
				+ "							ON Persona.ClassID = ClaID"
				+ "	WHERE RaceID BETWEEN ? AND ? AND ClassID BETWEEN ? AND ? AND Lev BETWEEN ? AND ? ; "; 
		try {
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setInt(1, racFir);
			stmt.setInt(2, racSec);
			stmt.setInt(3, claFir);
			stmt.setInt(4, claSec);
			stmt.setInt(5, levFir);
			stmt.setInt(6, levSec);
			ResultSet result = stmt.executeQuery();
			result.next(); 
			numPers = result.getInt(1); 
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return numPers; 
	}
	
	public Persona[] Find(int racFir, int racSec, int claFir, int claSec, int levFir, int levSec) {
		int numPers = countFind(racFir, racSec, claFir, claSec, levFir, levSec); 
		Persona[] pers = new Persona[numPers]; 
		String query = "SELECT PlayID, CharID , CharFirstName, "
				+ "				CharMiddName , CharLastName, Rac.RaceName, Clas.ClassName,"
				+ "				Lev , CreationDate"
				+ "				FROM Persona JOIN (SELECT RaceID, RaceName"
				+ "										FROM Race) AS Rac"
				+ "				                           ON Persona.RaceID = Rac.RaceID"
				+ "				                         JOIN (SELECT ClassID, ClassName"
				+ "											FROM Class) AS Clas"
				+ "										ON Persona.ClassID = Clas.ClassID"
				+ "					WHERE Persona.RaceID BETWEEN ? AND ? AND Persona.ClassID BETWEEN ? AND ? AND Lev BETWEEN ? AND ?; "; 
		try {
			PreparedStatement stmt = connection.prepareStatement(query);
			stmt.setInt(1, racFir);
			stmt.setInt(2, racSec);
			stmt.setInt(3, claFir);
			stmt.setInt(4, claSec);
			stmt.setInt(5, levFir);
			stmt.setInt(6, levSec);
			ResultSet result = stmt.executeQuery();
			int i = 0; 
			while(result.next() && i < numPers) {
				int plID = result.getInt("PlayID"); 
				String first = result.getString("CharFirstName"); 
				String midd = result.getString("CharMiddName"); 
				String last = result.getString("CharLastName"); 
				String cla = result.getString("Clas.ClassName"); 
				String rac = result.getString("Rac.RaceName"); 
				int chaId = result.getInt("CharID"); 
				int lev1 = result.getInt("Lev"); 
				Date da = result.getDate("CreationDate"); 
				Persona adding = new Persona(plID, chaId, first, midd, last, cla, rac, lev1, da); 
				pers[i] = adding; 
				i++; 
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		return pers; 
	}
	
	public String getClassName(int claID) throws SQLException {
		String query = "SELECT ClassName\r\n"
				+ "FROM Class\r\n"
				+ "WHERE ClassID Like ?; ";
		PreparedStatement stmt = connection.prepareStatement(query); 
		stmt.setInt(1, claID);
		ResultSet result = stmt.executeQuery(); 
		result.next(); 
		String claNam =  result.getString("ClassName"); 
		
		return claNam;
	}
	
	public String getRaceName(int racID) throws SQLException {
		String query = "SELECT RaceName\r\n"
				+ "FROM Race\r\n"
				+ "WHERE RaceID Like ?; ";
		PreparedStatement stmt = connection.prepareStatement(query); 
		stmt.setInt(1, racID);
		ResultSet result = stmt.executeQuery(); 
		result.next(); 
		String racNam =  result.getString("RaceName"); 
		
		return racNam;
	}
	
	
	
	public boolean deletePers(Persona p) throws SQLException  {
		String query = "DELETE FROM Persona WHERE CharID = ?";
		PreparedStatement stmt = connection.prepareStatement(query);
		stmt.setInt(1, p.getCharID());
		int numRowsAffected = stmt.executeUpdate();
		return numRowsAffected > 0;
	}
	
	public String[][]	top5classes() {
		String[][] top5 = new String[5][2];
		String query = "SELECT count(*) AS tot, ClassID\r\n"
				+ "FROM Persona\r\n"
				+ "group by ClassID\r\n"
				+ "having tot > 10\r\n"
				+ "order by tot DESC\r\n"
				+ "limit 5;  ";
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement(query);
		
		ResultSet result = stmt.executeQuery();
		int i = 0;
		while(result.next() && i < 5){
			int count = result.getInt("tot");
			int claID = result.getInt("ClassID");
			String className = getClassName(claID);
			top5[i][0] = "" + count;
			top5[i][1] = className;
			i++;
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return top5;
	}
	
	public String[][] top5races() {
		String[][] top5 = new String[5][2];
		String query = "SELECT count(*) AS tot, RaceID\r\n"
				+ "FROM Persona \r\n"
				+ "group by RaceID\r\n"
				+ "having tot > 10\r\n"
				+ "order by tot DESC\r\n"
				+ "limit 5; ";
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement(query);

		ResultSet result = stmt.executeQuery();
		int i = 0;
		while(result.next() && i < 5){
			int count = result.getInt("tot");
			int racID = result.getInt("RaceID");
			String raceName = getRaceName(racID);
			top5[i][0] = "" + count;
			top5[i][1] = raceName;
			i++;
		}		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return top5;
	}
	
	
	public double avgChartoPlay() {
		double avg = 0;
		String query = "SELECT avg(sums.counts) as AVER FROM (SELECT count(*) as counts\r\n"
				+ "FROM Persona\r\n"
				+ "GROUP BY PlayID) as sums; ";
		try {
			PreparedStatement stmt = connection.prepareStatement(query);
			ResultSet result = stmt.executeQuery(); 
			result.next(); 
			avg =  result.getDouble("AVER"); 
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return avg; 
	}
	
	
	
	/* 
	 * above things need to be copied
	 */
	
	
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
				classes = new Class[classnum + 1]; 
				int count = 1; 
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
				races = new Race[classnum + 1]; 
				int count = 1; 
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
