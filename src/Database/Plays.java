package Database;

public class Plays {
    private int teamID;
    private int gameID;
    private String result;

    public Plays(int teamID, int gameID, String result) {
        this.teamID = teamID;
        this.gameID = gameID;
        this.result = result;
    }

    public int getTeamID() {
        return teamID;
    }

    public int getGameID() {
        return gameID;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
