package ui.POMTests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ui.pages.HomePage;
import ui.pages.WebFormPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WebFormPageWithPOMTests extends BaseTestForPOM {


    @DisplayName("Тест WebFormPage with POM")
    @Test
    void ReturnToIndexTest () {
        HomePage homePage = new HomePage(driver);
        homePage.open();
        WebFormPage webFormPage = homePage.openWebformPage();

        assertEquals(baseUrl + webFormPage.getUrl(), webFormPage.getCurrentUrl());
        assertEquals("Web form", webFormPage.getTitle());
        assertEquals("Hands-On Selenium WebDriver with Java", webFormPage.getMainTitle());

    }



}
