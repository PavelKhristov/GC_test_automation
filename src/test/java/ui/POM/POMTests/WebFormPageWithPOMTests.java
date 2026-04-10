package ui.POM.POMTests;

import io.qameta.allure.Feature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ui.POM.pages.WebFormPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
@Feature("POM")
public class WebFormPageWithPOMTests extends BaseTestForPOM {

    WebFormPage webFormPage;

    @BeforeEach
    void openWebPage(){
        webFormPage = homePage.openWebformPage();
    };

    @DisplayName("Тест WebFormPage with POM")
    @Test
    void OpenWebFormTest () {

        webFormPage.validateCurrentURL();
        webFormPage.validateTitleName("Web form");
        webFormPage.validateMainTitleName();
    }

    @DisplayName("Тест Submit кнопки")
    @Test
    void SubmitFormTest () throws InterruptedException {
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
