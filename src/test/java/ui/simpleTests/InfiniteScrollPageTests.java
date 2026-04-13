package ui.simpleTests;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class InfiniteScrollPageTests extends BaseTest {


    @DisplayName("Тест Infinite Scroll via js")
    @Test
    void infiniteScrollTest() throws InterruptedException, IOException {
        driver.get(baseUrl + "infinite-scroll.html");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        By pLocator = By.tagName("p");
        List<WebElement> paragraphs = wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(pLocator, 0));
        int initParagraphsNumber = paragraphs.size();

        WebElement lastParagraph = driver.findElement(By.xpath(String.format("//p[%d]", initParagraphsNumber)));
        String script = "arguments[0].scrollIntoView();";
        js.executeScript(script, lastParagraph);

        System.out.println("lastParagraph" + lastParagraph);
        System.out.println("initParagraphsNumber" + initParagraphsNumber);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(pLocator, initParagraphsNumber));
        Thread.sleep(timeoutSlow);
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("./image.png"));
    }

    @DisplayName("Тест Infinite Scroll vai actions")
    @Test
    void infiniteScrollTest2() throws InterruptedException, IOException {
        driver.get(baseUrl + "infinite-scroll.html");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        By pLocator = By.tagName("p");
        List<WebElement> paragraphs = wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(pLocator, 0));
        int initParagraphsNumber = paragraphs.size();

        WebElement lastParagraph = driver.findElement(By.xpath(String.format("//p[%d]", initParagraphsNumber)));
        actions.scrollToElement(lastParagraph).perform();

        wait.until(ExpectedConditions.numberOfElementsToBe(pLocator, initParagraphsNumber));
        Thread.sleep(timeoutSlow);
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("./image.png"));
    }
}
