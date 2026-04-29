package ui.PageFactory.PFTests;

import io.qameta.allure.Feature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ui.POM.POMTests.BaseTestForPOM;
import ui.POM.pages.WebFormPage;
import ui.PageFactory.pages.WebFormPageForPF;

@Feature("POM")
public class WebFormPageTestsForPF extends BaseTestForPF {

    WebFormPageForPF webFormPage;

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
