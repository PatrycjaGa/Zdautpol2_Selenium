import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class BaseTest {

    WebDriver driver;
    WebDriverWait wait;

    public void highlightElement(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style', 'background: green; border: 3px solid blue;');", element);
    }

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Ja\\Desktop\\Patrycja\\Dokumenty\\KURS\\chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        String devToUrl = "https://dev.to/";
        driver.get(devToUrl);
        wait = new WebDriverWait(driver, 20);
    }

    @Test
    public void firstTest() {

        WebElement week = driver.findElement(By.xpath("//a[@href='/top/week']"));
//        highlightElement(driver, week);
        week.click();
        wait.until(ExpectedConditions.urlToBe("https://dev.to/top/week"));
        WebElement firstPostOnWeek = driver.findElement(By.cssSelector("h2.crayons-story__title > a"));
        String firstPostOnWeekText = firstPostOnWeek.getText();
        String firstPostLink = firstPostOnWeek.getAttribute("href");
        firstPostOnWeek.click();
        wait.until(ExpectedConditions.urlToBe(firstPostLink));
        WebElement postTitle = driver.findElement(By.cssSelector("div.crayons-article__header__meta > h1"));
        String postTitleText = postTitle.getText();

        String postUrl = driver.getCurrentUrl();
        assertEquals("Urls aren't the same", postUrl, firstPostLink);
        assertEquals("Titles aren't the same", postTitleText, firstPostOnWeekText);

    }

    @Test
    public void searchBarTesting() {
        WebElement searchBox = driver.findElement(By.id("nav-search"));
        highlightElement(driver, searchBox);
        String searchText = "testing";
        searchBox.sendKeys(searchText);
        searchBox.sendKeys(Keys.ENTER);
        String searchUrl = "https://dev.to/search?q=";
        String searchingUrlWIthText = searchUrl + searchText;
        wait.until(ExpectedConditions.urlToBe(searchingUrlWIthText));
        ArrayList<WebElement> postTilesList = (ArrayList<WebElement>) driver.findElements(By.cssSelector("h2.crayons-story__title > a"));
//        for (int i = 0; i <3; i ++){
//            WebElement element = postTilesList.get(i);
//            String elementText = element.getText();
//            assertTrue("there;s no searching value in post titles", elementText.contains(searchText));
//        }
        WebElement element = postTilesList.get(0);
            String elementText = element.getText();
            assertTrue("there;s no searching value in post titles", elementText.contains(searchText));


    }

    @Test
    
}
