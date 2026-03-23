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
    private static final String BASE_URL = "https://bonigarcia.dev/selenium-webdriver-java/";

    @BeforeEach
    void setup(){
        driver = new ChromeDriver();
        driver.get(BASE_URL);
        driver.manage().window().maximize();
    };

    @AfterEach
    void tearDown (){
        driver.quit();
    };

    @DisplayName("Тест для iframe")
    @Test
    void iframeTest() throws InterruptedException {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/iframes.html");
        assertThrows(NoSuchElementException.class, () -> driver.findElement(By.className("lead"))); //ошибка при попытке найти элемент внутри iframe
        WebElement iframeElement = driver.findElement(By.id("my-iframe"));

        driver.switchTo().frame(iframeElement);
        Thread.sleep(1000);

        assertThrows(NoSuchElementException.class, () -> driver.findElement(By.className("display-6")));//ошибка при попытке найти элемент вне iframe
        assertThat(driver.findElement(By.className("lead")).getText()).contains("Lorem ipsum");

        driver.switchTo().defaultContent();

        assertThat(driver.findElement(By.className("display-6")).getText()).contains("IFrame");
    }
}
