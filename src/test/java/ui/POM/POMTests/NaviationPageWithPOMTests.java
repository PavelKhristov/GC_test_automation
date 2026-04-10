package ui.POM.POMTests;

import io.qameta.allure.Feature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ui.POM.pages.NavigationPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static constants.Constants.*;

@Feature("POM")
public class NaviationPageWithPOMTests extends BaseTestForPOM {

    NavigationPage navigationPage;

    @DisplayName("Тест NavigationPage with POM")
    @Test
    void NavigationPageTest () throws InterruptedException {
        navigationPage = homePage.openNavigationPage();

        assertEquals(homePage.getBaseUrl() + NAVIGATION_PAGE_URL, navigationPage.getCurrentUrl());
        assertEquals("Navigation example", navigationPage.getTitle());
        assertEquals("Hands-On Selenium WebDriver with Java", navigationPage.getMainTitle());

        assertEquals(FIRST_PAGE_TEXT, navigationPage.getPageText());
        assertEquals(5, navigationPage.getButtons().size());
        Assertions.assertTrue(navigationPage.checkButtonIsActive("1"));
        navigationPage.checkButtonIsNotClickable("Previous");

        navigationPage.clickButton("2");
        Thread.sleep(timeoutFast);
        assertEquals(SECOND_PAGE_TEXT, navigationPage.getPageText());
        Assertions.assertTrue(navigationPage.checkButtonIsActive("2"));

        navigationPage.clickButton("3");
        Thread.sleep(timeoutFast);
        assertEquals(THIRD_PAGE_TEXT, navigationPage.getPageText());
        Assertions.assertTrue(navigationPage.checkButtonIsActive("3"));
        navigationPage.checkButtonIsNotClickable("Next");

        navigationPage.clickButton("1");
        Thread.sleep(timeoutFast);
        assertEquals(FIRST_PAGE_TEXT, navigationPage.getPageText());
        Assertions.assertTrue(navigationPage.checkButtonIsActive("1"));
        navigationPage.checkButtonIsNotClickable("Previous");

        navigationPage.clickButton("Next");
        Thread.sleep(timeoutFast);
        assertEquals(SECOND_PAGE_TEXT, navigationPage.getPageText());
        Assertions.assertTrue(navigationPage.checkButtonIsActive("2"));

        navigationPage.clickButton("Previous");
        Thread.sleep(timeoutFast);
        assertEquals(FIRST_PAGE_TEXT, navigationPage.getPageText());
        Assertions.assertTrue(navigationPage.checkButtonIsActive("1"));
        navigationPage.checkButtonIsNotClickable("Previous");

    }
}
