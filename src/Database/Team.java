package Database;
public class Team {
    private int teamID;
    private String teamName;
    private String location;

    public Team(int teamID, String teamName, String location) {
        super();
        this.teamID = teamID;
        this.teamName = teamName;
        this.location = location;
    }

    public int getTeamID() {
        return teamID;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String toString() {
        return "[teamID=" + teamID + ", teamName=" + teamName + 
        ", location=" + location + "]";
    }
}
