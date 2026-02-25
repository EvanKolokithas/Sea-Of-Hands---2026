package dataBase;

import java.io.File;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DatabaseManager {

	private static String databaseName = "seaOfHands";
	private static String databaseURL = "jdbc:derby:" + databaseName + ";create=true";
	
	private static Connection connection;
	private static boolean databasePopulated;
	
	public static String getDatabaseUrl() {
		return databaseURL;
	}
	
	public static boolean databasePopulated() {
		return databasePopulated;
	}
	
	
			
	public DatabaseManager() {
		
		
		InitializeDatabase();
		
	}

	
	private static void InitializeDatabase() {
		
		try {
			connection = DriverManager.getConnection(databaseURL);
			
			DatabaseMetaData databaseMeta = connection.getMetaData();
			
			ResultSet tablesFound = databaseMeta.getTables(null, null, "%", new String[] {"TABLE_NAME"} );
			
			//used to send commands to database and create sql scripts
			Statement stmt = connection.createStatement();
			
			//resets database
			
			while (tablesFound.next()) {
			    String tableName = tablesFound.getString("TABLE_NAME");
			    stmt.executeUpdate("DROP TABLE " + tableName);
			}
			
			//finalize
			stmt.close();
			
			//create initial table
			createInitialTables(connection);
			
		}
		catch(SQLException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	
	private static void createInitialTables(Connection conn) {

	    try {
	        DatabaseMetaData meta = conn.getMetaData();

	        ResultSet tables = meta.getTables(null, null, "GAME_TEXT", null);
	        
	        //checks to see if the table is already created
	        if (!tables.next()) {
	            try (Statement stmt = conn.createStatement()) {

	                stmt.executeUpdate(
	                        "CREATE TABLE game_text ("
	                                + "id INTEGER PRIMARY KEY NOT NULL GENERATED ALWAYS AS IDENTITY "
	                                + "(START WITH 1, INCREMENT BY 1),"
	                                + "message_type VARCHAR(50),"
	                                + "message_text CLOB)"
	                );

	                //System.out.println("Table GAME_TEXT created.");

	            }
	        } else {
	            //System.out.println("Table GAME_TEXT already exists.");
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	//checks if message already exists
	public boolean messageExists(String type) {

	    String sql = "SELECT 1 FROM game_text WHERE message_type = ?";

	    try (PreparedStatement ps = connection.prepareStatement(sql)) {

	        ps.setString(1, type);
	        ResultSet rs = ps.executeQuery();

	        return rs.next(); // true if a row exists

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return false;
	}
	
	//reuasble insert method
	public void insertMessage(String type, String text) {

	    String sql = "INSERT INTO game_text (message_type, message_text) VALUES (?, ?)";

	    try (PreparedStatement ps = connection.prepareStatement(sql)) {

	        ps.setString(1, type);
	        ps.setString(2, text);

	        ps.executeUpdate();

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public void populateGameData() {
		
		String introText = """
				Welcome to the Cabin.
				You have been here for as long as you can remember.	
					
				You know someone must have ostracized you long ago, 
				But it's been too long for you to remember why.
				
				There has to be a reason you have been shrouded in this isolation.
				Whoever put you away like this must have done it out of necessity.
				If you were not stranded, how might your presence alter those around you?
				
				The tide claws at the beach of your cabin rhythmically,
				Its hands are grasping for your walls before retreating back into its mass.
				
				Today, the hands will not retreat until you join them.
				You feel the urge to escape their reach.
				
				
				▄▀▀▀▀▄  ▄▀▀█▄▄▄▄  ▄▀▀█▄       ▄▀▀▀▀▄   ▄▀▀▀█▄ 
				█ █   ▐ ▐  ▄▀   ▐ ▐ ▄▀ ▀▄     █      █ █  ▄▀  ▀▄
				  ▀▄     █▄▄▄▄▄    █▄▄▄█     █      █ ▐ █▄▄▄▄ 
				▀▄   █    █    ▌   ▄▀   █     ▀▄    ▄▀  █    ▐ 
				█▀▀▀    ▄▀▄▄▄▄   █   ▄▀        ▀▀▀▀    █      
				▐       █    ▐   ▐   ▐                █       
				        ▐                             ▐       
				▄▀▀▄ ▄▄   ▄▀▀█▄   ▄▀▀▄ ▀▄  ▄▀▀█▄▄   ▄▀▀▀▀▄    
				█  █   ▄▀ ▐ ▄▀ ▀▄ █  █ █ █ █ ▄▀   █ █ █   ▐    
				▐  █▄▄▄█    █▄▄▄█ ▐  █  ▀█ ▐ █    █    ▀▄      
				  █   █   ▄▀   █   █   █    █    █ ▀▄   █     
				 ▄▀  ▄▀  █   ▄▀  ▄▀   █    ▄▀▄▄▄▄▀  █▀▀▀      
				█   █    ▐   ▐   █    ▐   █     ▐   ▐         
				▐   ▐            ▐        ▐                    
				""";
		
		String outroText = """
				The hands overwhelm you.
				They flood your home,
				latching onto each of your limbs before pulling in all directions.
				
				Each of your senses gives out:
				
				First, your touch as your nerves loosen from their cold grasp.
				Next, your taste as liquid pours into your lungs and stomach.
				Your smell and hearing go out at once as the hands drag you under their surface.
				
				Unable to breathe, drowning, all that's left is your sight.
				Beneath the sea of hands, you find what you were hiding from.
				The thing you've been hiding from all this time.
				
				Everything goes dark, and you die, surrounded at the bottom of your sea.

				""";
		
		if (!messageExists("intro")) {
	        insertMessage("intro", introText);
	    }

	    if (!messageExists("outro")) {
	        insertMessage("outro", outroText);
	    }

	    databasePopulated = true;

	    }
	   
		
	    	
	
	public String retrieveMessage(String type) {

	    String message = "";

	    String sql = "SELECT message_text FROM game_text WHERE message_type = ?";

	    try (PreparedStatement ps = connection.prepareStatement(sql)) {

	        ps.setString(1, type);

	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            message = rs.getString("message_text");
	        }

	    } 
	    catch(SQLException e) {
			System.err.println(e.toString());
		}
		catch (Exception e) {
			System.err.println(e.toString());
		}
	    return message;
	}
}
