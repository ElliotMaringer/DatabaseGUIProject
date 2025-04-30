package Database;
import java.sql.Date;

public class Player {
    private int playerID;
    private String playerName;
    private String position;
    private Date birthdate;

    public Player(int playerID, String playerName, String position, Date birthdate) {
        this.playerID = playerID;
        this.playerName = playerName;
        this.position = position;
        this.birthdate = birthdate;
    }

    public int getPlayerID() {
        return playerID;
    }

    public String getPlayerName() {
         return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public String toString() {
        return "Player [playerID=" + playerID + ", playerName =" + playerName + ", position= " + position + ", birthdate=" + birthdate + "]";
    }

    
}
