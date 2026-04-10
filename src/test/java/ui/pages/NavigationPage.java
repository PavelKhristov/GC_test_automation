package ui.pages;

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
    private static final String firstPageText =
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.";
    private static final String secondPageText =
            "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.";
    private static final String thirdPageText =
            "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
    private static final String NAVIGATION_PAGE_URL = "navigation1.html";

    //locators
    By title = By.className("display-6");

    By pageText = By.className("lead");

    By buttons = By.xpath("//li[contains(@class, 'page-item')]");

    //actions
    public String FirstPageText() {
        return firstPageText;
    }

    public String SecondPageText() {
        return secondPageText;
    }

    public String ThirdPageText() {
        return thirdPageText;
    }

    public String getUrl() {
        return NAVIGATION_PAGE_URL;
    }

    public String getTitle() {
        return driver.findElement(title).getText();
    }

    public String getPageText() {
        return driver.findElement(pageText).getText();
    }

    public List<WebElement> getButtons() {
        return driver.findElements(buttons);
    }

    public boolean checkButtonIsActive(String buttonName) {
        return driver.findElement(By.xpath("//a[text()='" + buttonName + "']/..")).getAttribute("class").contains("active");
    }

    public void checkButtonIsNotClickable(String buttonName) {
        Assertions.assertThrows(ElementNotInteractableException.class, () -> driver.findElement(By.linkText(buttonName)).click());
    }

    public void clickButton(String buttonName) {
        driver.findElement(By.linkText(buttonName)).click();
    }



    //method -> open another page objects
    public HomePage openHomePage(){
        driver.findElement(By.linkText("Back to index")).click();
        return new HomePage(driver);
    }


}
