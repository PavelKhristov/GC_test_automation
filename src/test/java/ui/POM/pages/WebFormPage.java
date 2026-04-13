package ui.POM.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static constants.Constants.*;

public class WebFormPage extends BasePage{

    public WebFormPage(WebDriver driver) {
        super(driver);
    }

//    private static final String WEB_FORM_URL = "web-form.html";

    //locators
    By title = By.className("display-6");

    By pageText = By.className("lead");

    WebElement submitButton = driver.findElement(By.xpath("//button[text()='Submit']"));

    //actions

    @Step("Get subpage title")
    private String getTitle() {
        return driver.findElement(title).getText();
    }

    @Step("Get page text")
    private String getPageText() {
        return driver.findElement(pageText).getText();
    }

    @Step("Submit page")
    public void submit() {
        submitButton.click();
    }

    @Step("Validate current URL")
    public void validateCurrentURL() {
        assertEquals(baseUrl + WEB_FORM_URL, getCurrentUrl());
    }

    @Step("Validate Title Name")
    public void validateTitleName(String titleName) {
        assertEquals(titleName, getTitle());
    }
    @Step("Validate Page Text")
    public void validatePageText(String pageText) {
        assertEquals(pageText, getPageText());
    }
    @Step("Validate Main Title Name")
    public void validateMainTitleName() {
        assertEquals("Hands-On Selenium WebDriver with Java", getMainTitle());
    }





    //method -> open another page objects
    public HomePage openHomePage(){
        driver.findElement(By.linkText("Return to index")).click();
        return new HomePage(driver);
    }


}
