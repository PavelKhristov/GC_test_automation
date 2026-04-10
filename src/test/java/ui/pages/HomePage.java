package ui.pages;

import configs.TestPropertiesConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage{

    public HomePage(WebDriver driver) {
        super(driver);
        open();
    }

    //locators

    //actions
    private void open() {
        driver.get(baseUrl);
    }






    //method -> open another page objects
    public WebFormPage openWebformPage(){
        driver.findElement(By.linkText("Web form")).click();
        return new WebFormPage(driver);
    }

    public NavigationPage openNavigationPage(){
        driver.findElement(By.linkText("Navigation")).click();
        return new NavigationPage(driver);
    }

}
