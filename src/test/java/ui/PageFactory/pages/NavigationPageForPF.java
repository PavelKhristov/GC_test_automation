package ui.PageFactory.pages;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ui.POM.pages.BasePage;
import ui.POM.pages.HomePage;

import java.util.List;

public class NavigationPageForPF extends BasePageForPF {

    public NavigationPageForPF(WebDriver driver) {
        super(driver);
//        PageFactory.initElements(driver, this);
    }


    //locators
    @FindBy (className = "display-6")
    private WebElement title;

    @FindBy (className = "lead")
    private WebElement pageText;

    @FindBy (xpath = "//li[contains(@class, 'page-item')]")
    private List<WebElement> buttons;

    @FindBy (linkText = "Next")
    private WebElement nextButton;


    //actions


    @Step("Get subpage title")
    public String getTitle() {
        return title.getText();
    }

    @Step("Get page text")
    public String getPageText() {
        return pageText.getText();
    }

    @Step("Get buttons")
    public List<WebElement> getButtons() {
        return buttons;
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

    @Step("Click Next button")
    public void clickNextButton() {
        nextButton.click();
    }



    //method -> open another page objects
    public HomePage openHomePage(){
        driver.findElement(By.linkText("Back to index")).click();
        return new HomePage(driver);
    }


}
