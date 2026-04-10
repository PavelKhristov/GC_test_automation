package ui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage{

    public HomePage(WebDriver driver) {
        super(driver);
        open();
    }

    //locators

    //actions
    @Step("Open homepage")
    private void open() {
        driver.get(baseUrl);
    }





    //method -> open another page objects
    @Step("Open WebForm page")
    public WebFormPage openWebformPage(){
        driver.findElement(By.linkText("Web form")).click();
        return new WebFormPage(driver);
    }
    @Step("Open Navigation page")
    public NavigationPage openNavigationPage(){
        driver.findElement(By.linkText("Navigation")).click();
        return new NavigationPage(driver);
    }

}
