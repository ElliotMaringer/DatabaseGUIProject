package Database;

import javax.swing.SwingUtilities;
import GUI.MainGUI; // Assuming your GUI class is in the GUI package

public class Nfl {
    public static void main(String[] args) {
        // Create the Database object to handle connections
        Database db = new Database();
        
        // Connect to the database
        db.connect();

        // Create the GUI and pass the Database connection to it
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    // Create and show the main GUI window
                    MainGUI frame = new MainGUI(db);  // Pass Database object to the GUI constructor
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        // Disconnect from the database when the application closes
        // You may want to handle this in the GUI to ensure proper disconnection
    }
}
