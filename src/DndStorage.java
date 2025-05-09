import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.ZoneId; 

public class DndStorage {
	
	public Class[] classes; 
	public Race[] races; 
	
	public Class[] getAllClasses(DndDatabase data) {
		
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
		
		try {
				String query = "SELECT * FROM Race";
				ResultSet results = data.runQuery(query);
				String query2 = "SELECT count(*) FROM Race"; 
				ResultSet result = data.runQuery(query2); 
				int classnum = 0; 
				if(result.next()) {
					classnum = result.getInt(1); 
					System.out.println(classnum);
				}
				Race[] races = new Race[classnum]; 
				int count = 0; 
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
		
		
		return races; 
	}
	
	
	
	public static void main(String[] args) {
		
		//Date date = new Date(0175, 11, 21); 
		
		//System.out.println(date); 
		
		
	
	    DndDatabase data = new DndDatabase();
		data.connect(); 
		
			String[][] top5R = data.top5races();
			String[][] top5C = data.top5classes(); 
			
			for(int i = 0; i < 5; i++) {
				System.out.println(top5R[i][0] + " " + top5R[i][1]);
			}
			System.out.println();
			for(int j = 0; j < 5; j++) {
				System.out.println(top5C[j][0] + " " + top5C[j][1]);
			} 
		
		/*
		//int count = data.countFindRaceAndLev(3, 5); 
		//int counts = data.countFindRace(3); 
		//int counts = data.countFindClass(4); 
		int counts = data.countFind(4, 4, 2, 2, 0, 100000); 
		Persona[] pers = data.Find(4, 4, 2, 2, 0, 100000);
		System.out.println(counts); 
		
		
		for(int i = 0; i < counts; i++) {
			System.out.println(pers[i]); 
		}
		
		/*
		Player play = new Player(56); 
		try {
			Persona[] pers = data.findPlayPersonas(play);
			for (int i = 0; i < pers.length; i++) {
				System.out.println(pers[i]); 
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		*/
	
		/*
		
		File persNames = new File("CharNames3.txt"); 
		File clasNum = new File("ClassNumbers3.txt"); 
		File racNum = new File("RaceNumbers3.txt"); 
		
		try {
			Scanner nam = new Scanner(persNames);
			Scanner cla = new Scanner(clasNum); 
			Scanner rac = new Scanner(racNum); 
			
			for (int i = 41; i < 58; i++) {
				
				int ch = 0; 
			
				while(nam.hasNext() & cla.hasNext() & rac.hasNext() & ch < 3 ) {
					String name = nam.nextLine(); 
					int space = name.indexOf(" "); 
					String first = name.substring(0, space); 
					name = name.substring(space + 1); 
					
					System.out.println(first + " " + name); 
					
					int clas = cla.nextInt(); 
					int race = rac.nextInt(); 
					Date date = new Date(0175, clas + 1, race - 1); 
					
					Persona adding = new Persona(i, first, name, clas, race, clas, date); 
					data.getAndsetClasName(adding); 
					data.getAndsetRaceName(adding); 
					System.out.println(adding); 
					data.insertPersona(adding);
					ch++; 
				}
			
			}
			
			nam.close(); 
			cla.close(); 
			rac.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
		   //TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
		
		
		
		
		
		/*
		File read = new File("PlayerNames.txt"); 
		try {
			Scanner scan = new Scanner(read);
			while(scan.hasNext()) {
				String name = scan.nextLine(); 
				int space = name.indexOf(" "); 
				String first = name.substring(0, space); 
				String last = name.substring(space + 1); 
				Player add = new Player(first, last); 
				data.insertPlayer(add);
				boolean test = data.playerExists(add);
				System.out.println(test + ": " + add + " player added"); 
			}
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		*/
		
		
		/*
		Player me = new Player("Lily", "Engen"); 
		Player notme = new Player("not", "me"); 
		boolean test = false; 
		boolean test1 = false; 
		try {
			test = data.playerExists(me);
			test1 = data.playerExists(notme); 
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("player does not exist"); 
		} 
		System.out.println(test + " " + test1); 
		*/
		
		
		
//	try {
//		String query = "SELECT * FROM Class";
//			ResultSet results = data.runQuery(query);
//		String query2 = "SELECT count(*) FROM Class"; 
//			ResultSet result = data.runQuery(query2); 
//			int classnum = 0; 
//			if(result.next()) {
//			classnum = result.getInt(1); 
//			System.out.println(classnum);
//			}
//		Class[] classes = new Class[classnum]; 
//		int count = 0; 
//		while(results.next()) {
//			String name = results.getString("ClassName"); 
//			int ID = results.getInt("ClassID"); 
//			
//			Class adding = new Class(name); 
//			adding.setClassID(ID);
//			classes[count] = adding; 
//			count++; 
//		}
//		for(Class cla: classes) {
//			System.out.println(cla); 
//		}
//		
//		query = "SELECT * FROM Race";
//		results = data.runQuery(query);
//		query2 = "SELECT count(*) FROM Race"; 
//		result = data.runQuery(query2); 
//		classnum = 0; 
//		if(result.next()) {
//		classnum = result.getInt(1); 
//		System.out.println(classnum);
//		}
//	Race[] races = new Race[classnum]; 
//	count = 0; 
//	while(results.next()) {
//		String name = results.getString("RaceName"); 
//		int ID = results.getInt("RaceID"); 
//		
//		Race adding = new Race(name); 
//		adding.setRaceID(ID);
//		races[count] = adding; 
//		count++; 
//	}
//	
//	for(Race ra : races) {
//		System.out.println(ra); 
//	}
//
//		
//		
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	}
}
