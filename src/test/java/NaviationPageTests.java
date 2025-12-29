import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NaviationPageTests {

    WebDriver driver;
//    Actions actions;
    private static final String BASE_URL = "https://bonigarcia.dev/selenium-webdriver-java/";

    @BeforeEach
    void setup(){
        driver = new ChromeDriver();
        driver.get(BASE_URL);
        driver.manage().window().maximize();
//        actions = new Actions(driver);
    };

    @AfterEach
    void tearDown (){
        driver.quit();
    };

    @DisplayName("Тест Navigation")
    @Test
    void NavigationTest () throws InterruptedException {
        driver.findElement(By.xpath("//a[@class = 'btn btn-outline-primary mb-2' and text() = 'Navigation']")).click();


        assertEquals("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.", driver.findElement(By.className("lead")).getText());
        assertEquals("https://bonigarcia.dev/selenium-webdriver-java/navigation1.html", driver.getCurrentUrl());

        driver.findElement(By.linkText("2")).click();
        Thread.sleep(2000);
        assertEquals("Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.", driver.findElement(By.className("lead")).getText());

        driver.findElement(By.linkText("3")).click();
        Thread.sleep(2000);
        assertEquals("Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.", driver.findElement(By.className("lead")).getText());
        Assertions.assertThrows(ElementNotInteractableException.class, () -> driver.findElement(By.linkText("Next")).click());

        driver.findElement(By.linkText("1")).click();
        Thread.sleep(2000);
        assertEquals("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.", driver.findElement(By.className("lead")).getText());
        Assertions.assertThrows(ElementNotInteractableException.class, () -> driver.findElement(By.linkText("Previous")).click());

        driver.findElement(By.linkText("Next")).click();
        Thread.sleep(2000);
        assertEquals("Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.", driver.findElement(By.className("lead")).getText());

        driver.findElement(By.linkText("Previous")).click();
        Thread.sleep(2000);
        assertEquals("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.", driver.findElement(By.className("lead")).getText());


        Thread.sleep(2000);

    }
}
