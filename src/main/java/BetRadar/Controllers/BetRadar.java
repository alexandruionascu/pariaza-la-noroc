package BetRadar.Controllers;
import BetRadar.Models.Fixture;
import BetRadar.Models.FixtureBuilder;
import BetRadar.Models.Team;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by faraonul on 5/19/17.
 */
public class BetRadar {


    private final int DEFAULT_TIMEOUT = 15;
    private final String LEAGUE_TABLE_CLASS = "normaltable";
    private final String PREMIER_LEAGUE_URL = "https://stats.betradar.com/s4/?clientid=1391#2_1,3_1,22_1,5_32887,9_summary";
    private final String PREMIER_LEAGUE_PREFIX = "https://stats.betradar.com/s4/?clientid=1391#2_1,3_1,22_1,5_32887,9_teampage,6_";
    private final String LA_LIGA_URL = "https://stats.betradar.com/s4/?clientid=1391#2_1,3_32,22_1,5_33529,9_summary";
    private final String LA_LIGA_PREFIX = "https://stats.betradar.com/s4/?clientid=1391#2_1,3_32,22_1,5_33529,9_teampage,6_";
    private final String BUNDESLIGA_URL = "https://stats.betradar.com/s4/?clientid=1391#2_1,3_30,22_1,5_33225,9_summary";
    private final String BUNGESLIGA_PREFIX = "https://stats.betradar.com/s4/?clientid=1391#2_1,3_30,22_1,5_33225,9_teampage,6_";
    private final String SERIE_A_URL = "https://stats.betradar.com/s4/?clientid=1391#2_1,3_31,22_1,5_33771,9_summary";
    private final String SERIA_A_PREFIX = "https://stats.betradar.com/s4/?clientid=1391#2_1,3_31,22_1,5_33771,9_teampage,6_";
    private final String LEAGUE_TABLE_SELECTOR = ".leaguetable table a";
    private final String HREF = "href";
    private final int ID_INDEX = 3;
    private final String LAST_X_CLASS = "couch_lastx";
    private final String LAST_X_TR = ".couch_lastx tbody tr";
    private final String ARROW_DOWN_SELECTOR = ".couch_lastx tfoot";

    private static final WebDriver driver = new ChromeDriver();

    private static BetRadar instance = null;
    private BetRadar() {}

    public static BetRadar getInstance() {
        if(instance == null) {
            instance = new BetRadar();
        }
        return instance;
    }


    private Team[] getTeams(String url) {
        try {
            driver.get(url);
            WebDriverWait wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(LEAGUE_TABLE_CLASS)));
            List<WebElement> elements = driver.findElements(By.cssSelector(LEAGUE_TABLE_SELECTOR));
            return elements.stream().map(e -> new Team(e.getText(),
                    e.getAttribute(HREF).split("'")[ID_INDEX])).toArray(Team[]::new);
        } catch (Exception ex) {
            return getTeams(url);
        }
    }

    public Team[] getPremierLeagueTeams() {
        return getTeams(PREMIER_LEAGUE_URL);
    }

    public Team[] getLaLigaTeams() {
        return getTeams(LA_LIGA_URL);
    }

    public Team[] getBundesligaTeams() {
        return getTeams(BUNDESLIGA_URL);
    }

    public Team[] getSerieATeams() {
        return getTeams(SERIE_A_URL);
    }

    public List<Fixture> getPremierLeagueTeamFixtures(String teamID) {
        return getFixtures(PREMIER_LEAGUE_PREFIX, teamID);
    }

    public List<Fixture> getLaLigaTeamFixtures(String teamID) {
        return getFixtures(LA_LIGA_PREFIX, teamID);
    }

    public List<Fixture> getBundesligaFixtures(String teamID) {
        return getFixtures(BUNGESLIGA_PREFIX, teamID);
    }

    public List<Fixture> getSerieAFixtures(String teamID) {
        return getFixtures(SERIA_A_PREFIX, teamID);
    }

    private List<Fixture> getFixtures(String prefix, String teamID) {
        driver.get(prefix + teamID);
        WebDriverWait wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(LAST_X_CLASS)));
        WebElement downArrow = driver.findElement(By.cssSelector(ARROW_DOWN_SELECTOR));
        int previousHash = 0;
        boolean scrolledBottom = false;
        List<Fixture> fixtures = new ArrayList<Fixture>();
        while(!scrolledBottom) {
            int currentHash = 0;
            //get the elements
            List<WebElement> elements = driver.findElements(By.cssSelector(LAST_X_TR));
            for(WebElement tableRow : elements) {
                String text = tableRow.getText().trim();
                if(text.length() > 0) {
                    String[] matchData = text.split("\n");
                    String date = matchData[0];
                    String[] teams = matchData[1].split("-");
                    String homeTeam = teams[0].trim();
                    String awayTeam = teams[1].trim();
                    String[] score = matchData[2].split(" ");
                    int scoredHome = Integer.valueOf(score[0].split(":")[0]);
                    int scoredAway = Integer.valueOf(score[0].split(":")[1]);
                    int round = Integer.valueOf(score[1]);
                    fixtures.add(new FixtureBuilder()
                            .date(date)
                            .homeTeam(homeTeam)
                            .awayTeam(awayTeam)
                            .homeScored(scoredHome)
                            .awayScored(scoredAway)
                            .round(round)
                            .build());

                    currentHash += text.hashCode();
                }
            }

            for(int j = 1; j <= 5; j++) {
                downArrow.click();
            }




            if(previousHash == currentHash) {
                scrolledBottom = true;
            }


            previousHash = currentHash;

        }

        return fixtures;
    }



}
