package devToPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.w3c.dom.html.HTMLInputElement;

public class DevToSinglePodcastPage {

    WebDriver driver;
    WebDriverWait wait;
    String partOfUrl = "stackpodcast";
    String play = "playing";
    String classAttribute = "class";

    @FindBy(className = "record-wrapper" )
    private WebElement playArea;

    @FindBy(className = "status-message")
    private WebElement initializing;

    public DevToSinglePodcastPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    public void playPodcast(){
        wait.until(ExpectedConditions.urlContains(partOfUrl));
        playArea.click();
        wait.until(ExpectedConditions.invisibilityOf(initializing));
    }

    public boolean checkThatIsPlaying(){
        String playAreaClassAttribute = playArea.getAttribute(classAttribute);
        return playAreaClassAttribute.contains(play);
    }
}
