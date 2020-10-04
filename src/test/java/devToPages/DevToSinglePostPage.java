package devToPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DevToSinglePostPage {


    WebDriver driver;
    WebDriverWait wait;

    @FindBy(css = "div.crayons-article__header__meta > h1")
    public WebElement postTitle;

    public DevToSinglePostPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(this.driver, this);
    }
}
