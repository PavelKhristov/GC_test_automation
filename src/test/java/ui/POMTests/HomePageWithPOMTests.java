package ui.POMTests;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ui.pages.HomePage;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HomePageWithPOMTests extends BaseTestForPOM {


    @DisplayName("Проверка домашней страницы")
    @Test
    void openHomePageTest (){
        HomePage homePage = new HomePage(driver);
        homePage.open();

        String actualTitle = homePage.getMainTitle();
        assertEquals(baseUrl, homePage.getCurrentUrl());
        assertEquals("Hands-On Selenium WebDriver with Java", actualTitle);
    };

}
