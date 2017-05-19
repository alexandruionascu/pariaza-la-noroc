package BetRadar.Controllers;

import BetRadar.Models.Team;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by faraonul on 5/19/17.
 */
public class BetRadar {
    private static BetRadar instance = null;
    private BetRadar() {}

    private final int DEFAULT_TIMEOUT = 10;
    private final String LEAGUE_TABLE_CLASS = "normaltable";
    private final String PREMIER_LEAGUE_URL = "https://stats.betradar.com/s4/?clientid=1391#2_1,3_1,22_1,5_32887,9_summary";
    private final String LEAGUE_TABLE_SELECTOR = ".leaguetable table a";
    private final String HREF = "href";
    private final int ID_INDEX = 3;

    public static BetRadar getInstance() {
        if(instance == null) {
            instance = new BetRadar();
        }
        return instance;
    }

    private Team[] getTeams(String url) {
        WebDriver driver = new ChromeDriver();
        driver.get(url);
        WebDriverWait wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(LEAGUE_TABLE_CLASS)));
        List<WebElement> elements = driver.findElements(By.cssSelector(LEAGUE_TABLE_SELECTOR));
        return elements.stream().map(e -> new Team(e.getText(),
                e.getAttribute(HREF).split("'")[ID_INDEX])).toArray(Team[]::new);

        /*for(WebElement element : elements) {
            String teamID = element.getAttribute(HREF).split("'")[ID_INDEX];
            System.out.println(teamID);
            System.out.println(element.getText());
        }*/
    }

    public Team[] getPremierLeagueTeams() {
        return getTeams(PREMIER_LEAGUE_URL);
    }




}
