package hw4;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class hw4Tests {
    WebDriver driver;
    private static final String BASE_URL = "https://bonigarcia.dev/selenium-webdriver-java/";

    @BeforeAll
    void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterAll
    void tearDown() {
        driver.quit();
    }


    private void openLinks(String link, String chapterName, String expecteedtitle) {
        driver.get(BASE_URL);
        String webFormUrl = link;
        driver.findElement(By.xpath("//h5[text() = '" + chapterName + "']/../a[@href = '" + link + "']")).click();
        String currentUrl = driver.getCurrentUrl();
        WebElement title = driver.findElement(By.className("display-6"));

        assertEquals(BASE_URL + webFormUrl, currentUrl);
        assertEquals(expecteedtitle, title.getText());
    }

    @Test
    @Order(1)
    void openWebFormTest() {
        openLinks("web-form.html", "Chapter 3. WebDriver Fundamentals", "Web form");
    }

    @Test
    @Order(2)
    void openNavigationTest() {
        openLinks("navigation1.html", "Chapter 3. WebDriver Fundamentals", "Navigation example");
    }

    @Test
    @Order(3)
    void openDropdownMenuTest() {
        openLinks("dropdown-menu.html", "Chapter 3. WebDriver Fundamentals", "Dropdown menu");
    }

    @Test
    @Order(4)
    void openMouseOverTest() {
        openLinks("mouse-over.html", "Chapter 3. WebDriver Fundamentals", "Mouse over");
    }

    @Test
    @Order(5)
    void openDragAndDropTest() {
        openLinks("drag-and-drop.html", "Chapter 3. WebDriver Fundamentals", "Drag and drop");
    }

    @Test
    @Order(6)
    void openDrawingInCanvasTest() {
        openLinks("draw-in-canvas.html", "Chapter 3. WebDriver Fundamentals", "Drawing in canvas");
    }

    @Test
    @Order(7)
    void openLoadingImagesTest() {
        openLinks("loading-images.html", "Chapter 3. WebDriver Fundamentals", "Loading images");
    }

    @Test
    @Order(8)
    void openSlowCalculatorTest() {
        openLinks("slow-calculator.html", "Chapter 3. WebDriver Fundamentals", "Slow calculator");
    }

    @Test
    @Order(9)
    void openLongPageTest() {
        openLinks("long-page.html", "Chapter 4. Browser-Agnostic Features", "This is a long page");
    }

    @Test
    @Order(10)
    void openInfiniteScrollTest() {
        openLinks("infinite-scroll.html", "Chapter 4. Browser-Agnostic Features", "Infinite scroll");
    }

    @Test
    @Order(11)
    void openShadowDOMTest() {
        openLinks("shadow-dom.html", "Chapter 4. Browser-Agnostic Features", "Shadow DOM");
    }

    @Test
    @Order(12)
    void openCookiesTest() {
        openLinks("cookies.html", "Chapter 4. Browser-Agnostic Features", "Cookies");
    }

    @Test
    @Order(13)
    void openFramesTest() {
        openLinks("frames.html", "Chapter 4. Browser-Agnostic Features", "Frames");
    }

    @Test
    @Order(14)
    void openIFrameTest() {
        openLinks("iframes.html", "Chapter 4. Browser-Agnostic Features", "IFrame");
    }

    @Test
    @Order(15)
    void openDialogBoxesTest() {
        openLinks("dialog-boxes.html", "Chapter 4. Browser-Agnostic Features", "Dialog boxes");
    }

    @Test
    @Order(16)
    void openWebStorageTest() {
        openLinks("web-storage.html", "Chapter 4. Browser-Agnostic Features", "Web storage");
    }

    @Test
    @Order(17)
    void openGeolocationTest() {
        openLinks("geolocation.html", "Chapter 5. Browser-Specific Manipulation", "Geolocation");
    }

    @Test
    @Order(18)
    void openNotificationsTest() {
        openLinks("notifications.html", "Chapter 5. Browser-Specific Manipulation", "Notifications");
    }

    @Test
    @Order(19)
    void openGetUserMediaTest() {
        openLinks("get-user-media.html", "Chapter 5. Browser-Specific Manipulation", "Get user media");
    }

    @Test
    @Order(20)
    void openMultilanguageTest() {
        openLinks("multilanguage.html", "Chapter 5. Browser-Specific Manipulation", "");
    }

    @Test
    @Order(21)
    void openConsoleLogsTest() {
        openLinks("console-logs.html", "Chapter 5. Browser-Specific Manipulation", "Console logs");
    }

    @Test
    @Order(22)
    void openLoginFormTest() {
        openLinks("login-form.html", "Chapter 7. The Page Object Model (POM)", "Login form");
    }

    @Test
    @Order(23)
    void openSlowLoginFormTest() {
        openLinks("login-slow.html", "Chapter 7. The Page Object Model (POM)", "Slow login form");
    }

    @Test
    @Order(24)
    void openRandomCalculatorTest() {
        openLinks("random-calculator.html", "Chapter 8. Testing Framework Specifics", "Random calculator");
    }

    @Test
    @Order(25)
    void openDownloadFilesTest() {
        openLinks("download.html", "Chapter 9. Third-Party Integrations", "Download files");
    }

    @Test
    @Order(26)
    void openAbTestingTest() {
        openLinks("ab-testing.html", "Chapter 9. Third-Party Integrations", "A/B Testing");
    }

    @Test
    @Order(27)
    void openDataTypesTest() {
        openLinks("data-types.html", "Chapter 9. Third-Party Integrations", "Data types");
    }

}
