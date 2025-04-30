package Database;

public class CoachedBy {
    private int coachID;
    private int teamID;

    public CoachedBy(int coachID, int teamID) {
        this.coachID = coachID;
        this.teamID = teamID;
    }

    public int getCoachID() {
        return coachID;
    }

    public int getTeamID() {
        return teamID;
    }
}
