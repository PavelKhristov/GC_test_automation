import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class DropdownMenuPageTests {

    WebDriver driver;
    Actions actions;
    private static final String BASE_URL = "https://bonigarcia.dev/selenium-webdriver-java/";

    @BeforeEach
    void setup(){
        driver = new ChromeDriver();
        driver.get(BASE_URL);
        driver.manage().window().maximize();
        actions = new Actions(driver);
    };

    @AfterEach
    void tearDown (){
        driver.quit();
    };

    @DisplayName("Тест Открытие меню кликом выши")
    @Test
    void DropdownMenuTest () throws InterruptedException {
        driver.findElement(By.xpath("//a[@class = 'btn btn-outline-primary mb-2' and text() = 'Dropdown menu']")).click();
        Thread.sleep(1000);

        WebElement dropdown1 = driver.findElement(By.id("my-dropdown-1"));
        WebElement dropdown2 = driver.findElement(By.id("my-dropdown-2"));
        WebElement dropdown3 = driver.findElement(By.id("my-dropdown-3"));


        actions.click(dropdown1).perform();
        Thread.sleep(2000);

        actions.contextClick(dropdown2).perform();
        Thread.sleep(2000);

        actions.doubleClick(dropdown3).perform();
        Thread.sleep(2000);
    }
    // остановился на 1:11:29
}
