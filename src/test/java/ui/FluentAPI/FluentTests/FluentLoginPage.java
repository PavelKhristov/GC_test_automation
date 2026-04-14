package ui.FluentAPI.FluentTests;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FluentLoginPage extends FluentBasePage{

    @FindBy(id = "username")
    WebElement usernameInput;

    @FindBy(id = "password")
    WebElement passwordInput;

    @FindBy(css = "button")
    WebElement submitButton;

    @FindBy(id = "success")
    WebElement successBox;

    @FindBy(id = "invalid")
    WebElement invalidCredentialBox;

    public FluentLoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        visit("https://bonigarcia.dev/selenium-webdriver-java/login-form.html");
    }

    @Step("Login")
    public FluentLoginPage login (String username, String password){
        type(usernameInput, username);
        type(passwordInput, password);
        click(submitButton);
        return this;
    }

    @Step("Check success box present")
    public FluentLoginPage checksSccessBoxPresent (){
        assertThat(isDisplayed(successBox)).isTrue();
        return this;
    }

    @Step("Check success box in not present")
    public FluentLoginPage checksSccessBoxIsNotPresent (){
        assertThat(isDisplayed(successBox)).isFalse();
        return this;
    }

    @Step("Check invalid credentials box present")
    public FluentLoginPage checkInvalidCredentialsBoxPresent (){
        assertThat(isDisplayed(invalidCredentialBox)).isTrue();
        return this;
    }

    @Step("Check invalid credentials box is not present")
    public FluentLoginPage checkInvalidCredentialsBoxIsNotPresent (){
        assertThat(isDisplayed(invalidCredentialBox)).isFalse();
        return this;
    }
}
