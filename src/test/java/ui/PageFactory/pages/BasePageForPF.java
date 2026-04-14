package ui.PageFactory.pages;

import components.HeaderComponent;
import configs.TestPropertiesConfig;
import io.qameta.allure.Step;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePageForPF {
    WebDriver driver;
    TestPropertiesConfig config = ConfigFactory.create(TestPropertiesConfig.class, System.getProperties());
    String baseUrl = config.getBaseUrl();

    public BasePageForPF(WebDriver driver) {
        this.driver = driver;
        header = new HeaderComponent(driver);
        PageFactory.initElements(driver, this);
    }

    //locators
    HeaderComponent header;

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

    public HeaderComponent getHeader(){return header;}
}
