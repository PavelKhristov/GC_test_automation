package ui.simpleTests;

import configs.TestConfig;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import steps.BaseSteps;
import ui.extensions.AllureExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Feature("Extensions")
@ExtendWith(AllureExtension.class)
public class ExtensionTests extends BaseSteps{
    //логин и пароль в данном кейсе были добавлены в открытую намеренно, для упрощения. Работу с конфигами и скрытием кредов смотреть в LoginPageTests.
    @DisplayName("Тест успешной авторизации")
    @Test
    void testLoginSuccess() throws InterruptedException {
        WebDriver driver = getDriver();
        driver.findElement(By.xpath("//a[@href = 'login-form.html']")).click();

        driver.findElement(By.id("username")).sendKeys("user");
        driver.findElement(By.id("password")).sendKeys("user");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[@type = 'submit']")).click();
        Thread.sleep(1000);
        WebElement message = driver.findElement(By.className("alert"));
        assertEquals("Login successful", message.getText());
    }

    //тест для проверки работы extensions, для этого теста должен добавляться шаг со скриншотом, а для предыдущего нет
    @DisplayName("Тест падающей авторизации")
    @Test
    void testLoginFailure() throws InterruptedException {
        WebDriver driver = getDriver();
        driver.findElement(By.xpath("//a[@href = 'login-form.html']")).click();

        driver.findElement(By.id("username")).sendKeys("user1");
        driver.findElement(By.id("password")).sendKeys("user1");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[@type = 'submit']")).click();
        Thread.sleep(1000);
        WebElement message = driver.findElement(By.className("alert"));
        assertEquals("Login successful", message.getText());
    }

}
