package ui.Selenide.pages;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;
import static constants.Constants.BASE_URL;

public class HomePageForSelenide extends BasePageForSelenide {

    @Step("Open homepage")
    public void open() {
        Selenide.open(BASE_URL, HomePageForSelenide.class);
    }

    @Step("Open WebForm page")
    public WebFormPageForSelenide openWebformPage(){
        $(By.linkText("Web form")).click();
        return page(WebFormPageForSelenide.class);
    }

}
