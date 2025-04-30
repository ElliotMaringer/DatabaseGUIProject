package GUI;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Database.Database;
import Database.Player;
import Database.Team;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;


public class MainGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private Database database;
	private JPanel contentPane;
	private JTable resultTable1;
	private JTable resultTable2;
	private JTable resultTable3;
	private JComboBox<String> selectTeam;
	private JComboBox<String> selectEntity;
	private JComboBox<String> selectPlayer; 
	private JComboBox<String> selectStat;//initialize all dynamic parts of window builder
	private JButton runQuery;
	private JButton runQuery2;
	private JButton runQuery3;
	private JButton resetButton;
	private JButton insertButton;
	private JButton updateButton;
	private JButton deleteButton;
	private JTextField playerNameField;
	private JTextField playerBirthdateField;
	private JTextField playerPositionField;
	private JTextField playerIdField;
	private JTextField advRush;
	private JTextField advRushTD;
	private JTextField advPass;
	private JTextField advPassTD;
	private JTextField advRec;
	private JTextField advRecTD;
	
	
	
	

	
	public MainGUI(Database database) {                       //Create window builder and initialize its elements and populate combo boxes
		this.database = database;
		initialize();
		populateCombo();
		
		runQuery.addActionListener(new ActionListener() {			
            public void actionPerformed(ActionEvent e) {
            	try {
	                // Get selected items from combo boxes
	                String selectedTeam = (String) selectTeam.getSelectedItem();
	                String selectedStuff = (String) selectEntity.getSelectedItem();
	                int teamID = getTeamIDName(selectedTeam);
	                // Generate the query using selected values
	                String query = database.makeQuery(teamID, selectedStuff);
	
	                // Execute the query and get the results
	                ResultSet results = null;
	                if (database.isConnectionOpen()) {
	                    // Execute the query and get the results
	                    results = database.runQuery(query);
	
	                    // Populate the table with results
	                    populateTable(results, resultTable1);
	                    results.close(); 
	                } else {
	                    JOptionPane.showMessageDialog(null, "Database connection is closed!");
	                }
            	} catch (SQLException ex) {
            		ex.printStackTrace();
            	}
            }
        });
		
		runQuery2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	try {
	                // Get selected items from combo boxes
	                String selectedPlayer = (String) selectPlayer.getSelectedItem();

	                int playerID = getPlayerIDName(selectedPlayer);
	                // Generate the query using selected values
	                String query = database.makeQuery2(playerID);
	
	                // Execute the query and get the results
	                ResultSet results = null;
	                if (database.isConnectionOpen()) {
	                    // Execute the query and get the results
	                    results = database.runQuery(query);
	
	                    // Populate the table with results
	                    populateTable(results, resultTable2);
	                    results.close();  // Always close ResultSet after use
	                } else {
	                    JOptionPane.showMessageDialog(null, "Database connection is closed!");
	                }
            	} catch (SQLException ex) {
            		ex.printStackTrace();
            	}
            }
        });
		
		runQuery3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	try {
	               int rushYD = Integer.parseInt(advRush.getText());
	               int rushTD = Integer.parseInt(advRushTD.getText());
	               int passYD = Integer.parseInt(advPass.getText());
	               int passTD = Integer.parseInt(advPassTD.getText());
	               int recYD = Integer.parseInt(advRec.getText());
	               int recTD = Integer.parseInt(advRecTD.getText());
	               String selectedStat = (String) selectStat.getSelectedItem();
	               
	                String query = database.makeQuery3(rushYD, rushTD, passYD, passTD, recYD, recTD, selectedStat);
	
	                // Execute the query and get the results
	                ResultSet results = null;
	                if (database.isConnectionOpen()) {
	                    // Execute the query and get the results
	                    results = database.runQuery(query);
	
	                    // Populate the table with results
	                    populateTable(results, resultTable3);
	                    results.close();  // Always close ResultSet after use
	                } else {
	                    JOptionPane.showMessageDialog(null, "Database connection is closed!");
	                }
            	} catch (SQLException ex) {
            		ex.printStackTrace();
            	}
            }
        });
		
		resetButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        resultTable1.setModel(new DefaultTableModel());
		        resultTable2.setModel(new DefaultTableModel());
		        selectTeam.setSelectedIndex(0); // Reset dropdowns
		        selectEntity.setSelectedIndex(0);
		    }
		});
		
		addWindowListener(new WindowAdapter() {					
	            public void windowClosing(WindowEvent e) {
	               database.disconnect(); 
	           }
	    });
		
		insertButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String name = playerNameField.getText();
					String birthdate = playerBirthdateField.getText();
					String position = playerPositionField.getText();
					database.insertPlayer(name, birthdate, position);
					database.insertPlayerStat(name, getPlayerIDName(name));
					JOptionPane.showMessageDialog(null, "Player Inserted");
				} catch (SQLException ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
				}
			}
		});
		
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String name = playerNameField.getText();
					int id = getPlayerIDName(name);
					database.deletePlayerStat(id);
					database.deletePlayer(name);
					JOptionPane.showMessageDialog(null, "Player Deleted");
				} catch (SQLException ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
				}
			}
		});
		
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String name = playerNameField.getText();
					int id = Integer.parseInt(playerIdField.getText());
					database.updatePlayer(id,name);
					JOptionPane.showMessageDialog(null, "Player Updated");
				} catch (SQLException ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
				}
			}
		});
	}
	
	
	
	public int getTeamIDName(String teamName) {							//Helper method for getting teamID from a teamName
		ArrayList<Team> teams = getAllTeams();
        for(Team team: teams) {
        	if(team.getTeamName().equalsIgnoreCase(teamName)) {
        		return team.getTeamID();
        	}
        }
		return 0;
	}
	
	public int getPlayerIDName(String playerName) {			//Helper method for getting PlayerID from a playerName
		ArrayList<Player> players = getAllPlayers();
        for(Player player: players) {
        	if(player.getPlayerName().equalsIgnoreCase(playerName)) {
        		return player.getPlayerID();
        	}
        }
		return 0;
	}
	
	
	public ArrayList<Team> getAllTeams() {				//Helper method for getting all teams
	    ArrayList<Team> lst = new ArrayList<>();
	    try {
	        // Reuse already established connection
	        String query = "SELECT * FROM Team";
	        ResultSet results = database.runQuery(query);

	        while (results.next()) {
	            int teamID = results.getInt("TeamID");
	            String teamName = results.getString("TeamName");
	            String location = results.getString("TeamLocation");

	            Team team = new Team(teamID, teamName, location);
	            lst.add(team);
	        }
	        results.close(); // Close ResultSet after use
	    } catch (SQLException e) {
	        System.out.println("Something went wrong!");
	        e.printStackTrace();
	    }
	    return lst;  // Return the list of teams
	}
	
	public ArrayList<Player> getAllPlayers() {			//Helper method for getting all players
	    ArrayList<Player> lst = new ArrayList<>();
	    try {
	        // Reuse already established connection
	        String query = "SELECT * FROM Player";
	        ResultSet results = database.runQuery(query);

	        while (results.next()) {
	            int playerID = results.getInt("PlayerID");
	            String playerName = results.getString("PlayerName");
	            Date birthdate = results.getDate("Birthdate");
	            String position = results.getString("Position");

	            Player player = new Player(playerID, playerName, position, birthdate);
	            lst.add(player);
	        }
	        results.close();
	    } catch (SQLException e) {
	        System.out.println("Something went wrong!");
	        e.printStackTrace();
	    }
	    return lst;
	}

	
	private void populateCombo() {				//Method to populate JcomboBoxes
		ArrayList<Team> teams = getAllTeams();
		for (Team team : teams) {
			selectTeam.addItem(team.getTeamName());
		}
		
		ArrayList<Player> players = getAllPlayers();
		for(Player player: players) {
			selectPlayer.addItem(player.getPlayerName());
		}
		
		String[] entities = {"Coach", "Game", "Player"};
	    for (String entity : entities) {
	        selectEntity.addItem(entity);
	    }	
	    
	    String[] stats = {"PlayerRushYD", "PlayerRushTD", "PlayerPassYD", "PlayerPassTD", "PlayerRecYD", "PlayerRecTD"};
	    for(String stat: stats) {
	    	selectStat.addItem(stat);
	    }
	}

	public void initialize() {									//Initialize Window
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 1000);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		selectTeam = new JComboBox<>();
		selectTeam.setBounds(39, 82, 81, 21);
		contentPane.add(selectTeam);
		
		selectEntity = new JComboBox<>();
		selectEntity.setBounds(164, 82, 81, 21);
		contentPane.add(selectEntity);
		
		resultTable1 = new JTable();
		resultTable1.setBounds(38, 120, 293, 128);
		contentPane.add(resultTable1);
		
		resultTable2 = new JTable();
		resultTable2.setBounds(10, 350, 900, 100);
		contentPane.add(resultTable2);
		
		resultTable3 = new JTable();
		resultTable3.setBounds(50, 600, 900, 300);
		contentPane.add(resultTable3);
		
		runQuery = new JButton("Run Query");
		runQuery.setBounds(303, 82, 85, 21);
		contentPane.add(runQuery);
		
		runQuery2 = new JButton("Run Query");
		runQuery2.setBounds(50, 450, 85, 21);
		contentPane.add(runQuery2);
		
		runQuery3 = new JButton("Run Query");
		runQuery3.setBounds(350, 515, 85, 21);
		contentPane.add(runQuery3);
		
		JLabel teamLabel = new JLabel("Select Team");
		teamLabel.setBounds(51, 55, 75, 21);
		contentPane.add(teamLabel);
		

		
		JLabel EntityLabel = new JLabel("Select Entity");
		EntityLabel.setBounds(175, 59, 70, 13);
		contentPane.add(EntityLabel);
		
		resetButton = new JButton("Reset");
		resetButton.setBounds(341, 252, 85, 21);
		contentPane.add(resetButton);
		
		JLabel title = new JLabel("NFL Database");
		title.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 25));
		title.setBounds(10, 10, 180, 35);
		contentPane.add(title);
		
		JLabel manageLabel = new JLabel("Manage Database");
		manageLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
	    manageLabel.setBounds(450, 15, 180, 35);
	    contentPane.add(manageLabel);
	    
	    
	    insertButton = new JButton("Insert");
	    insertButton.setBounds(500, 232, 85, 21);
	    contentPane.add(insertButton);

	    updateButton = new JButton("Update");
	    updateButton.setBounds(600, 232, 85, 21);
	    contentPane.add(updateButton);
	    
	    deleteButton = new JButton("Delete");
	    deleteButton.setBounds(700, 232, 85, 21);
	    contentPane.add(deleteButton);
	    
	    playerNameField = new JTextField();
	    playerNameField.setBounds(600, 100, 150, 20);
	    contentPane.add(playerNameField);

	    playerBirthdateField = new JTextField();
	    playerBirthdateField.setBounds(600, 130, 150, 20);
	    contentPane.add(playerBirthdateField);

	    playerPositionField = new JTextField();
	    playerPositionField.setBounds(600, 160, 150, 20);
	    contentPane.add(playerPositionField);

	    playerIdField = new JTextField(); // For Update/Delete
	    playerIdField.setBounds(600, 190, 150, 20);
	    contentPane.add(playerIdField);
	    
	    JLabel helpLabel = new JLabel("Fill in the boxes below with content of the desired format and select an action");
	    helpLabel.setBounds(450,50,600,20);
	    contentPane.add(helpLabel);
	    

	    JLabel nameLabel = new JLabel("Player Name:");
	    nameLabel.setBounds(450, 100, 100, 20);
	    contentPane.add(nameLabel);

	    JLabel birthdateLabel = new JLabel("Birthdate (YYYY-MM-DD):");
	    birthdateLabel.setBounds(450, 130, 150, 20);
	    contentPane.add(birthdateLabel);

	    JLabel positionInsertLabel = new JLabel("Position:");
	    positionInsertLabel.setBounds(450, 160, 100, 20);
	    contentPane.add(positionInsertLabel);

	    JLabel idLabel = new JLabel("Player ID:");
	    idLabel.setBounds(450, 190, 100, 20);
	    contentPane.add(idLabel);
	    
	    selectPlayer = new JComboBox<>();
		selectPlayer.setBounds(50, 290, 100, 30);
		contentPane.add(selectPlayer);
		
		JLabel PlayerLabel = new JLabel("Select Player");
		PlayerLabel.setBounds(50, 270, 100, 20);
		contentPane.add(PlayerLabel);
		
		JLabel statsLabel = new JLabel("Player Stats");
		statsLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
	    statsLabel.setBounds(200, 290, 180, 35);
	    contentPane.add(statsLabel);
	    
	    JLabel playerNameLabel = new JLabel("Name");
	    playerNameLabel.setBounds(10, 330, 100, 20);
	    contentPane.add(playerNameLabel);
	    
	    JLabel playerIDLabel = new JLabel("ID");
	    playerIDLabel.setBounds(110, 330, 100, 20);
	    contentPane.add(playerIDLabel);
	    
	    JLabel playerRushYD = new JLabel("RushYD");
	    playerRushYD.setBounds(210, 330, 100, 20);
	    contentPane.add(playerRushYD);
	    
	    JLabel playerRushTD = new JLabel("RushTD");
	    playerRushTD.setBounds(310, 330, 100, 20);
	    contentPane.add(playerRushTD);
	    
	    JLabel playerPassYD = new JLabel("PassYD");
	    playerPassYD.setBounds(410, 330, 100, 20);
	    contentPane.add(playerPassYD);
	    
	    JLabel playerPassTD = new JLabel("PassTD");
	    playerPassTD.setBounds(510, 330, 100, 20);
	    contentPane.add(playerPassTD);
	    
	    JLabel playerRecYD = new JLabel("RecYD");
	    playerRecYD.setBounds(610, 330, 100, 20);
	    contentPane.add(playerRecYD);
	    
	    JLabel playerRecTD = new JLabel("RecTD");
	    playerRecTD.setBounds(710, 330, 100, 20);
	    contentPane.add(playerRecTD);
	    
	    JLabel interceptions = new JLabel("Interceptions");
	    interceptions.setBounds(810, 330, 100, 20);
	    contentPane.add(interceptions);
	    
	    JLabel advancedLabel = new JLabel("Advanced Player Queries");
	    advancedLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
	    advancedLabel.setBounds(50, 500, 250, 35);
	    contentPane.add(advancedLabel);
	    
	    JLabel advRushLabel = new JLabel("RushYD");
	    advRushLabel.setBounds(175, 550, 100, 20);
	    contentPane.add(advRushLabel);
	    
	    advRush = new JTextField(); 
	    advRush.setBounds(175, 575, 75, 20);
	    contentPane.add(advRush);
	    
	    JLabel advRushTDLabel = new JLabel("RushTD");
	    advRushTDLabel.setBounds(300, 550, 100, 20);
	    contentPane.add(advRushTDLabel);
	    
	    advRushTD = new JTextField(); 
	    advRushTD.setBounds(300, 575, 75, 20);
	    contentPane.add(advRushTD);
	    
	    JLabel advPassLabel = new JLabel("PassYD");
	    advPassLabel.setBounds(425, 550, 100, 20);
	    contentPane.add(advPassLabel);
	    
	    advPass = new JTextField(); 
	    advPass.setBounds(425, 575, 75, 20);
	    contentPane.add(advPass);
	    
	    JLabel advPassTDLabel = new JLabel("PassTD");
	    advPassTDLabel.setBounds(550, 550, 100, 20);
	    contentPane.add(advPassTDLabel);
	    
	    advPassTD = new JTextField(); 
	    advPassTD.setBounds(550, 575, 75, 20);
	    contentPane.add(advPassTD);
	    
	    JLabel advRecLabel = new JLabel("RecYD");
	    advRecLabel.setBounds(675, 550, 100, 20);
	    contentPane.add(advRecLabel);
	    
	    advRec = new JTextField(); 
	    advRec.setBounds(675, 575, 75, 20);
	    contentPane.add(advRec);
	    
	    JLabel advRecTDLabel = new JLabel("RecTD");
	    advRecTDLabel.setBounds(800, 550, 100, 20);
	    contentPane.add(advRecTDLabel);
	    
	    advRecTD = new JTextField(); 
	    advRecTD.setBounds(800, 575, 75, 20);
	    contentPane.add(advRecTD);
	    
	    selectStat = new JComboBox<>();
		selectStat.setBounds(450, 515, 100, 30);
		contentPane.add(selectStat);
		
		JLabel selectStatLabel = new JLabel("Order By");
		selectStatLabel.setBounds(450, 485, 100, 20);
	    contentPane.add(selectStatLabel);
	}
	
	private void populateTable(ResultSet results, JTable table) {			//Populate result tables
        try {
            // Get metadata to dynamically create column names
            ResultSetMetaData metaData = results.getMetaData();
            int columnCount = metaData.getColumnCount();
            
            // Create column headers
            Vector<String> columnNames = new Vector<>();
            for (int i = 1; i <= columnCount; i++) {
                columnNames.add(metaData.getColumnName(i));
            }

            // Create rows of data
            Vector<Vector<Object>> data = new Vector<>();
            while (results.next()) {
                Vector<Object> row = new Vector<>();
                for (int i = 1; i <= columnCount; i++) {
                    row.add(results.getObject(i));
                }
                data.add(row);
            }

            // Update the JTable
            DefaultTableModel model = new DefaultTableModel(data, columnNames);
            table.setModel(model);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
