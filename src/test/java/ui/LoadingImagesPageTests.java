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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class LoadingImagesPageTests {
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

    @DisplayName("Тест с явным ожиданием")
    @Test
    void loadingImagesTest() {
        driver.get(baseUrl + "loading-images.html");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement loadingText = driver.findElement(By.id("text"));
        WebElement loadingSpinner = driver.findElement(By.id("spinner"));
        assertTrue(loadingSpinner.isDisplayed());
        assertEquals(loadingText.getText(), "Please wait until the images are loaded...");
        assertThrows(NoSuchElementException.class, () -> driver.findElement(By.className("compass")));
        assertThrows(NoSuchElementException.class, () -> driver.findElement(By.className("calendar")));
        assertThrows(NoSuchElementException.class, () -> driver.findElement(By.className("award")));
        assertThrows(NoSuchElementException.class, () -> driver.findElement(By.className("landscape")));

        WebElement compass = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("compass")));
        assertTrue(compass.isDisplayed());
        assertThrows(NoSuchElementException.class, () -> driver.findElement(By.className("calendar")));
        assertThrows(NoSuchElementException.class, () -> driver.findElement(By.className("award")));
        assertThrows(NoSuchElementException.class, () -> driver.findElement(By.className("landscape")));

        WebElement calendar = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("calendar")));
        assertTrue(calendar.isDisplayed());
        assertThrows(NoSuchElementException.class, () -> driver.findElement(By.className("award")));
        assertThrows(NoSuchElementException.class, () -> driver.findElement(By.className("landscape")));

        WebElement award = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("award")));
        assertTrue(award.isDisplayed());
        assertThrows(NoSuchElementException.class, () -> driver.findElement(By.className("landscape")));

        WebElement landscape = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("landscape")));
        assertTrue(landscape.isDisplayed());
        WebElement loadedText = driver.findElement(By.id("text"));
        assertThrows(NoSuchElementException.class, () -> driver.findElement(By.id("spinner")));
        assertEquals(loadedText.getText(), "Done!");
    }

    @DisplayName("Пример теста с неявным ожиданием")
    @Test
    void limplicitWaitTest() {
        driver.get(baseUrl + "loading-images.html");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        /*тест не ждет 20 сек., т.к. постоянно выполняет проверку и локатор находится раньше.
        Минус в том что если локатор не будет найден, то тест будет бежать 20 секунд.
        (правильнее будет поставить меньше времени ожидания, если тест обычно бегает быстрее)
        */
        WebElement landscape = driver.findElement(By.id("landscape"));
        assertThat(landscape.getAttribute("src")).containsIgnoringCase("landscape");
    }

    @DisplayName("Пример теста с явным ожиданием")
    @Test
    void explicitWaitTest() {
        driver.get(baseUrl + "loading-images.html");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//div[@id='image-container']/img"), 4));
    }

    @DisplayName("Пример теста с гибким ожиданием")
    @Test
    void fluentWaitTest() {
        driver.get(baseUrl + "loading-images.html");
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class);

        WebElement landscape = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("landscape")));
        assertThat(landscape.getAttribute("src")).containsIgnoringCase("landscape");
    }



}
