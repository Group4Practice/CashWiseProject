package pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.awt.*;
import java.util.List;

public class SalesPage {
    static WebDriver driver;
    public SalesPage() {
        driver = Driver.getDriver();
        PageFactory.initElements(driver,this);
    }
    //Log Out
    @FindBy(css = "div[class='css-omke5m']")
    public WebElement accountSettings;
    @FindBy(xpath = "//li[text()='Log out']")
    public WebElement logOutBtn;
    @FindBy(xpath = "//button[text()='Log out']")
    public WebElement logOutConfirm;
    //Path to the Product and service page
    @FindBy(xpath = "//li[text()='Sales']")
    public WebElement salesTab;
    @FindBy(css="a[href='/dashboard/sales/products-and-services']")
    public WebElement productAndServicesTab;
    //add new product or service
    @FindBy(xpath = "//button[text()='Add products or service']")
    public WebElement addProductBtn;
    @FindBy(id="product_title_input_text")
    public WebElement name;
    @FindBy(id="product_price_input_text")
    public WebElement price;
    @FindBy(id="mui-component-select-service_type_id")
    public WebElement selectServiceType;
    @FindBy(xpath="//li[text()='Product']")
    public WebElement selectProduct;
    @FindBy(id="mui-component-select-category_id")
    public WebElement selectCategory;
    @FindBy(xpath = "//li[text()='Glassware']")
    public  WebElement selectGlassware;
    @FindBy(id="product_description_input_text")
    public WebElement description;
    @FindBy(xpath="//button[text()='Save']")
    public WebElement saveBtn;
    @FindBy(xpath = "//tr/td[1]")
     public List<WebElement> productName;
    @FindBy(xpath = "//tr/td[2]")
    public List<WebElement> productPrice;
    //delete product or service
    @FindBy(xpath = "//tbody/tr[1]/td[6]//div/button[2]")
    public WebElement deleteIcon;
    @FindBy(xpath = "//button[text()='Delete']")
    public WebElement deleteButton;
    //edit product or service
    @FindBy(xpath = "//tbody/tr[1]/td[6]//div/button[1]")
    public WebElement editIcon;
    public void logOut(){
        accountSettings.click();
        logOutBtn.click();
        logOutConfirm.click();
    }

    public void addProductOrService(String strName, String strPrice) throws InterruptedException {
        salesTab.click();
        productAndServicesTab.click();
        addProductBtn.click();
        name.sendKeys(strName);
        price.sendKeys(strPrice);
        selectServiceType.click();
        selectProduct.click();
        selectCategory.click();
        selectGlassware.click();
        description.sendKeys("Test description111");
        saveBtn.click();
        Thread.sleep(3000);

    }
    public void deleteProduct() {
        salesTab.click();
        productAndServicesTab.click();
        deleteIcon.click();
        deleteButton.click();
    }
    public void editProduct(String updatePrice) throws InterruptedException {
        salesTab.click();
        productAndServicesTab.click();
        editIcon.click();
        Actions actions = new Actions(driver);
        actions.click(name)
                .sendKeys(Keys.CONTROL, "a")
                .sendKeys(Keys.BACK_SPACE)
                .sendKeys("Updated Name")
                .build()
                .perform();

        actions.click(price)
                .sendKeys(Keys.CONTROL, "a")
                .sendKeys(Keys.BACK_SPACE)
                .sendKeys(updatePrice)
                .build()
                .perform();
        saveBtn.click();
        Thread.sleep(3000);
    }


}
