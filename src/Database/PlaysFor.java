package Database;

public class PlaysFor {
    private int teamID;
    private int playerID;

    public PlaysFor(int teamID, int playerID) {
        this.teamID = teamID;
        this.playerID = playerID;
    }
    
    public int getTeamID() {
        return teamID;
    }

    public int getPlayerID() {
        return playerID;
    }

    public String toString() {
        return "PlaysFor [teamID=" + teamID + ", playerID=" + playerID + "]";
    }
}
