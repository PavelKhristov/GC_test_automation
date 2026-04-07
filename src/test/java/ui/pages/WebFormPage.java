package ui.pages;

import configs.TestPropertiesConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WebFormPage {

    WebDriver driver;
    private static final String WEB_FORM_URL = "web-form.html";

    //locators
    By title = By.className("display-6");

    //actions

    public WebFormPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public String getUrl() {
        return WEB_FORM_URL;
    }

    public String getTitle() {
        return driver.findElement(title).getText();
    }

    //method -> open another page objects

}
