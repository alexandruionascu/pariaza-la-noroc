package BetRadar.Models;

/**
 * Created by faraonul on 5/19/17.
 */
public class FixtureBuilder {
    private String date;
    private String homeTeam;
    private String awayTeam;
    private int homeScored;
    private int awayScored;
    private int round;
    private String location;

    public FixtureBuilder date(String date) {
        this.date = date;
        return this;
    }

    public FixtureBuilder homeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
        return this;
    }

    public FixtureBuilder location(String location) {
        this.location = location;
        return this;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

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
        this.homeTeam = homeTeam;
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

    public FixtureBuilder awayTeam(String awayTeam) {

        this.awayTeam = awayTeam;
        return this;
    }

    public FixtureBuilder homeScored(int homeScored) {
        this.homeScored = homeScored;
        return this;
    }

    public FixtureBuilder awayScored(int awayScored) {
        this.awayScored = awayScored;
        return this;
    }

    public FixtureBuilder round(int round) {
        this.round = round;
        return this;
    }

    public Fixture build() {
        return new Fixture(this);
    }

}
