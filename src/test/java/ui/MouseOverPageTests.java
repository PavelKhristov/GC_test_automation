package ui;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class MouseOverPageTests extends BaseTest{



    @DisplayName("Тест Наведение мыши на элементы")
    @Test
    void MouseOverTest () throws InterruptedException {
        driver.findElement(By.xpath("//a[@class = 'btn btn-outline-primary mb-2' and text() = 'Mouse over']")).click();
        Thread.sleep(timeoutFast);

        List<WebElement> images = driver.findElements(By.xpath("//div[@class='figure text-center col-3 py-2']/img"));
        for (WebElement image : images) {
            Actions actions = new Actions(driver);
            actions.moveToElement(image).perform();
            Thread.sleep(timeoutFast);
        }
    }

}
