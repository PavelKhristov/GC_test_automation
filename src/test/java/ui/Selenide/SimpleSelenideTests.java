package ui.Selenide;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import steps.AllureSteps;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.attributeMatching;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;
import static constants.Constants.BASE_URL;

public class SimpleSelenideTests {
    AllureSteps allureSteps = new AllureSteps();

//    Home page
    @DisplayName("Проверка домашней страницы")
    @Test
    void openHomePageTest(){
        open(BASE_URL);
        Assertions.assertEquals("Hands-On Selenium WebDriver with Java", Selenide.title());
        Assertions.assertEquals("https://bonigarcia.dev/selenium-webdriver-java/", url());
    }

//    Infinite scroll page
    @Test
    @DisplayName("Check screenshot attachment")
    void infiniteScrollTestWithAttach() throws InterruptedException, IOException {
        Selenide.open("https://bonigarcia.dev/selenium-webdriver-java/infinite-scroll.html");
        WebDriver driver = Selenide.webdriver().object();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        By pLocator = By.tagName("p");
        List<WebElement> paragraphs = wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(pLocator, 0));
        int initParagraphsNumber = paragraphs.size();

        WebElement lastParagraph = driver.findElement(By.xpath(String.format("//p[%d]", initParagraphsNumber)));
        String script = "arguments[0].scrollIntoView();";
        js.executeScript(script, lastParagraph);

        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(pLocator, initParagraphsNumber));
        Thread.sleep(3000);
        allureSteps.captureScreenshotSelenide();
    }

//    Loading images page
    @Test
    void loadingImagesDefaultWaitTest() {
        open("https://bonigarcia.dev/selenium-webdriver-java/loading-images.html");

        $("#compass").shouldHave(attributeMatching("src", ".*compass.*"));
    }

    @Test
    void loadingImagesWithUpdatedTimeoutWaitTest() {
        open("https://bonigarcia.dev/selenium-webdriver-java/loading-images.html");
        Configuration.timeout = 10_000;
        $("#landscape").shouldHave(attributeMatching("src", ".*landscape.*"));
    }

    @Test
    void loadingImagesWithExplicitTimeoutWaitTest() {
        open("https://bonigarcia.dev/selenium-webdriver-java/loading-images.html");

        ElementsCollection images = $$("img").filter(visible);
        images.shouldHave(size(4), Duration.ofSeconds(10));
    }
}
