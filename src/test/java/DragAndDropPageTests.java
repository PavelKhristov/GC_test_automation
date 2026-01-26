import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

        Rectangle recBefore = draggable.getRect();
        Point loactionBefore = draggable.getLocation();
        Dimension sizeBefore = draggable.getSize();;
        System.out.printf("LoactionBefore: %s\n", loactionBefore);
        System.out.printf("SizeBefore: %s\n", sizeBefore);
        System.out.printf("Dimension %s, Height %s, Width %s, Point %s, X: %s, Y: %s\n", recBefore.getDimension(), recBefore.getHeight(), recBefore.getWidth(), recBefore.getPoint(), recBefore.getX(), recBefore.getY());
        actions
                .dragAndDrop(draggable, droppable)
                .perform();
        Thread.sleep(2000);

        Rectangle recAfter = draggable.getRect();
        Point loactionAfter = draggable.getLocation();
        Dimension sizeAfter = draggable.getSize();;
        System.out.printf("LoactionAfter: %s\n", loactionAfter);
        System.out.printf("SizeAfter: %s\n", sizeAfter);
        System.out.printf("Dimension %s, Height %s, Width %s, Point %s, X: %s, Y: %s\n", recAfter.getDimension(), recAfter.getHeight(), recAfter.getWidth(), recAfter.getPoint(), recAfter.getX(), recAfter.getY());

        Point expectedLocation = droppable.getLocation();
        System.out.printf("expectedLocation: %s\n", expectedLocation);

        //хардкод ER позиции
        assertEquals("(972, 236)", loactionAfter.toString());
        //По другому можно сравнить с позицией элемента, куда переносим
        assertEquals(expectedLocation, loactionAfter);
        //Проверяем изменение в стиле
        assertEquals("left: 660px; top: 0px;", draggable.getDomAttribute("style"));
    }
}
