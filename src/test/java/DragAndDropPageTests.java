import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DragAndDropPageTests {

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

    @DisplayName("Тест Драг'н'дроп")
    @Test
    void DropdownMenuTest () throws InterruptedException {
        driver.findElement(By.xpath("//a[@class = 'btn btn-outline-primary mb-2' and text() = 'Drag and drop']")).click();
        Thread.sleep(1000);


        WebElement draggable = driver.findElement(By.id("draggable"));
        WebElement droppable = driver.findElement(By.id("target"));
        actions.dragAndDrop(draggable, droppable).perform();
        Thread.sleep(2000);
        assertEquals("left: 660px; top: 0px;", draggable.getDomAttribute("style"));
    }
}
