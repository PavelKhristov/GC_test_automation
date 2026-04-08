package ui.pages;

import configs.TestPropertiesConfig;
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
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public String getMainTitle() {
        return driver.getTitle();
    }
}
