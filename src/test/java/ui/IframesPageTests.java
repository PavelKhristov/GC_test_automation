package ui;

import configs.TestConfig;
import configs.TestPropertiesConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class IframesPageTests {
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

    @DisplayName("Тест для iframe")
    @Test
    void iframeTest() throws InterruptedException {
        driver.get(baseUrl + "iframes.html");
        assertThrows(NoSuchElementException.class, () -> driver.findElement(By.className("lead"))); //ошибка при попытке найти элемент внутри iframe
        WebElement iframeElement = driver.findElement(By.id("my-iframe"));

        driver.switchTo().frame(iframeElement);
        Thread.sleep(timeoutFast);

        assertThrows(NoSuchElementException.class, () -> driver.findElement(By.className("display-6")));//ошибка при попытке найти элемент вне iframe
        assertThat(driver.findElement(By.className("lead")).getText()).contains("Lorem ipsum");

        driver.switchTo().defaultContent();

        assertThat(driver.findElement(By.className("display-6")).getText()).contains("IFrame");
    }
}
