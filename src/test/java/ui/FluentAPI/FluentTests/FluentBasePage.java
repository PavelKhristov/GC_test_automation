package ui.FluentAPI.FluentTests;

import io.qameta.allure.Step;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;

import java.time.Duration;

import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;

public class FluentBasePage {
    static final Logger log = getLogger(lookup().lookupClass());

    WebDriver driver;
    WebDriverWait wait;
    int timeoutSec = 5;

    @FindBy(className = "display-6")
    private WebElement title;

    public FluentBasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSec));
        PageFactory.initElements(driver, this);
    }

    @Step("Getting current url")
    public String getCurrentUrl(){return driver.getCurrentUrl();}

    @Step("Getting subpage title")
    public String getTitle(){return title.getText();}

    public void visit(String url){driver.get(url);}

    public void click(WebElement element){element.click();}

    public void type(WebElement element, String text){element.sendKeys(text);}

    public boolean isDisplayed(WebElement element){
        return isDisplayed(ExpectedConditions.visibilityOf(element));
    }

    public boolean isDisplayed(ExpectedCondition<?> expectedCondition){
        try {
            wait.until(expectedCondition);
        } catch (TimeoutException e) {
            log.warn("Timeout of {} wait for element " , timeoutSec);
            return false;
        }
        return true;
    }
}
