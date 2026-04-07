package ui;

import configs.TestPropertiesConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.openqa.selenium.PageLoadStrategy.NONE;

public class OtherTests {
    //Оставил тест без BaseTest намеренно
    WebDriver driver;
    //Конфиг берется из configs.TestConfig
    TestPropertiesConfig config = ConfigFactory.create(TestPropertiesConfig.class, System.getProperties());
    String baseUrl = config.getBaseUrl();

    @BeforeEach
    void setup(){
        ChromeOptions chromeOptions = new ChromeOptions();
        //chromeOptions.setPlatformName("Windows 10"); //set remote OS
        chromeOptions.setBrowserVersion("123.0.6312.86");
        chromeOptions.setPageLoadStrategy(NONE);
        chromeOptions.setAcceptInsecureCerts(true);
        chromeOptions.setPageLoadTimeout(Duration.ofSeconds(60));
        chromeOptions.setScriptTimeout(Duration.ofSeconds(30));
        chromeOptions.setImplicitWaitTimeout(Duration.ofSeconds(5));
        driver = new ChromeDriver(chromeOptions);
        driver.get(baseUrl);
    };

    @AfterEach
    void tearDown (){
        driver.quit();
    };

    @DisplayName("Тест открытия новой табы в браузере")
    @Test
    void testNewTab() {
        String initHandle = driver.getWindowHandle();

        driver.switchTo().newWindow(WindowType.TAB);
        driver.get(baseUrl + "web-form.html");
        assertThat(driver.getWindowHandles()).hasSize(2);

        driver.switchTo().window(initHandle);
        driver.close();
        assertThat(driver.getWindowHandles()).hasSize(1);
    }

    @DisplayName("Тест открытия нового окна в браузере")
    @Test
    void testNewWindow() {
        String initHandle = driver.getWindowHandle();

        driver.switchTo().newWindow(WindowType.WINDOW);
        driver.get(baseUrl + "web-form.html");
        assertThat(driver.getWindowHandles()).hasSize(2);

        driver.switchTo().window(initHandle);
        driver.close();
        assertThat(driver.getWindowHandles()).hasSize(1);
    }

    @DisplayName("Тест окна браузера")
    @Test
    void WindowTest() {
        WebDriver.Window window = driver.manage().window();

        Point initialPosition = window.getPosition();
        Dimension initialSize = window.getSize();
        System.out.printf("Initial window: position {%s} -- size {%s}\n", initialPosition, initialSize);

        window.maximize();

        Point maximizedPosition = window.getPosition();
        Dimension maximizedSize = window.getSize();
        System.out.printf("Maximized window: position {%s} -- size {%s}\n", maximizedPosition, maximizedSize);

        assertThat(initialPosition).isNotEqualTo(maximizedPosition);
        assertThat(initialSize).isNotEqualTo(maximizedSize);
    }
}
