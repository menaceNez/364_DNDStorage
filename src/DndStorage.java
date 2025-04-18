import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DndStorage {
	
	
	public static void main(String[] args) {
		
		DndDatabase data = new DndDatabase();
		data.connect();
	
		
		
	try {
		String query = "SELECT * FROM Class";
			ResultSet results = data.runQuery(query);
		String query2 = "SELECT count(*) FROM Class"; 
			ResultSet result = data.runQuery(query2); 
			int classnum = 0; 
			if(result.next()) {
			classnum = result.getInt(1); 
			System.out.println(classnum);
			}
		Class[] classes = new Class[classnum]; 
		int count = 0; 
		while(results.next()) {
			String name = results.getString("ClassName"); 
			int ID = results.getInt("ClassID"); 
			
			Class adding = new Class(name); 
			adding.setClassID(ID);
			classes[count] = adding; 
			count++; 
		}
		for(Class cla: classes) {
			System.out.println(cla); 
		}
		
		query = "SELECT * FROM Race";
		results = data.runQuery(query);
		query2 = "SELECT count(*) FROM Race"; 
		result = data.runQuery(query2); 
		classnum = 0; 
		if(result.next()) {
		classnum = result.getInt(1); 
		System.out.println(classnum);
		}
	Race[] races = new Race[classnum]; 
	count = 0; 
	while(results.next()) {
		String name = results.getString("RaceName"); 
		int ID = results.getInt("RaceID"); 
		
		Race adding = new Race(name); 
		adding.setRaceID(ID);
		races[count] = adding; 
		count++; 
	}
	
	for(Race ra : races) {
		System.out.println(ra); 
	}

		
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}