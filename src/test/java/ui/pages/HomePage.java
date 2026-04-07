package ui.pages;

import configs.TestPropertiesConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {

    WebDriver driver;
    TestPropertiesConfig config = ConfigFactory.create(TestPropertiesConfig.class, System.getProperties());
    String baseUrl = config.getBaseUrl();

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }


    //locators

    //actions
    public void open() {
        driver.get(baseUrl);
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    //method -> open another page objects
    public WebFormPage openWebformPage(){
        driver.findElement(By.linkText("Web form")).click();
        return new WebFormPage(driver);
    }

}
