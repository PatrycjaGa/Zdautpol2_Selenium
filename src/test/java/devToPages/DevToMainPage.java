package devToPages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DevToMainPage {

    WebDriver driver;
    String url = "https://dev.to/";
    WebDriverWait wait;

    @FindBy(xpath = "//a[@href='/top/week']")
    WebElement week;

    @FindBy(id = "nav-search")
    WebElement searchBox;

    @FindBy(xpath = "//a[@href='/pod']")
    WebElement podcastButton;

    public DevToMainPage(WebDriver driver, WebDriverWait wait){
        this.driver = driver;
        this.wait = wait;
        driver.get(url);
        PageFactory.initElements(driver, this);
    }

    public DevToWeekPage goToWeekPage(){
        week.click();
        wait.until(ExpectedConditions.urlToBe(week.getAttribute("href")));
        return new DevToWeekPage(driver, wait);
    }

    public DevToSearchResultsPage search(String searchText){
        searchBox.sendKeys(searchText);
        searchBox.sendKeys(Keys.ENTER);
        return new DevToSearchResultsPage(this.driver, this.wait);
    }

    public DevToPodcastsPage goToPodcasts(){
        podcastButton.click();
        return new DevToPodcastsPage(this.driver, this.wait);

    }

}
