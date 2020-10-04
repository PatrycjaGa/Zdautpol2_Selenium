package devToPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DevToMainPage {

    WebDriver driver;
    String url = "https://dev.to/";

    @FindBy(xpath = "//a[@href='/top/week']")
    WebElement week;

    public DevToMainPage(WebDriver driver){
        this.driver = driver;
        driver.get(url);
        PageFactory.initElements(driver, this);

    }

    public void goToWeekPage(){
        week.click();
    }

}
