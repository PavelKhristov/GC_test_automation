import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

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

    @DisplayName("Тест Return to index")
    @Test
    void ReturnToIndexTest () throws InterruptedException {
        driver.findElement(By.xpath("//a[@class = 'btn btn-outline-primary mb-2' and text() = 'Web form']")).click();

        driver.findElement(By.xpath("//a[contains(text(), 'Return to index')]")).click();
        Thread.sleep(2000);
        driver.navigate().back();
        String currentURL = driver.getCurrentUrl();
        assertEquals("https://bonigarcia.dev/selenium-webdriver-java/web-form.html", currentURL);

    }

    @DisplayName("Тест Text Input")
    @Test
    void TextInputTest () throws InterruptedException {
        driver.findElement(By.xpath("//a[@class = 'btn btn-outline-primary mb-2' and text() = 'Web form']")).click();
        WebElement textInput = driver.findElement(By.xpath("//label[contains(text(), 'Text input')]/input"));
        textInput.sendKeys("text");
        Thread.sleep(1000);
        assertEquals("text", textInput.getDomProperty("value"));
        textInput.clear();
        Thread.sleep(1000);
        assertEquals("", textInput.getDomProperty("value"));
    }

    @DisplayName("Тест Password")
    @Test
    void PasswordTest () throws InterruptedException {
        driver.findElement(By.xpath("//a[@class = 'btn btn-outline-primary mb-2' and text() = 'Web form']")).click();
        WebElement password = driver.findElement(By.xpath("//label[normalize-space(text())='Password']/input"));
        password.sendKeys("pass");
        Thread.sleep(1000);
        assertEquals("pass", password.getDomProperty("value"));
    }

    @DisplayName("Тест Text Area")
    @Test
    void TextAreaTest () throws InterruptedException {
        driver.findElement(By.xpath("//a[@class = 'btn btn-outline-primary mb-2' and text() = 'Web form']")).click();
        WebElement textArea = driver.findElement(By.xpath("//label[normalize-space(text())='Textarea']/textarea"));
        textArea.sendKeys("text area");
        Thread.sleep(1000);
        assertEquals("text area", textArea.getDomProperty("value"));
    }

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

    @DisplayName("Тест Readonly input")
    @Test
    void ReadonlyInputTest () throws InterruptedException {
        driver.findElement(By.xpath("//a[@class = 'btn btn-outline-primary mb-2' and text() = 'Web form']")).click();
        WebElement readonlyInput = driver.findElement(By.xpath("//label[normalize-space(text())='Readonly input']/input"));


        assertEquals("rgba(233, 236, 239, 1)", readonlyInput.getCssValue("background-color"));
        assertEquals("Readonly input", readonlyInput.getDomAttribute("value"));
        //вводим значение и проверяем что значение не изенилось
        readonlyInput.sendKeys("test");
        Thread.sleep(2000);
        assertEquals("Readonly input", readonlyInput.getDomAttribute("value"));

        boolean isReadOnly = readonlyInput.getAttribute("readonly") != null;
        assertEquals(isReadOnly, true);
    }


    @DisplayName("Тест Dropdown (select)")
    @Test
    void DropdownSelectTest () throws InterruptedException {
        driver.findElement(By.xpath("//a[@class = 'btn btn-outline-primary mb-2' and text() = 'Web form']")).click();
        WebElement selector = driver.findElement(By.xpath("//label[normalize-space(text())='Dropdown (select)']/select"));
        Select select = new Select(selector);
        List<WebElement> actualOptions = select.getOptions();
        assertEquals(4, actualOptions.size());

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

    @DisplayName("Тест Dropdown (datalist)")
    @Test
    void DropdownDatalistTest () throws InterruptedException {
        driver.findElement(By.xpath("//a[@class = 'btn btn-outline-primary mb-2' and text() = 'Web form']")).click();
        WebElement dataList = driver.findElement(By.xpath("//label[normalize-space(text())='Dropdown (datalist)']/input"));

        assertEquals("Type to search...", dataList.getDomAttribute("placeholder"));
        dataList.sendKeys("CustomValue");
        Thread.sleep(1000);
        assertEquals("CustomValue", dataList.getDomProperty("value"));
        dataList.clear();

        List<WebElement> dataListOptions = driver.findElements(By.xpath("//datalist/option"));
        assertEquals(5, dataListOptions.size());
        for (WebElement option : dataListOptions) {
            dataList.clear();
            String optionValue = option.getAttribute("value");
            dataList.sendKeys(optionValue);
            assertEquals(optionValue, dataList.getDomProperty("value"));
            Thread.sleep(2000);
        }
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
        WebElement form = driver.findElement(By.xpath("//form"));
        form.submit();
        assertThat(driver.getCurrentUrl()).contains("file.txt");
    }

    @DisplayName("Тест Checkboxes")
    @Test
    void CheckboxesTest () throws InterruptedException {
        driver.findElement(By.xpath("//a[@class = 'btn btn-outline-primary mb-2' and text() = 'Web form']")).click();

        WebElement checkedCheckbox = driver.findElement(By.xpath("//label[normalize-space(.)='Checked checkbox']/input"));
        WebElement defauleChechbox = driver.findElement(By.xpath("//label[normalize-space(.)='Default checkbox']/input"));
        boolean checkedCheckboxState1 = checkedCheckbox.getAttribute("checked") != null;
        boolean defauleChechboxState1 = defauleChechbox.getAttribute("checked") != null;
        assertEquals(checkedCheckboxState1, true);
        assertEquals(defauleChechboxState1, false);
        checkedCheckbox.click();
        Thread.sleep(1000);
        boolean checkedCheckboxState2 = checkedCheckbox.getAttribute("checked") != null;
        boolean defauleChechboxState2 = defauleChechbox.getAttribute("checked") != null;
        assertEquals(checkedCheckboxState2, false);
        assertEquals(defauleChechboxState2, false);
        defauleChechbox.click();
        Thread.sleep(1000);
        boolean checkedCheckboxState3 = checkedCheckbox.getAttribute("checked") != null;
        boolean defauleChechboxState3 = defauleChechbox.getAttribute("checked") != null;
        assertEquals(checkedCheckboxState3, false);
        assertEquals(defauleChechboxState3, true);
        Thread.sleep(1000);
    }

    @DisplayName("Тест Radiobuttons")
    @Test
    void RadiobuttonsTest () throws InterruptedException {
        driver.findElement(By.xpath("//a[@class = 'btn btn-outline-primary mb-2' and text() = 'Web form']")).click();

        WebElement checkedRaiobutton = driver.findElement(By.xpath("//label[normalize-space(.)='Checked radio']/input"));
        WebElement defaultRaiobutton = driver.findElement(By.xpath("//label[normalize-space(.)='Default radio']/input"));
        boolean checkedRaiobuttonState1 = checkedRaiobutton.getAttribute("checked") != null;
        boolean defaultRaiobuttonState1 = defaultRaiobutton.getAttribute("checked") != null;
        assertEquals(checkedRaiobuttonState1, true);
        assertEquals(defaultRaiobuttonState1, false);
        defaultRaiobutton.click();
        Thread.sleep(1000);
        boolean checkedRaiobuttonState2 = checkedRaiobutton.getAttribute("checked") != null;
        boolean defaultRaiobuttonState2 = defaultRaiobutton.getAttribute("checked") != null;
        assertEquals(checkedRaiobuttonState2, false);
        assertEquals(defaultRaiobuttonState2, true);
        checkedRaiobutton.click();
        Thread.sleep(1000);
        boolean checkedRaiobuttonState3 = checkedRaiobutton.getAttribute("checked") != null;
        boolean defaultRaiobuttonState3 = defaultRaiobutton.getAttribute("checked") != null;
        assertEquals(checkedRaiobuttonState3, true);
        assertEquals(defaultRaiobuttonState3, false);
        Thread.sleep(1000);
    }



    @DisplayName("Тест Submit кнопки")
    @Test
    void SubmitFormTest () throws InterruptedException {
        driver.findElement(By.xpath("//a[@class = 'btn btn-outline-primary mb-2' and text() = 'Web form']")).click();
        //1й вариант сабмита: по клику
        driver.findElement(By.xpath("//button[text()='Submit']")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//h1[text()='Form submitted']"));
        driver.findElement(By.xpath("//p[text()='Received!']"));
        Thread.sleep(1000);
        driver.navigate().back();
        Thread.sleep(1000);
        //2й вариант сабмита: по сабмиту
        driver.findElement(By.xpath("//form")).submit();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//h1[text()='Form submitted']"));
        driver.findElement(By.xpath("//p[text()='Received!']"));
        Thread.sleep(1000);
        driver.navigate().back();
        Thread.sleep(1000);
    }


    @DisplayName("Тест Color Picker")
    @Test
    void ColorPickerTest () throws InterruptedException {
        driver.findElement(By.xpath("//a[@class = 'btn btn-outline-primary mb-2' and text() = 'Web form']")).click();
        WebElement colorPicker = driver.findElement(By.xpath("//label[normalize-space(.)='Color picker']/input"));



//        Вариант с JS
        JavascriptExecutor js = (JavascriptExecutor) driver;

        String initColor = colorPicker.getAttribute("value");
        System.out.println("The initial color is " + initColor);

        Color red = new Color(255, 0, 0, 1);
        String script = String.format("arguments[0].setAttribute('value', '%s');", red.asHex());
        Thread.sleep(3000);
        js.executeScript(script, colorPicker);

        String finalColor = colorPicker.getAttribute("value");
        System.out.println("The final color is " + finalColor);
        assertThat(finalColor).isNotEqualTo(initColor);
        assertThat(Color.fromString(finalColor)).isEqualTo(red);
        Thread.sleep(3000);

//        Вариант с sendkeys
        Thread.sleep(1000);
        colorPicker.sendKeys("#00ff00");
        Thread.sleep(1000);
        assertEquals("#00ff00", colorPicker.getDomProperty("value"));
        Thread.sleep(1000);


    }

    @DisplayName("Тест Date Picker")
    @Test
    void DatePickerTest () throws InterruptedException {
        driver.findElement(By.xpath("//a[@class = 'btn btn-outline-primary mb-2' and text() = 'Web form']")).click();
        WebElement datePickerInput = driver.findElement(By.xpath("//label[normalize-space(.)='Date picker']/input"));
        datePickerInput.click();
        WebElement datePickerCalendar = driver.findElement(By.xpath("//div[@class = 'datepicker datepicker-dropdown dropdown-menu datepicker-orient-left datepicker-orient-top']"));;
    }

    @DisplayName("Тест Example Range")
    @Test
    void ExampleRangeTest () throws InterruptedException {
        driver.findElement(By.xpath("//a[@class = 'btn btn-outline-primary mb-2' and text() = 'Web form']")).click();
        WebElement exampleRange = driver.findElement(By.xpath("//label[normalize-space(.)='Example range']/input"));
        Thread.sleep(1000);
        assertEquals("5", exampleRange.getDomProperty("value"));
    }


}
