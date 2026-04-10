package ui.POMTests;

import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import ui.pages.HomePage;
import ui.pages.WebFormPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
@Feature("POM")
public class WebFormPageWithPOMTests extends BaseTestForPOM {
    HomePage homePage;

    WebFormPage webFormPage;

    @DisplayName("Тест WebFormPage with POM")
    @Test
    void OpenWebFormTest () {
        homePage = new HomePage(driver);
        webFormPage = homePage.openWebformPage();

        webFormPage.validateCurrentURL();
        webFormPage.validateTitleName("Web form");
        webFormPage.validateMainTitleName();
    }

    @DisplayName("Тест Submit кнопки")
    @Test
    void SubmitFormTest () throws InterruptedException {
        homePage = new HomePage(driver);
        webFormPage = homePage.openWebformPage();
        webFormPage.submit();
        Thread.sleep(timeoutFast);
        webFormPage.validateTitleName("Form submitted");
        webFormPage.validatePageText("Received!");
        Thread.sleep(timeoutFast);
        driver.navigate().back();
        Thread.sleep(timeoutFast);
        webFormPage.validateTitleName("Web form");
    }



}
