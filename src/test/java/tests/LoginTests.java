package tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import utilities.Driver;

public class LoginTests {
    static WebDriver driver;
    LoginPage loginPage;
@BeforeEach
public void before() {
    driver = Driver.getDriver();
    driver.get("https://cashwise.us/main");
    loginPage = new LoginPage();
    }
    @Test
    public void testSuccessfulLogin() {
        loginPage.login("marinarudenko91@gmail.com", "password123");
        String homePage = "https://cashwise.us/main?showLogin=true";
        Assertions.assertEquals(homePage,driver.getCurrentUrl());
    }
    @Test
    public void testEmptyEmailField() {
        loginPage.login("", "password123");
        Assertions.assertTrue(loginPage.errorMessage.isDisplayed());
    }
    @Test
    public void testEmptyPasswordField() {
        loginPage.login("marinarudenko91@gmail.com", "");
        Assertions.assertTrue(loginPage.errorMessage.isDisplayed());
    }
    @AfterAll
    public static void afterAllMethod(){
    driver.quit();
    }
}
