package ui.POMTests;

import configs.TestPropertiesConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

public class BaseTestForPOM {

    WebDriver driver;
    Actions actions;
    TestPropertiesConfig config = ConfigFactory.create(TestPropertiesConfig.class, System.getProperties());
//    String baseUrl = config.getBaseUrl();

    //Таймауты
    Integer timeoutWeryFast = config.getTimeoutWeryFast();
    Integer timeoutFast = config.getTimeoutFast();
    Integer timeoutMedium = config.getTimeoutMedium();
    Integer timeoutSlow = config.getTimeoutSlow();


    @BeforeEach
    void setup(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        actions = new Actions(driver);
    };

    @AfterEach
    void tearDown (){
        driver.quit();
    };
}
