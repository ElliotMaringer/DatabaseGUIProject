package Database;

public class Game {
    private int gameID;
    private int week;
    private String location;

    public Game(int gameID, int week, String location) {
        this.gameID = gameID;
        this.week = week;
        this.location = location;
    }

    public int getGameID() {
        return gameID;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public String getLoctation() {
        return location;
    }
    
    public void setLocation(String location) {
         this.location = location;
    }

    public String toString() {
        return "Game [gameID =" + gameID + ", week=" + week +
         ", location=" + location + "]";
    }
}
