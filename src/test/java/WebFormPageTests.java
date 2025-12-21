import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class WebFormPageTests {

    WebDriver driver;
    private static final String BASE_URL = "https://bonigarcia.dev/selenium-webdriver-java/";

    @BeforeEach
    void setup(){
        driver = new ChromeDriver();
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

    @DisplayName("Поиск полей")
    @Test
    void TextInputTest () throws InterruptedException {
        driver.findElement(By.xpath("//a[@class = 'btn btn-outline-primary mb-2' and text() = 'Web form']")).click();
        driver.findElement(By.xpath("//label[contains(text(), 'Text input')]/input")).sendKeys("123");
        driver.findElement(By.xpath("//label[normalize-space(text())='Password']/input")).sendKeys("12345");
        driver.findElement(By.xpath("//label[normalize-space(text())='Textarea']/textarea")).sendKeys("text");

        driver.findElement(By.xpath("//label[normalize-space(text())='Readonly input']/input"));
        driver.findElement(By.xpath("//label[normalize-space(text())='Dropdown (datalist)']/input"));

        driver.findElement(By.xpath("//label[normalize-space(.)='Checked checkbox']/input")).click();
        driver.findElement(By.xpath("//label[normalize-space(.)='Default checkbox']/input")).click();
        driver.findElement(By.xpath("//label[normalize-space(.)='Checked radio']/input")).click();
        driver.findElement(By.xpath("//label[normalize-space(.)='Default radio']/input")).click();
        driver.findElement(By.xpath("//a[contains(text(), 'Return to index')]")).click();
        Thread.sleep(2000);
        driver.navigate().back();
        driver.findElement(By.xpath("//label[normalize-space(.)='Color picker']/input"));
        driver.findElement(By.xpath("//label[normalize-space(.)='Date picker']/input"));
        driver.findElement(By.xpath("//label[normalize-space(.)='Example range']/input"));


    }

//    @DisplayName("Тест ...")
//    @Test
//    void ...Test (){
//        driver.findElement(By.xpath("//a[@class = 'btn btn-outline-primary mb-2' and text() = 'Web form']")).click();
//    }

    @DisplayName("Тест Disabled input")
    @Test
    void DisabledInputTest (){
        driver.findElement(By.xpath("//a[@class = 'btn btn-outline-primary mb-2' and text() = 'Web form']")).click();
        WebElement disabledInput = driver.findElement(By.xpath("//label[normalize-space(text())='Disabled input']/input"));

        assertFalse(disabledInput.isEnabled());
        assertEquals("Disabled input", disabledInput.findElement(By.xpath("..")).getText());
        assertEquals("rgba(233, 236, 239, 1)", disabledInput.getCssValue("background-color"));
        assertEquals("Disabled input", disabledInput.getDomAttribute("placeholder"));

        //проверяем что возвращается ошибка при попытке вбить текст:
        Assertions.assertThrows(ElementNotInteractableException.class, () -> disabledInput.sendKeys("test"));
    }


    @DisplayName("Тест Dropdown (select)")
    @Test
    void DropdownSelectTest () throws InterruptedException {
        driver.findElement(By.xpath("//a[@class = 'btn btn-outline-primary mb-2' and text() = 'Web form']")).click();
        WebElement selector = driver.findElement(By.xpath("//label[normalize-space(text())='Dropdown (select)']/select"));
        Select select = new Select(selector);
        List<WebElement> actualOptions = select.getOptions();
//        List<String> expectedOptions = List.of("Open this select menu", "One", "Two", "Three");
        assertEquals(4, actualOptions.size());
//        assertEquals(expectedOptions, actualOptions);

        assertEquals("Open this select menu", select.getFirstSelectedOption().getText());
        select.selectByVisibleText("One");
        assertEquals("One", select.getFirstSelectedOption().getText());
        Thread.sleep(1000);
        select.selectByValue("2");
        assertEquals("Two", select.getFirstSelectedOption().getText());
        Thread.sleep(1000);
        select.selectByIndex(3);
        assertEquals("Three", select.getFirstSelectedOption().getText());
        Thread.sleep(1000);
        select.selectByVisibleText("Open this select menu");
        assertEquals("Open this select menu", select.getFirstSelectedOption().getText());
        Thread.sleep(1000);
    }

    @DisplayName("Тест File input")
    @Test
    void FileInputTest () throws IOException, InterruptedException {
        String filePath = "src/test/resources/file.txt";
        // Чтение содержимого файла в виде строки
        String content = new String(Files.readAllBytes(Paths.get(filePath)));
        // Используйте содержимое файла в вашем коде, например, вывод на экран
        System.out.println("Содержимое файла: " + content);
        // Получаем URL ресурса
        URL url = WebFormPageTests.class.getClassLoader().getResource("file.txt");
        System.out.println("URL: " + url);
        // Получаем абсолютный путь к файлу
        String absolutePath = null;
        if (url != null) {
            absolutePath = new File(url.getPath()).getAbsolutePath();
            System.out.println("Абсолютный путь к файлу: " + absolutePath);
        } else {
            System.out.println("Ресурс не найден.");
        }
        // Получаем относительный путь к файлу
        String selectFile = System.getProperty("user.dir") + "/src/test/resources/file.txt";
        System.out.println("Относительный путь к файлу: " + selectFile);

        driver.findElement(By.xpath("//a[@class = 'btn btn-outline-primary mb-2' and text() = 'Web form']")).click();
        WebElement fileInput = driver.findElement(By.xpath("//input[@name='my-file']"));
        assertEquals("File input", fileInput.findElement(By.xpath("..")).getText());
        fileInput.sendKeys(selectFile);
        Thread.sleep(1000);

    }

    @DisplayName("Тест Submit кнопки")
    @Test
    void SubmitFormTest () throws InterruptedException {
        driver.findElement(By.xpath("//a[@class = 'btn btn-outline-primary mb-2' and text() = 'Web form']")).click();
        //1й вариант сабмита
        driver.findElement(By.xpath("//button[text()='Submit']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//h1[text()='Form submitted']"));
        driver.findElement(By.xpath("//p[text()='Received!']"));
        Thread.sleep(1000);
        driver.navigate().back();
        Thread.sleep(1000);
        //2й вариант сабмита
        driver.findElement(By.xpath("//form")).submit();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//h1[text()='Form submitted']"));
        driver.findElement(By.xpath("//p[text()='Received!']"));
        Thread.sleep(1000);
        driver.navigate().back();
        Thread.sleep(1000);

    }


}
