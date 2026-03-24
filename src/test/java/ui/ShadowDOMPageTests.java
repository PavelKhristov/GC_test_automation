package ui;

import configs.TestConfig;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ShadowDOMPageTests {
    WebDriver driver;
    TestConfig config = new TestConfig();
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

    @DisplayName("Тест ShadowDOM")
    @Test
    void testShadowDom() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/shadow-dom.html");

        assertThrows(NoSuchElementException.class, () -> driver.findElement(By.cssSelector("p")));
        WebElement content = driver.findElement(By.id("content"));
        SearchContext shadowRoot = content.getShadowRoot();
        WebElement textElement = shadowRoot.findElement(By.cssSelector("p"));
        assertThat(textElement.getText()).contains("Hello Shadow DOM");
    }
}
