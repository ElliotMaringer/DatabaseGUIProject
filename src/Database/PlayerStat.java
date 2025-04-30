package Database;

public class PlayerStat {
    private int playerStatID;
    private int playerID;
    private int rushYards;
    private int rushTDs;
    private int passYards;
    private int passTDs;
    private int recYards;
    private int recTDs;
    private int interceptions;

    public PlayerStat(int playerStatID, int playerID, int rushYards, int rushTDs, int passYards, 
                      int passTDs, int recYards, int recTDs, int interceptions) {
        this.playerStatID = playerStatID;
        this.playerID = playerID;
        this.rushYards = rushYards;
        this.rushTDs = rushTDs;
        this.passYards = passYards;
        this.passTDs = passTDs;
        this.recYards = recYards;
        this.recTDs = recTDs;
        this.interceptions = interceptions;
    }

    public int getPlayerStatID() {
        return playerStatID;
    }

    public int getPlayerID() {
        return playerID;
    }

    public int getRushYards() {
        return rushYards;
    }

    public int getRushTDs() {
        return rushTDs;
    }

    public int getPassYards() {
        return passYards;
    }

    public int getPassTDs() {
        return passTDs;
    }

    public int getRecYards() {
        return recYards;
    }

    public int getRecTDs() {
        return recTDs;
    }

    public int getInterceptions() {
        return interceptions;
    }

    @Override
    public String toString() {
        return "PlayerStat [playerStatID=" + playerStatID + ", playerID=" + playerID +
               ", rushYards=" + rushYards + ", rushTDs=" + rushTDs + ", passYards=" + passYards + 
               ", passTDs=" + passTDs + ", recYards=" + recYards + ", recTDs=" + recTDs + 
               ", interceptions=" + interceptions + "]";
    }
}

