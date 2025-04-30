package Database;

public class TeamStat {
    private int teamStatID;
    private int teamID;
    private int totalPassYards;
    private int totalPassTDs;
    private int totalRushYards;
    private int totalRushTDs;
    private int losses;
    private int wins;
    private int offensiveRating;
    private int defensiveRating;

    public TeamStat(int teamStatID, int teamID, int totalPassYards, int totalPassTDs, int totalRushYards, 
                    int totalRushTDs, int losses, int wins, int offensiveRating, int defensiveRating) {
        this.teamStatID = teamStatID;
        this.teamID = teamID;
        this.totalPassYards = totalPassYards;
        this.totalPassTDs = totalPassTDs;
        this.totalRushYards = totalRushYards;
        this.totalRushTDs = totalRushTDs;
        this.losses = losses;
        this.wins = wins;
        this.offensiveRating = offensiveRating;
        this.defensiveRating = defensiveRating;
    }

    public int getTeamStatID() {
        return teamStatID;
    }

    public int getTeamID() {
        return teamID;
    }

    public int getTotalPassYards() {
        return totalPassYards;
    }

    public int getTotalPassTDs() {
        return totalPassTDs;
    }

    public int getTotalRushYards() {
        return totalRushYards;
    }

    public int getTotalRushTDs() {
        return totalRushTDs;
    }

    public int getLosses() {
        return losses;
    }

    public int getWins() {
        return wins;
    }

    public int getOffensiveRating() {
        return offensiveRating;
    }

    public int getDefensiveRating() {
        return defensiveRating;
    }

    @Override
    public String toString() {
        return "TeamStat [teamStatID=" + teamStatID + ", teamID=" + teamID + 
               ", totalPassYards=" + totalPassYards + ", totalPassTDs=" + totalPassTDs + 
               ", totalRushYards=" + totalRushYards + ", totalRushTDs=" + totalRushTDs + 
               ", losses=" + losses + ", wins=" + wins + 
               ", offensiveRating=" + offensiveRating + ", defensiveRating=" + defensiveRating + "]";
    }
}

