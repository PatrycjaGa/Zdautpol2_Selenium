package devToPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class DevToSearchResultsPage {

    WebDriver driver;
    WebDriverWait wait;
    public String url = "https://dev.to/search?q=";

    @FindBy(css = "h2.crayons-story__title > a")
    List<WebElement> postTilesList;

    public DevToSearchResultsPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    public ArrayList<String> getTopThreePostTiles(){
        wait.until(ExpectedConditions.visibilityOfAllElements(postTilesList));
        ArrayList<String> ar = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            ar.add(postTilesList.get(i).getText());
        }

        return ar;
    }
}
