package ui.POMTests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import ui.pages.HomePage;
import ui.pages.WebFormPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WebFormPageWithPOMTests extends BaseTestForPOM {


    @DisplayName("Тест WebFormPage with POM")
    @Test
    void OpenWebFormTest () {
        HomePage homePage = new HomePage(driver);
        WebFormPage webFormPage = homePage.openWebformPage();

        assertEquals(homePage.getBaseUrl() + webFormPage.getUrl(), webFormPage.getCurrentUrl());
        assertEquals("Web form", webFormPage.getTitle());
        assertEquals("Hands-On Selenium WebDriver with Java", webFormPage.getMainTitle());
    }

    @DisplayName("Тест Submit кнопки")
    @Test
    void SubmitFormTest () throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        WebFormPage webFormPage = homePage.openWebformPage();
        webFormPage.submit();
        Thread.sleep(timeoutFast);
        assertEquals("Form submitted", webFormPage.getTitle());
        assertEquals("Received!", webFormPage.getPageText());
        Thread.sleep(timeoutFast);
        driver.navigate().back();
        Thread.sleep(timeoutFast);
        assertEquals("Web form", webFormPage.getTitle());
    }



}
