package Database;
import java.sql.Date;

public class Coach {
    private int coachID;
    private String coachName;
    private Date birthdate;

    public Coach(int coachID, String coachName, Date birthdate) {
        this.coachID = coachID;
        this.coachName = coachName;
        this.birthdate = birthdate;
    }

    public int getCoachID() {
        return coachID;
    }

    public String getCoachName() {
        return coachName;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String toString() {
        return "Coach [coachID=" + coachID + ", coachName=" + 
        coachName + ", birthdate=" + birthdate + "]";
    }
}
