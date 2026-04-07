package ui;

import configs.TestConfig;
import configs.TestPropertiesConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class MouseOverPageTests {

    WebDriver driver;
    TestPropertiesConfig config = ConfigFactory.create(TestPropertiesConfig.class, System.getProperties());
    String baseUrl = config.getBaseUrl();
    Integer timeoutWeryFast = config.getTimeoutWeryFast();
    Integer timeoutFast = config.getTimeoutFast();
    Integer timeoutMedium = config.getTimeoutMedium();
    Integer timeoutSlow = config.getTimeoutSlow();

    @BeforeEach
    void setup(){
        driver = new ChromeDriver();
        driver.get(baseUrl);
        driver.manage().window().maximize();
    };

    @AfterEach
    void tearDown (){
        driver.quit();
    };

    @DisplayName("Тест Наведение мыши на элементы")
    @Test
    void MouseOverTest () throws InterruptedException {
        driver.findElement(By.xpath("//a[@class = 'btn btn-outline-primary mb-2' and text() = 'Mouse over']")).click();
        Thread.sleep(timeoutFast);

        List<WebElement> images = driver.findElements(By.xpath("//div[@class='figure text-center col-3 py-2']/img"));
        for (WebElement image : images) {
            Actions actions = new Actions(driver);
            actions.moveToElement(image).perform();
            Thread.sleep(timeoutFast);
        }
    }

}
