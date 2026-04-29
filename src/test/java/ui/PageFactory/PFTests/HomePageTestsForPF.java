package ui.PageFactory.PFTests;


import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ui.POM.POMTests.BaseTestForPOM;
import ui.POM.pages.HomePage;
import ui.PageFactory.pages.HomePageForPF;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Feature("POM")
class HomePageTestsForPF extends BaseTestForPF {


    @DisplayName("Проверка домашней страницы")
    @Test
    void openHomePageTest (){
        String actualTitle = homePage.getMainTitle();
        assertEquals(homePage.getBaseUrl(), homePage.getCurrentUrl());
        assertEquals("Hands-On Selenium WebDriver with Java", actualTitle);
    };

    @DisplayName("Проверка header")
    @Test
    void headerTest (){
        String headerSubtitleText = homePage.getHeader().getSubtitleText();
        String headerText = homePage.getHeader().getTitleText();
        assertEquals("Practice site", headerSubtitleText);
        assertEquals("Hands-On Selenium WebDriver with Java", headerText);
    };

}
