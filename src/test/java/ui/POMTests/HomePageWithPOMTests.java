package ui.POMTests;


import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ui.pages.HomePage;

import static org.junit.jupiter.api.Assertions.assertEquals;
@Feature("POM")
class HomePageWithPOMTests extends BaseTestForPOM {


    @DisplayName("Проверка домашней страницы")
    @Test
    void openHomePageTest (){
        homePage = new HomePage(driver);

        String actualTitle = homePage.getMainTitle();
        assertEquals(homePage.getBaseUrl(), homePage.getCurrentUrl());
        assertEquals("Hands-On Selenium WebDriver with Java", actualTitle);
    };

}
