package ui;

import configs.TestConfig;
import configs.TestPropertiesConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class CookiesPageTests {
    WebDriver driver;
//    TestConfig config = new TestConfig();
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

    @DisplayName("Тест для cookies")
    @Test
    void cookieTest() throws InterruptedException {
        driver.get(baseUrl + "cookies.html");
        WebDriver.Options options = driver.manage();
        Set<Cookie> cookies = options.getCookies();
        assertThat(cookies).hasSize(2);
        Cookie username = options.getCookieNamed("username");
        assertThat(username.getValue()).isEqualTo("John Doe");
        assertThat(username.getPath()).isEqualTo("/");

        Thread.sleep(timeoutMedium);
        driver.findElement(By.id("refresh-cookies")).click();

        Cookie newCookie = new Cookie("new-cookie-key", "new-cookie-value");
        options.addCookie(newCookie);
        String readValue = options.getCookieNamed(newCookie.getName())
                .getValue();
        assertThat(newCookie.getValue()).isEqualTo(readValue);

        cookies = options.getCookies();
        assertThat(cookies).hasSize(3);

        Thread.sleep(timeoutMedium);
        driver.findElement(By.id("refresh-cookies")).click();

        String[] actualText = driver.findElement(By.id("cookies-list")).getText().split("\n");
        assertEquals("username=John Doe", actualText[0]);
        assertEquals("date=10/07/2018", actualText[1]);
        assertEquals("new-cookie-key=new-cookie-value", actualText[2]);

        options.deleteCookie(username);
        assertThat(options.getCookies()).hasSize(cookies.size() - 1);

        Thread.sleep(timeoutMedium);
        driver.findElement(By.id("refresh-cookies")).click();
    }

}
