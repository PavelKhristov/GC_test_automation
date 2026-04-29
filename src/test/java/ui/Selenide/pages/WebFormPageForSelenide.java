package ui.Selenide.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class WebFormPageForSelenide extends BasePageForSelenide {



    @Step("Get Title")
    public String getTitle() {
        return $(By.className("display-6")).getText();
    }

    @Step("Validate Title Name")
    public SelenideElement getReadonlyInput() {
        return $(By.name("my-readonly"));
    }



}
