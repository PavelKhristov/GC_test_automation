package ui.POM.pages;

import configs.TestPropertiesConfig;
import io.qameta.allure.Step;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;

public class BasePage {
    WebDriver driver;
    TestPropertiesConfig config = ConfigFactory.create(TestPropertiesConfig.class, System.getProperties());
    String baseUrl = config.getBaseUrl();

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    //locators

    //actions
    @Step("Get current url")
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
    @Step("Get main title")
    public String getMainTitle() {
        return driver.getTitle();
    }

    public String getBaseUrl() {
        return baseUrl;
    }
}
