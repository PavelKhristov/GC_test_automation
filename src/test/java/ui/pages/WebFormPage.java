package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WebFormPage extends BasePage{

    public WebFormPage(WebDriver driver) {
        super(driver);
    }

    private static final String WEB_FORM_URL = "web-form.html";

    //locators
    By title = By.className("display-6");

    //actions


    public String getUrl() {
        return WEB_FORM_URL;
    }

    public String getTitle() {
        return driver.findElement(title).getText();
    }

    //method -> open another page objects
    public HomePage openHomePage(){
        driver.findElement(By.linkText("Return to index")).click();
        return new HomePage(driver);
    }

}
