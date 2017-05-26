package BetRadar.Controllers;
import BetRadar.Models.Fixture;
import BetRadar.Models.FixtureBuilder;
import BetRadar.Models.Team;
import org.apache.commons.lang3.ArrayUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;


/**
 * Created by faraonul on 5/19/17.
 */
public class BetRadar {


    private final static int DEFAULT_TIMEOUT = 15;
    private final static String LEAGUE_TABLE_CLASS = "normaltable";
    private final static String PREMIER_LEAGUE_URL = "https://stats.betradar.com/s4/?clientid=1391#2_1,3_1,22_1,5_32887,9_summary";
    private final static String PREMIER_LEAGUE_PREFIX = "https://stats.betradar.com/s4/?clientid=1391#2_1,3_1,22_1,5_32887,9_teampage,6_";
    private final static String LA_LIGA_URL = "https://stats.betradar.com/s4/?clientid=1391#2_1,3_32,22_1,5_33529,9_summary";
    private final static String LA_LIGA_PREFIX = "https://stats.betradar.com/s4/?clientid=1391#2_1,3_32,22_1,5_33529,9_teampage,6_";
    private final static String BUNDESLIGA_URL = "https://stats.betradar.com/s4/?clientid=1391#2_1,3_30,22_1,5_33225,9_summary";
    private final static String BUNGESLIGA_PREFIX = "https://stats.betradar.com/s4/?clientid=1391#2_1,3_30,22_1,5_33225,9_teampage,6_";
    private final static String SERIE_A_URL = "https://stats.betradar.com/s4/?clientid=1391#2_1,3_31,22_1,5_33771,9_summary";
    private final static String SERIA_A_PREFIX = "https://stats.betradar.com/s4/?clientid=1391#2_1,3_31,22_1,5_33771,9_teampage,6_";
    private final static String LEAGUE_TABLE_SELECTOR = ".leaguetable table a";
    private final static String HREF = "href";
    private final static String SRC = "src";
    private final static String ANCHOR = "a";
    private final static int ID_INDEX = 3;
    private final static String LAST_X_CLASS = "couch_lastx";
    private final static String LAST_X_TR = ".couch_lastx tbody tr";
    private final static String ARROW_DOWN_SELECTOR = ".couch_lastx tfoot";
    private final static String[] LEAGUE_URLS = {PREMIER_LEAGUE_URL, LA_LIGA_URL, BUNDESLIGA_URL, SERIE_A_URL};
    private final static String[] LEAGUE_PREFIXES = {PREMIER_LEAGUE_PREFIX, LA_LIGA_PREFIX, BUNGESLIGA_PREFIX, SERIA_A_PREFIX};
    private final static String LOGO_SELECTOR = ".teampage_logo img";

    private static final WebDriver driver = new ChromeDriver();
    private static final JavascriptExecutor executor = (JavascriptExecutor) driver;

    private static BetRadar instance = null;
    private BetRadar() {}
    private static Team[] allTeams = null;

    public static BetRadar getInstance() {
        if(instance == null) {
            instance = new BetRadar();
            allTeams = getAllTeams();
        }
        return instance;
    }

    private void nagivate(String location) throws  InterruptedException {
        executor.executeScript(location);
        Thread.sleep(3000);
    }


    private static Team[] getTeams(String url) {
        try {
            driver.get(url);
            WebDriverWait wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(LEAGUE_TABLE_CLASS)));
            List<WebElement> elements = driver.findElements(By.cssSelector(LEAGUE_TABLE_SELECTOR));
            return elements.stream().map(e -> new Team(e.getText(),
                    e.getAttribute(HREF))).toArray(Team[]::new);
        } catch (Exception ex) {
            return getTeams(url);
        }
    }

    public static Team[] getAllTeams() {
        Team[] teams = {};
        try {
            for(String leagueUrl : LEAGUE_URLS) {
                teams = (Team[]) ArrayUtils.addAll(teams, getTeams(leagueUrl));
            }
        } catch (Exception ex) {
            return  getAllTeams();
        }

        return teams;
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

    public List<Fixture> getFixtures(Team team) throws Exception {
        nagivate(team.getLocation());
        String imageUrl = driver.findElement(By.cssSelector(LOGO_SELECTOR)).getAttribute(SRC);
        team.setImageUrl(imageUrl);
        return scrapFixtures();
    }

    private List<Fixture> scrapFixtures() {
        WebDriverWait wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(LAST_X_CLASS)));
        WebElement downArrow = driver.findElement(By.cssSelector(ARROW_DOWN_SELECTOR));
        int previousHash = 0;
        HashSet<Integer> rounds = new HashSet<Integer>();
        boolean scrolledBottom = false;
        List<Fixture> fixtures = new ArrayList<Fixture>();
        List<WebElement> anchors = driver.findElements(By.cssSelector(LAST_X_TR + " " + ANCHOR));
        List<String> locations = new ArrayList<String>();
        for(WebElement anchor : anchors) {
            locations.add(anchor.getAttribute(HREF));
        }
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
                    if(!rounds.contains(round)) {
                        fixtures.add(new FixtureBuilder()
                                .date(date)
                                .homeTeam(homeTeam)
                                .awayTeam(awayTeam)
                                .homeScored(scoredHome)
                                .awayScored(scoredAway)
                                .round(round)
                                .build());
                    }


                    currentHash += text.hashCode();
                    rounds.add(round);
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


        for (int i = 0; i < fixtures.size(); i++) {
            fixtures.get(i).setLocation(locations.get(i));
        }

        for(int i = 0; i < fixtures.size(); i++) {
            if(fixtures.get(i).getHomeTeam().endsWith("...")) {
                String prefix = fixtures.get(i).getHomeTeam()
                        .substring(0, fixtures.get(i).getHomeTeam().length() - 3);
                fixtures.get(i).setHomeTeam(Arrays.stream(allTeams)
                        .filter(f -> f.getName().startsWith(prefix))
                        .findFirst().get().getName());
                System.out.println("FOUND" + fixtures.get(i).getHomeTeam());
            }

            if(fixtures.get(i).getAwayTeam().endsWith("...")) {
                String prefix = fixtures.get(i).getAwayTeam()
                        .substring(0, fixtures.get(i).getAwayTeam().length() - 3);
                fixtures.get(i).setAwayTeam(Arrays.stream(allTeams)
                        .filter(f -> f.getName().startsWith(prefix))
                        .findFirst().get().getName());
            }
        }

        return fixtures;
    }

    private List<Fixture> getFixtures(String prefix, String teamID) {
        driver.get(prefix + teamID);
        return scrapFixtures();
    }


}
