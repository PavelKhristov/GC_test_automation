package ui.pages;

import configs.TestPropertiesConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WebFormPage extends BasePage{

//    WebDriver driver;
    private static final String WEB_FORM_URL = "web-form.html";

    public WebFormPage(WebDriver driver) {
        super(driver);
    }

    //locators
    By title = By.className("display-6");

    //actions


    public String getUrl() {
        return WEB_FORM_URL;
    }

    public String getTitle() {
        return driver.findElement(title).getText();
    }

    //method -> open another page objects

}
