package ui.Selenide.pages;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;

import static com.codeborne.selenide.WebDriverRunner.url;

public class BasePageForSelenide {



    @Step("Get current url")
    public String getCurrentUrl() {
        return url();
    }

    @Step("Get main title")
    public String getMainTitle() {
        return Selenide.title();
    }
}
