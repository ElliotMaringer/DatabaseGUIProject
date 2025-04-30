package Database;


public class GameStat {
    private int gameStatID;
    private int gameID;
    private int homeRushYards;
    private int homeRushTDs;
    private int homePassYards;
    private int homePassTDs;
    private int homeScore;
    private int awayRushYards;
    private int awayRushTDs;
    private int awayPassYards;
    private int awayPassTDs;
    private int awayScore;

    public GameStat(int gameStatID, int gameID, int homeRushYards, int homeRushTDs, int homePassYards, 
                    int homePassTDs, int homeScore, int awayRushYards, int awayRushTDs, 
                    int awayPassYards, int awayPassTDs, int awayScore) {
        this.gameStatID = gameStatID;
        this.gameID = gameID;
        this.homeRushYards = homeRushYards;
        this.homeRushTDs = homeRushTDs;
        this.homePassYards = homePassYards;
        this.homePassTDs = homePassTDs;
        this.homeScore = homeScore;
        this.awayRushYards = awayRushYards;
        this.awayRushTDs = awayRushTDs;
        this.awayPassYards = awayPassYards;
        this.awayPassTDs = awayPassTDs;
        this.awayScore = awayScore;
    }

    public int getGameStatID() {
        return gameStatID;
    }

    public int getGameID() {
        return gameID;
    }

    public int getHomeRushYards() {
        return homeRushYards;
    }

    public int getHomeRushTDs() {
        return homeRushTDs;
    }

    public int getHomePassYards() {
        return homePassYards;
    }

    public int getHomePassTDs() {
        return homePassTDs;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public int getAwayRushYards() {
        return awayRushYards;
    }

    public int getAwayRushTDs() {
        return awayRushTDs;
    }

    public int getAwayPassYards() {
        return awayPassYards;
    }

    public int getAwayPassTDs() {
        return awayPassTDs;
    }

    public int getAwayScore() {
        return awayScore;
    }

    @Override
    public String toString() {
        return "GameStat [gameStatID=" + gameStatID + ", gameID=" + gameID + 
               ", homeRushYards=" + homeRushYards + ", homeRushTDs=" + homeRushTDs + 
               ", homePassYards=" + homePassYards + ", homePassTDs=" + homePassTDs + 
               ", homeScore=" + homeScore + ", awayRushYards=" + awayRushYards + 
               ", awayRushTDs=" + awayRushTDs + ", awayPassYards=" + awayPassYards + 
               ", awayPassTDs=" + awayPassTDs + ", awayScore=" + awayScore + "]";
    }
}

