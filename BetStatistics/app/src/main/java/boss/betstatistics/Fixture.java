package boss.betstatistics;

public class Fixture {
    private String date;
    private String homeTeam;
    private String awayTeam;
    private int homeScored;
    private int awayScored;
    private int round;

    public Fixture() {}

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        homeTeam = homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    public int getHomeScored() {
        return homeScored;
    }

    public void setHomeScored(int homeScored) {
        this.homeScored = homeScored;
    }

    public int getAwayScored() {
        return awayScored;
    }

    public void setAwayScored(int awayScored) {
        this.awayScored = awayScored;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }
}
