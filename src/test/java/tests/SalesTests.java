package tests;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import pages.SalesPage;
import utilities.Driver;
import utilities.SeleniumUtils;

public class SalesTests {

    Faker faker = new Faker();
    public String email = faker.internet().emailAddress();

    public String fName = faker.commerce().productName();
    public String fPrice = faker.number().digit();
    static WebDriver driver =  Driver.getDriver();
    LoginPage loginPage = new LoginPage();
    SalesPage salesPage = new SalesPage();
    @BeforeEach
    public void before() {
        driver.get("https://cashwise.us/main");
        loginPage.login("marinarudenko91@gmail.com", "password123");
    }
    @Test
    public void addProduct() throws InterruptedException {
        salesPage.addProductOrService(fName, fPrice);

        boolean found = false;
        for(int i = 0; i < salesPage.productName.size(); i++){
            if(salesPage.productName.get(i).getText().equals(fName)){
                found = true;
                break;
            }
        }
        Assertions.assertTrue(found);

    }
    @Test
    public void testDeleteProduct() {
        salesPage.deleteProduct();
        boolean found = true;
        for(int i = 0; i < salesPage.productName.size(); i++){
            if(salesPage.productName.get(i).getText().equals(fName)){
                found = false;
                break;
            }
        }
        Assertions.assertTrue(found);
    }

    @Test
    public void testEditProduct() throws InterruptedException {

        salesPage.editProduct("686");
        boolean found = false;
        for(int i = 0; i < salesPage.productName.size(); i++){
            if(salesPage.productPrice.get(i).getText().contains("686")){
                found = true;
                break;
            }
        }
        Assertions.assertTrue(found);
    }

    @AfterEach
    public void after() throws InterruptedException {
        Thread.sleep(3000);
        salesPage.logOut();
    }
    @AfterAll
    public static void afterAll() {
        driver.quit();
    }
}
