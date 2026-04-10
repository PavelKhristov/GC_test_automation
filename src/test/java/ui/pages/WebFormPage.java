package ui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WebFormPage extends BasePage{

    public WebFormPage(WebDriver driver) {
        super(driver);
    }

    private static final String WEB_FORM_URL = "web-form.html";

    //locators
    By title = By.className("display-6");

    By pageText = By.className("lead");

    WebElement submitButton = driver.findElement(By.xpath("//button[text()='Submit']"));

    //actions

    @Step("Get subpage url")
    public String getUrl() {
        return WEB_FORM_URL;
    }

    @Step("Get subpage title")
    public String getTitle() {
        return driver.findElement(title).getText();
    }
    @Step("Get page text")
    public String getPageText() {
        return driver.findElement(pageText).getText();
    }
    @Step("Submit page")
    public void submit() {
        submitButton.click();
    }





    //method -> open another page objects
    public HomePage openHomePage(){
        driver.findElement(By.linkText("Return to index")).click();
        return new HomePage(driver);
    }


}
