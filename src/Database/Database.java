package Database;
import java.net.URLEncoder;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {

	/*
	 * load SQL driver (JDBC: Java Database Connector/ODBC)
	 * - add to build path
	 * 
	 * set up our database (script)
	 * 
	 * connect to the database
	 * 
	 * insert/modify/delete data (Java)
	 * 
	 * query data (Java)
	 * 
	 * disconnect from the database
	 * 
	 */

	 /* SQLite connection to a local database */
//	private String url = "jdbc:sqlite:/Users/asauppe/Documents/teaching/cs364/Company.db";

	/* MySQL connection to a local database */
    private String url = "jdbc:mysql://localhost:3306/ymcatest?user=root&password=";


	// private String url = "jdbc:mysql://138.49.184.47:3306/maringer5245?user=maringer5245&password="; // password added in constructor

	private Connection connection;
	
	public Database() {
		String password = "password"; //TODO: set this to your password
		try {
			String encodedPassword = URLEncoder.encode(password, "UTF-8");
			password = encodedPassword;
		} catch (UnsupportedEncodingException e) {
			System.out.println("Error Encoding the Password");
		}
	
		url = url + password;
	}
	
	public void connect() {							//Connect to database when given url
		try {
			connection = DriverManager.getConnection(url);
			System.out.println("Connection Successful");
		} catch (SQLException e) {
			System.out.println("Cannot connect!");
			System.out.println(e);
		}
	}
	
	public void disconnect() {					//disconnect from database
		try {
			connection.close();
		} catch (SQLException e) {
			System.out.println("Cannot disconnect!");
		}
	}
	
	public ResultSet runQuery(String query) throws SQLException {		//Runs a query in the form of a string and returns a result set
		PreparedStatement stmt = connection.prepareStatement(query);
		ResultSet results = stmt.executeQuery();
		return results;
	}
	
	public String makeQuery(int teamID, String selectedEntity) { //Dynamically make query depending on which option is chosen in combo box
	    String query = "";
	    
	    switch (selectedEntity) {
	        case "Coach":
	            query = "SELECT CoachName, Birthdate FROM Coach JOIN CoachedBy ON CoachedBy.CoachID = Coach.CoachID WHERE CoachedBy.TeamID = '" + teamID + "'";
	            break;
	        case "Player":
	            query = "SELECT Player.PlayerName, PlaysFor.TeamID, Player.Birthdate, Player.Position FROM Player JOIN PlaysFor ON PlaysFor.PlayerID = Player.PlayerID WHERE PlaysFor.TeamID = '" + teamID + "'";
	            break;
	        case "Game":
	            query = "SELECT Game.Week, Game.Location, Plays.Result FROM Game JOIN Plays ON Plays.GameID = Game.GameID WHERE Plays.TeamID = '" + teamID + "'";
	            break;
	        default:
	            throw new IllegalArgumentException("Invalid option selected: " + selectedEntity);
	    }
	    
	    return query;
	}
	
	public String makeQuery2(int playerID) {  //Make query to fetch player stats
	    String query = "SELECT Player.PlayerName, Player.PlayerID, PlayerStat.PlayerRushYD, PlayerStat.PlayerRushTD, PlayerStat.PlayerPassYD, PlayerStat.PlayerPassTD, PlayerStat.PlayerRecYD, PlayerStat.PlayerRecTD, PlayerStat.PlayerInterceptions FROM Player JOIN PlayerStat ON Player.PlayerID = PlayerStat.PlayerID Where Player.PlayerID = '" + playerID + "'";
	    
	   
	    return query;
	}
	
	public String makeQuery3(int rushYD, int rushTD, int passYD, int passTD, int recYD, int recTD, String selectedStat) { 
		String query = "SELECT Player.PlayerName, PlayerStat.PlayerRushYD, PlayerStat.PlayerRushTD, " 
                + "PlayerStat.PlayerPassYD, PlayerStat.PlayerPassTD, PlayerStat.PlayerRecYD, PlayerStat.PlayerRecTD "
                + "FROM Player "
                + "JOIN PlayerStat ON Player.PlayerID = PlayerStat.PlayerID "
                + "WHERE PlayerStat.PlayerRushYD >= " + rushYD 
                + " AND PlayerStat.PlayerRushTD >= " + rushTD 
                + " AND PlayerStat.PlayerPassYD >= " + passYD 
                + " AND PlayerStat.PlayerPassTD >= " + passTD 
                + " AND PlayerStat.PlayerRecYD >= " + recYD 
                + " AND PlayerStat.PlayerRecTD >= " + recTD
                + " ORDER BY PlayerStat." + selectedStat + " DESC"
                + " LIMIT 10;";  

   return query;

	}
	
	public void insertPlayer(String name, String birthdate, String position) throws SQLException {  //Helper method to insertPlayer into database
		String query = "INSERT INTO Player (Playername, Birthdate, Position) VALUES (?,?,?)";
		PreparedStatement stmt = connection.prepareStatement(query);
		stmt.setString(1,name);
		stmt.setString(2,birthdate);
		stmt.setString(3,position);
		stmt.executeUpdate();
	}
	
	public void updatePlayer(int playerID, String name, String birthdate, String position) throws SQLException {  //Helper Method to updatePlayer in database
		String query = "UPDATE PLAYER SET PlayerName = ?, Birthdate = ?, Position = ? Where PlayerID = ?";
		PreparedStatement stmt = connection.prepareStatement(query);
		stmt.setString(1,name);
		stmt.setString(2,birthdate);
		stmt.setString(3,position);
		stmt.setInt(4,playerID);
		stmt.executeUpdate();
	}
	
	public void deletePlayer(String playerName) throws SQLException {			//Helper method to delete player in database
		String query = "DELETE FROM PLAYER WHERE PlayerName = ?";
		PreparedStatement stmt = connection.prepareStatement(query);
		stmt.setString(1, playerName);
		stmt.executeUpdate();
	}
	
	
	public void insertPlayerStat(String name, int playerID) throws SQLException {  //Helper method to insertPlayerStat into database
		String query = "INSERT INTO PLAYERSTAT (PlayerID, PlayerRushYD, PlayerRushTD, PlayerPassYD, PlayerPassTD, PlayerRecYD, PlayerRecTD, PlayerInterceptions) "
				+ "VALUES (?,0,0,0,0,0,0,0)";
		PreparedStatement stmt = connection.prepareStatement(query);
		stmt.setInt(1,playerID);
		stmt.executeUpdate();
	}
	
	public void deletePlayerStat(int playerID) throws SQLException {			//Helper method to delete player in database
		String query = "DELETE FROM PLAYERSTAT WHERE PlayerID = ?";
		PreparedStatement stmt = connection.prepareStatement(query);
		stmt.setInt(1, playerID);
		stmt.executeUpdate();
	}
	
	
	public void updatePlayer(int playerID, String playerName) throws SQLException {			//Helper method to delete player in database
		String query = "UPDATE PLAYER SET PlayerName = '?' WHERE PlayerID = ?";
		PreparedStatement stmt = connection.prepareStatement(query);
		stmt.setString(1, playerName);
		stmt.setInt(2, playerID);
		stmt.executeUpdate();
	}
	
	

	public boolean isConnectionOpen() {
		try {
			return !connection.isClosed();
		} catch(SQLException e) {
			return false;
		}
	}

	
}
