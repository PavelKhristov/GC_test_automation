import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NaviationPageTests {

    WebDriver driver;
    //убрал Actions, для реализации разных способов
    //Actions actions;

    private static final String BASE_URL = "https://bonigarcia.dev/selenium-webdriver-java/";
    private static final String firstPageText =
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.";
    private static final String middlePageText =
            "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.";
    private static final String lastPageText =
            "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";

    @BeforeEach
    void setup(){
        driver = new ChromeDriver();
        driver.get(BASE_URL);
        driver.manage().window().maximize();
        //actions = new Actions(driver);
    };

    @AfterEach
    void tearDown (){
        driver.quit();
    };

    @DisplayName("Тест Navigation")
    @Test
    void NavigationTest () throws InterruptedException {
        driver.findElement(By.xpath("//a[@class = 'btn btn-outline-primary mb-2' and text() = 'Navigation']")).click();

        assertEquals(firstPageText, driver.findElement(By.className("lead")).getText());
        assertEquals("https://bonigarcia.dev/selenium-webdriver-java/navigation1.html", driver.getCurrentUrl());
        List<WebElement> buttons = driver.findElements(By.xpath("//li[contains(@class, 'page-item')]"));
        assertEquals(5, buttons.size());
        Assertions.assertTrue(
                driver.findElement(By.xpath("//a[text()='1']/..")).getAttribute("class").contains("active"));
        Assertions.assertThrows(ElementNotInteractableException.class, () -> driver.findElement(By.linkText("Previous")).click());


        driver.findElement(By.linkText("2")).click();
        Thread.sleep(1000);
        assertEquals(middlePageText, driver.findElement(By.className("lead")).getText());
        Assertions.assertTrue(
                driver.findElement(By.xpath("//a[text()='2']/..")).getAttribute("class").contains("active"));


        driver.findElement(By.linkText("3")).click();
        Thread.sleep(1000);
        assertEquals(lastPageText, driver.findElement(By.className("lead")).getText());
        Assertions.assertThrows(ElementNotInteractableException.class, () -> driver.findElement(By.linkText("Next")).click());
        Assertions.assertTrue(
                driver.findElement(By.xpath("//a[text()='3']/..")).getAttribute("class").contains("active"));

        driver.findElement(By.linkText("1")).click();
        Thread.sleep(1000);
        assertEquals(firstPageText, driver.findElement(By.className("lead")).getText());
        Assertions.assertThrows(ElementNotInteractableException.class, () -> driver.findElement(By.linkText("Previous")).click());
        Assertions.assertTrue(
                driver.findElement(By.xpath("//a[text()='1']/..")).getAttribute("class").contains("active"));

        driver.findElement(By.linkText("Next")).click();
        Thread.sleep(1000);
        assertEquals(middlePageText, driver.findElement(By.className("lead")).getText());
        Assertions.assertTrue(
                driver.findElement(By.xpath("//a[text()='2']/..")).getAttribute("class").contains("active"));

        driver.findElement(By.linkText("Previous")).click();
        Thread.sleep(1000);
        assertEquals(firstPageText, driver.findElement(By.className("lead")).getText());
        Assertions.assertTrue(
                driver.findElement(By.xpath("//a[text()='1']/..")).getAttribute("class").contains("active"));
        Assertions.assertThrows(ElementNotInteractableException.class, () -> driver.findElement(By.linkText("Previous")).click());
        Thread.sleep(1000);

    }
}
