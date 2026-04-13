package ui.simpleTests;

import configs.TestConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginPageTests extends BaseTest {

    //используем конфиг из TestConfig
    TestConfig config = new TestConfig();
    String timeoutFast = config.getTimeoutFast();


    @DisplayName("Тест авторизации")
    @Test
    void signInTest() throws InterruptedException {
        driver.findElement(By.xpath("//a[@href = 'login-form.html']")).click();

        driver.findElement(By.id("username")).sendKeys(config.getUsername());
        driver.findElement(By.id("password")).sendKeys(config.getPassword());
        Thread.sleep(Long.parseLong(timeoutFast));
        driver.findElement(By.xpath("//button[@type = 'submit']")).click();
        Thread.sleep(Long.parseLong(timeoutFast));
        WebElement message = driver.findElement(By.className("alert"));
        assertEquals("Login successful", message.getText());
    }
}
