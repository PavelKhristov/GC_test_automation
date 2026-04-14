package ui.PageFactory.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePageForPF extends BasePageForPF {

    public HomePageForPF(WebDriver driver) {
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
    public WebFormPageForPF openWebformPage(){
        driver.findElement(By.linkText("Web form")).click();
        return new WebFormPageForPF(driver);
    }
    @Step("Open Navigation page")
    public NavigationPageForPF openNavigationPage(){
        driver.findElement(By.linkText("Navigation")).click();
        return new NavigationPageForPF(driver);
    }

}
