package pages;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.time.Duration;

public class LoginPage {
    static WebDriver driver;
public LoginPage() {
    driver = Driver.getDriver();
    PageFactory.initElements(driver,this);
}
@FindBy(xpath = "//button[text()='Sign in']")
public WebElement signInBtn;
@FindBy(id="email_input_text")
    public WebElement email;

@FindBy(id="password_input_text")
    public WebElement password;

@FindBy(xpath = "(//button[text()='Sign in'])[2]")
    public WebElement loginButton;
@FindBy(xpath = "(//div[@class='css-1l0lg74'])[5]")
public WebElement errorMessage;

public void login(String userName, String pwd){
    signInBtn.click();
    email.sendKeys(userName);
    password.sendKeys(pwd);
    loginButton.click();
}

}
