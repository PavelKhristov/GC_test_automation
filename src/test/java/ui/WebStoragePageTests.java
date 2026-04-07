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
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.SessionStorage;
import org.openqa.selenium.html5.WebStorage;

import static org.assertj.core.api.Assertions.assertThat;

public class WebStoragePageTests {
    WebDriver driver;
    TestPropertiesConfig config = ConfigFactory.create(TestPropertiesConfig.class, System.getProperties());
    String baseUrl = config.getBaseUrl();

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
    //html5 можно найти в 'org.seleniumhq.selenium:selenium-java:4.30.0'
    @DisplayName("Тест WebStorage")
    @Test
    void testWebStorage() {
        driver.get(baseUrl + "web-storage.html");
        WebStorage webStorage = (WebStorage) driver;

        LocalStorage localStorage = webStorage.getLocalStorage();
        System.out.printf("Local storage elements: {%s}\n", localStorage.size());

        SessionStorage sessionStorage = webStorage.getSessionStorage();
        sessionStorage.keySet()
                .forEach(key -> System.out.printf("Session storage: {%s}={%s}\n", key, sessionStorage.getItem(key)));
        assertThat(sessionStorage.size()).isEqualTo(2);

        sessionStorage.setItem("new element", "new value");
        assertThat(sessionStorage.size()).isEqualTo(3);

        driver.findElement(By.id("display-session")).click();
    }
}
