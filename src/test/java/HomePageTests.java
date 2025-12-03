import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HomePageTests {
    WebDriver driver;
    private static final String BASE_URL = "https://bonigarcia.dev/selenium-webdriver-java/";

    @BeforeEach
    void setup(){
        driver = new ChromeDriver ();
        driver.get(BASE_URL);
        driver.manage().window().maximize();
    };

    @AfterEach
    void tearDown (){
        driver.quit();
    };
    @DisplayName("Проверка домашней страницы")
    @Test
    void openHomePageTest (){
        String actualTitle = driver.getTitle();
        assertEquals("Hands-On Selenium WebDriver with Java", actualTitle);
    };

    @DisplayName("Проверка открытия Web form")
    @Test
    void webFormTest (){
        openLinkInMainPageByXPath("//h5[text() = 'Chapter 3. WebDriver Fundamentals']/../a[contains(@href, 'web-form')]", "web-form.html", "Web form");
    }

    @DisplayName("Проверка открытия Navigation")
    @Test
    void navigationTest (){
        openLinkInMainPageByXPath("//h5[text() = 'Chapter 3. WebDriver Fundamentals']/../a[contains(@href, 'navigation1')]", "navigation1.html", "Navigation example");
    }

    @DisplayName("Проверка открытия Dropdown menu")
    @Test
    void dropdownMenuTest (){
        openLinkInMainPageByXPath("//h5[text() = 'Chapter 3. WebDriver Fundamentals']/../a[contains(@href, 'dropdown-menu')]", "dropdown-menu.html", "Dropdown menu");
    }

    @DisplayName("Проверка открытия Mouse over")
    @Test
    void mouseOverTest (){
        openLinkInMainPageByXPath("//h5[text() = 'Chapter 3. WebDriver Fundamentals']/../a[contains(@href, 'mouse-over')]", "mouse-over.html", "Mouse over");
    }

    @DisplayName("Проверка открытия Drag and drop")
    @Test
    void dragAndDropTest (){
        openLinkInMainPageByXPath("//h5[text() = 'Chapter 3. WebDriver Fundamentals']/../a[contains(@href, 'drag-and-drop')]", "drag-and-drop.html", "Drag and drop");
    }

    @DisplayName("Проверка открытия Draw in canvas")
    @Test
    void drawInCanvasTest (){
        openLinkInMainPageByXPath("//h5[text() = 'Chapter 3. WebDriver Fundamentals']/../a[contains(@href, 'draw-in-canvas')]", "draw-in-canvas.html", "Drawing in canvas");
    }

    //дальше проверяю по одной странице для каждого следующего раздела
    @DisplayName("Проверка открытия Long page")
    @Test
    void longPageTest (){
        openLinkInMainPageByXPath("//h5[text() = 'Chapter 4. Browser-Agnostic Features']/../a[contains(@href, 'long-page')]", "long-page.html", "This is a long page");
    }

    @DisplayName("Проверка открытия Geolocation")
    @Test
    void geolocationTest (){
        openLinkInMainPageByXPath("//h5[text() = 'Chapter 5. Browser-Specific Manipulation']/../a[contains(@href, 'geolocation')]", "geolocation.html", "Geolocation");
    }

    @DisplayName("Проверка открытия Login form")
    @Test
    void loginFormTest (){
        openLinkInMainPageByXPath("//h5[text() = 'Chapter 7. The Page Object Model (POM)']/../a[contains(@href, 'login-form')]", "login-form.html", "Login form");
    }

    @DisplayName("Проверка открытия Random calculator")
    @Test
    void randomCalculatorTest (){
        openLinkInMainPageByXPath("//h5[text() = 'Chapter 8. Testing Framework Specifics']/../a[contains(@href, 'random-calculator')]", "random-calculator.html", "Random calculator");
    }

    @DisplayName("Проверка открытия Download files")
    @Test
    void downloadFilesTest (){
        openLinkInMainPageByXPath("//h5[text() = 'Chapter 9. Third-Party Integrations']/../a[contains(@href, 'download')]", "download.html", "Download files");
    }

    private void openLinkInMainPageByXPath(String link, String url, String title) {
//        driver.findElement(By.linkText("Web form")).click();
//        ниже пример xpath, в котором мы находим строку по сочитанию нескольких значений
//        driver.findElement(By.xpath("//a[@class = 'btn btn-outline-primary mb-2' and @href = 'web-form.html' and text() = 'Web form']")).click();

//        ниже пример более сложного xpath, мы находим h5 с нужным текстом,
//        поднимаемся на уровень вверх и ищем a который имеет ссылку, содержащую определенный текст
        driver.findElement(By.xpath(link)).click();
        WebElement actualTitle = driver.findElement(By.className("display-6"));

        assertEquals(BASE_URL + url, driver.getCurrentUrl());
        assertEquals(title, actualTitle.getText());
    }

    ;

}
