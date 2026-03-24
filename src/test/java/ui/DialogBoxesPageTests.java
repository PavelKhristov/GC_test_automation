package ui;

import configs.TestConfig;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

public class DialogBoxesPageTests {
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

    @DisplayName("Тест для диалоговых окон")
    @Test
    void dialogBoxesTest() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        driver.get("https://bonigarcia.dev/selenium-webdriver-java/dialog-boxes.html");
        driver.findElement(By.id("my-alert")).click();
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        assertThat(alert.getText()).isEqualTo("Hello world!");
        alert.accept();
        driver.findElement(By.id("my-confirm")).click();
        driver.switchTo().alert().accept();
        assertThat(driver.findElement(By.id("confirm-text")).getText()).isEqualTo("You chose: true");
        driver.findElement(By.id("my-confirm")).click();
        driver.switchTo().alert().dismiss();
        assertThat(driver.findElement(By.id("confirm-text")).getText()).isEqualTo("You chose: false");
        driver.findElement(By.id("my-prompt")).click();
        driver.switchTo().alert().sendKeys("Test");
        driver.switchTo().alert().accept();
        assertThat(driver.findElement(By.id("prompt-text")).getText()).isEqualTo("You typed: Test");
        driver.findElement(By.id("my-modal")).click();
        WebElement save = driver.findElement(By.xpath("//button[normalize-space() = 'Save changes']"));
        wait.until(ExpectedConditions.elementToBeClickable(save));
        save.click();
    }
}
