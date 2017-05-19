import BetRadar.Controllers.BetRadar;
import BetRadar.Models.Team;


/**
 * Created by faraonul on 5/13/17.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        /*WebDriver driver = new ChromeDriver();
        driver.get("https://stats.betradar.com/s4/?clientid=1391#2_1,3_1,22_1,5_32887,9_teampage,6_38");
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("couch_lastx")));
        WebElement downArrow = driver.findElement(By.cssSelector(".couch_lastx tfoot"));
        WebElement slickSelect = driver.findElement(By.className("slickselect"));
        System.out.println(slickSelect.getText());
        int previousHash = 0;
        boolean scrolledBottom = false;
        while(!scrolledBottom) {
            int currentHash = 0;
            //get the elements
            List<WebElement> elements = driver.findElements(By.cssSelector(".couch_lastx tbody tr"));
            for(WebElement tableRow : elements) {
                String text = tableRow.getText().trim();
                if(text.length() > 0) {
                   //String[] matchData = text.split(" ");
                    //String date = matchData[0];
                    //String homeTeam = matchData[1];
                    //String awayTeam = matchData[3];
                   // String score = matchData[4];
                   // String fixtureNo = matchData[5];


                    System.out.println(text);
                    System.out.println("--------------");
                    currentHash += text.hashCode();
                }
            }

            for(int j = 1; j <= 5; j++) {
                downArrow.click();
            }




            if(previousHash == currentHash)
                scrolledBottom = true;

            previousHash = currentHash;

        }

        */

        for(Team t : BetRadar.getInstance().getPremierLeagueTeams()) {
            System.out.println(t.getId() + " - " + t.getName());
        }

    }
}
