package ui.Selenide.SelTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ui.Selenide.pages.HomePageForSelenide;
import ui.Selenide.pages.WebFormPageForSelenide;

import static com.codeborne.selenide.Condition.value;
import static constants.Constants.BASE_URL;
import static constants.Constants.WEB_FORM_URL;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SelenideTests {

    HomePageForSelenide homePage;

    @BeforeEach
    void init(){
        homePage = new HomePageForSelenide();
    };

    @DisplayName("Проверка домашней страницы")
    @Test
    void openHomePageTest (){
        homePage.open();

        assertEquals(BASE_URL, homePage.getCurrentUrl());
        assertEquals("Hands-On Selenium WebDriver with Java", homePage.getMainTitle());
    };

    @DisplayName("Тест ткрытие WebFormPage")
    @Test
    void OpenWebFormTest () {
        homePage.open();
        WebFormPageForSelenide webFormPage = homePage.openWebformPage();

        assertEquals(BASE_URL + WEB_FORM_URL, webFormPage.getCurrentUrl());
        assertEquals("Hands-On Selenium WebDriver with Java", webFormPage.getMainTitle());
        assertEquals("Web form", webFormPage.getTitle());
    }

    @DisplayName("Тест WebFormPage")
    @Test
    void readonlyTextTest () {
        homePage.open();
        WebFormPageForSelenide webFormPage = homePage.openWebformPage();

        webFormPage.getReadonlyInput().shouldHave(value("Readonly input"));
    }

}
