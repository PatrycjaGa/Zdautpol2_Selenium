import devToPages.*;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

        wait = new WebDriverWait(driver, 20);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void selectFirstPostFromWeek() {
        DevToMainPage devToMainPage = new DevToMainPage(driver, wait);
        DevToWeekPage devToWeekPage = devToMainPage.goToWeekPage();


        String firstPostLink = devToWeekPage.firstPostOnWeek.getAttribute("href");
        String firstPostText = devToWeekPage.firstPostOnWeek.getText();

        DevToSinglePostPage devToSinglePostPage = devToWeekPage.selectFirstPost();
        wait.until(ExpectedConditions.urlToBe(firstPostLink));
        String postTitleText = devToSinglePostPage.postTitle.getText();
        String currentUrl = driver.getCurrentUrl();

        assertEquals("Urls aren't the same", currentUrl, firstPostLink);
        assertEquals("Titles aren't the same", postTitleText, firstPostText);


    }

    @Test
    public void searchBarTesting() {

        DevToMainPage devToMainPage = new DevToMainPage(driver, wait);
        String searchText = "testing";
        DevToSearchResultsPage devToSearchResultsPage = devToMainPage.search(searchText);

        String searchingUrlWithText = devToSearchResultsPage.url + searchText;
        wait.until(ExpectedConditions.urlToBe(searchingUrlWithText));

        ArrayList<String> postTilesList = devToSearchResultsPage.getTopThreePostTiles();
        int i = 0;
        while (i < 3) {
            String postTileText = postTilesList.get(i).toLowerCase();
            assertTrue("there;s no searching value in post titles", postTileText.contains(searchText));
            i++;
        }
    }

    @Test
    public void findJavaInNavBar() {
        WebElement java = driver.findElement(By.cssSelector("div#default-sidebar-element-java > a"));
        highlightElement(driver, java);
        java.click();
    }

    @Test
    public void findPodcast() {
        DevToMainPage devToMainPage = new DevToMainPage(driver, wait);
        DevToPodcastsPage devToPodcastsPage = devToMainPage.goToPodcasts();
        DevToSinglePodcastPage devToSinglePodcastPage = devToPodcastsPage.selectFourthPodcast();
        devToSinglePodcastPage.playPodcast();
        boolean isPlaying = devToSinglePodcastPage.checkThatIsPlaying();
        assertTrue("Podcast isn't playing", isPlaying);

    }


}
