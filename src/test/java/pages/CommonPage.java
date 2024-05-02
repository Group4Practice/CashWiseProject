package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class CommonPage {
    static WebDriver driver;
    public CommonPage() {
        driver = Driver.getDriver();
        PageFactory.initElements(driver,this);
    }
    @FindBy(linkText = "PIM")
    public WebElement pimTab;

    @FindBy(linkText = "Leave")
    public WebElement leaveTab;

    @FindBy(linkText = "Dashboard")
    public WebElement dashBoardTab;

}
