import FifaDB.Controllers.FifaDb;
import FifaDB.Models.Player;
import com.google.common.base.Predicate;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jaunt.Element;
import com.jaunt.Elements;
import com.jaunt.UserAgent;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


/**
 * Created by faraonul on 5/13/17.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        WebDriver driver = new ChromeDriver();
        driver.get("https://stats.betradar.com/s4/?clientid=1391#2_1,3_1,22_1,5_32887,9_teampage,6_38");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("odd")));
        List<WebElement> elements = driver.findElements(By.cssSelector("tr"));
        for(WebElement e : elements) {
            System.out.println(e.getText());
        }

    }
}
