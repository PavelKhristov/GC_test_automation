package ui.simpleTests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class IframesPageTests extends BaseTest {


    @DisplayName("Тест для iframe")
    @Test
    void iframeTest() throws InterruptedException {
        driver.get(baseUrl + "iframes.html");
        assertThrows(NoSuchElementException.class, () -> driver.findElement(By.className("lead"))); //ошибка при попытке найти элемент внутри iframe
        WebElement iframeElement = driver.findElement(By.id("my-iframe"));

        driver.switchTo().frame(iframeElement);
        Thread.sleep(timeoutFast);

        assertThrows(NoSuchElementException.class, () -> driver.findElement(By.className("display-6")));//ошибка при попытке найти элемент вне iframe
        assertThat(driver.findElement(By.className("lead")).getText()).contains("Lorem ipsum");

        driver.switchTo().defaultContent();

        assertThat(driver.findElement(By.className("display-6")).getText()).contains("IFrame");
    }
}
