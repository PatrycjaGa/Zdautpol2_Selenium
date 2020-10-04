package devToPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DevToWeekPage {
    WebDriver driver;
    String url = "https://dev.to/top/week";
    WebDriverWait wait;

    @FindBy(css = "h2.crayons-story__title > a" )
    public  WebElement firstPostOnWeek;

    public DevToWeekPage(WebDriver driver, WebDriverWait wait){
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    public DevToSinglePostPage selectFirstPost(){
        wait.until(ExpectedConditions.urlToBe(this.url));
        firstPostOnWeek.click();
        return new DevToSinglePostPage(this.driver, this.wait);
    }


}

