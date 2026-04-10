package ui.POMTests;

import io.qameta.allure.Feature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ui.pages.HomePage;
import ui.pages.NavigationPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
@Feature("POM")
public class NaviationPageWithPOMTests extends BaseTestForPOM {

    @DisplayName("Тест NavigationPage with POM")
    @Test
    void NavigationPageTest () throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        NavigationPage navigationPage = homePage.openNavigationPage();

        assertEquals(homePage.getBaseUrl() + navigationPage.getUrl(), navigationPage.getCurrentUrl());
        assertEquals("Navigation example", navigationPage.getTitle());
        assertEquals("Hands-On Selenium WebDriver with Java", navigationPage.getMainTitle());

        assertEquals(navigationPage.FirstPageText(), navigationPage.getPageText());
        assertEquals(5, navigationPage.getButtons().size());
        Assertions.assertTrue(navigationPage.checkButtonIsActive("1"));
        navigationPage.checkButtonIsNotClickable("Previous");

        navigationPage.clickButton("2");
        Thread.sleep(timeoutFast);
        assertEquals(navigationPage.SecondPageText(), navigationPage.getPageText());
        Assertions.assertTrue(navigationPage.checkButtonIsActive("2"));

        navigationPage.clickButton("3");
        Thread.sleep(timeoutFast);
        assertEquals(navigationPage.ThirdPageText(), navigationPage.getPageText());
        Assertions.assertTrue(navigationPage.checkButtonIsActive("3"));
        navigationPage.checkButtonIsNotClickable("Next");

        navigationPage.clickButton("1");
        Thread.sleep(timeoutFast);
        assertEquals(navigationPage.FirstPageText(), navigationPage.getPageText());
        Assertions.assertTrue(navigationPage.checkButtonIsActive("1"));
        navigationPage.checkButtonIsNotClickable("Previous");

        navigationPage.clickButton("Next");
        Thread.sleep(timeoutFast);
        assertEquals(navigationPage.SecondPageText(), navigationPage.getPageText());
        Assertions.assertTrue(navigationPage.checkButtonIsActive("2"));

        navigationPage.clickButton("Previous");
        Thread.sleep(timeoutFast);
        assertEquals(navigationPage.FirstPageText(), navigationPage.getPageText());
        Assertions.assertTrue(navigationPage.checkButtonIsActive("1"));
        navigationPage.checkButtonIsNotClickable("Previous");

    }
}
