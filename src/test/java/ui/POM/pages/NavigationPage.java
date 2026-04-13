package ui.POM.pages;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class NavigationPage extends BasePage{
    public NavigationPage(WebDriver driver) {
        super(driver);
    }


    //locators
    By title = By.className("display-6");

    By pageText = By.className("lead");

    By buttons = By.xpath("//li[contains(@class, 'page-item')]");

    //actions


    @Step("Get subpage title")
    public String getTitle() {
        return driver.findElement(title).getText();
    }

    @Step("Get page text")
    public String getPageText() {
        return driver.findElement(pageText).getText();
    }
    @Step("Get buttons")
    public List<WebElement> getButtons() {
        return driver.findElements(buttons);
    }

    @Step("Get property=active")
    public boolean checkButtonIsActive(String buttonName) {
        return driver.findElement(By.xpath("//a[text()='" + buttonName + "']/..")).getAttribute("class").contains("active");
    }
    @Step("Validate that button is not clickable")
    public void checkButtonIsNotClickable(String buttonName) {
        Assertions.assertThrows(ElementNotInteractableException.class, () -> driver.findElement(By.linkText(buttonName)).click());
    }
    @Step("Click button")
    public void clickButton(String buttonName) {
        driver.findElement(By.linkText(buttonName)).click();
    }



    //method -> open another page objects
    public HomePage openHomePage(){
        driver.findElement(By.linkText("Back to index")).click();
        return new HomePage(driver);
    }


}
