import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class WebFormPageTests {

    WebDriver driver;
    Actions actions;
    private static final String BASE_URL = "https://bonigarcia.dev/selenium-webdriver-java/";
    private static final Path TXT_FILE_Path = Paths.get("src/test/resources/file.txt");



    @BeforeEach
    void setup(){
        driver = new ChromeDriver();
        driver.get(BASE_URL);
        driver.manage().window().maximize();
        actions = new Actions(driver);
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
        assertEquals("text", textInput.getDomProperty("value"), "Неверный текст!");
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
    void TextAreaTest () throws InterruptedException, IOException {
        driver.findElement(By.xpath("//a[@class = 'btn btn-outline-primary mb-2' and text() = 'Web form']")).click();
        WebElement textArea = driver.findElement(By.xpath("//label[normalize-space(text())='Textarea']/textarea"));
        String expectedText = Files.readString(TXT_FILE_Path);

        textArea.sendKeys(expectedText);
        Thread.sleep(1000);
        String actualText = textArea.getAttribute("value");
        assertEquals(expectedText, actualText);
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
        assertTrue(isReadOnly);
        //2й вариант асерта
        assertEquals(true, isReadOnly);
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
        // Чтение содержимого файла в виде строки
        String content = new String(Files.readAllBytes(TXT_FILE_Path));
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
        Thread.sleep(1000);
        assertEquals("Form submitted", driver.findElement(By.className("display-6")).getText());
    }

    @DisplayName("Тест Checkboxes")
    @Test
    void CheckboxesTest () throws InterruptedException {
        driver.findElement(By.xpath("//a[@class = 'btn btn-outline-primary mb-2' and text() = 'Web form']")).click();

        WebElement checkedCheckbox = driver.findElement(By.xpath("//label[normalize-space(.)='Checked checkbox']/input"));
        WebElement defauleChechbox = driver.findElement(By.xpath("//label[normalize-space(.)='Default checkbox']/input"));
        assertTrue(checkedCheckbox.isSelected());
        assertFalse(defauleChechbox.isSelected());
        checkedCheckbox.click();
        Thread.sleep(1000);
        assertFalse(checkedCheckbox.isSelected());
        assertFalse(defauleChechbox.isSelected());
        defauleChechbox.click();
        Thread.sleep(1000);
        assertFalse(checkedCheckbox.isSelected());
        assertTrue(defauleChechbox.isSelected());
        Thread.sleep(1000);
    }

    @DisplayName("Тест Radiobuttons")
    @Test
    void RadiobuttonsTest () throws InterruptedException {
        driver.findElement(By.xpath("//a[@class = 'btn btn-outline-primary mb-2' and text() = 'Web form']")).click();

        WebElement checkedRaiobutton = driver.findElement(By.xpath("//label[normalize-space(.)='Checked radio']/input"));
        WebElement defaultRaiobutton = driver.findElement(By.xpath("//label[normalize-space(.)='Default radio']/input"));
        assertTrue(checkedRaiobutton.isSelected());
        assertFalse(defaultRaiobutton.isSelected());
        defaultRaiobutton.click();
        Thread.sleep(1000);
        assertFalse(checkedRaiobutton.isSelected());
        assertTrue(defaultRaiobutton.isSelected());
        checkedRaiobutton.click();
        Thread.sleep(1000);
        assertTrue(checkedRaiobutton.isSelected());
        assertFalse(defaultRaiobutton.isSelected());
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
//        Вариант с sendkeys
        Thread.sleep(1000);
        colorPicker.sendKeys("#00ff00");
        Thread.sleep(1000);
        assertEquals("#00ff00", colorPicker.getDomProperty("value"));
        Thread.sleep(1000);

//        Вариант с JS
        JavascriptExecutor js = (JavascriptExecutor) driver;

        Color red = new Color(255, 0, 0, 1);
        Color blue = new Color(0, 0, 255, 1);
        String script = "arguments[0].value = arguments[1]; arguments[0].dispatchEvent(new Event('change'));";
        Thread.sleep(3000);

        js.executeScript(script, colorPicker, red.asHex());
        assertEquals("#ff0000", colorPicker.getDomProperty("value"));
        Thread.sleep(3000);
        js.executeScript(script, colorPicker, blue.asHex());
        assertEquals("#0000ff", colorPicker.getDomProperty("value"));
        Thread.sleep(3000);
    }

    @DisplayName("Тест Date Picker")
    @Test
    void DatePickerTest () throws InterruptedException {
        /*Покрывал не все кейсы, только введение даты в поле вручную и выбор даты через клик по открывающейся форме*/
        driver.findElement(By.xpath("//a[@class = 'btn btn-outline-primary mb-2' and text() = 'Web form']")).click();
        WebElement datePickerInput = driver.findElement(By.xpath("//label[normalize-space(.)='Date picker']/input"));

        //Вариант с выбором через клик следующего дня
        datePickerInput.click();
        LocalDate newDate = LocalDate.now().plusDays(1);
        System.out.println("newDate = " + newDate);
        String xpathClassName = LocalDate.now().getMonth().maxLength() == LocalDate.now().getDayOfMonth() ? "new day" : "day";
        System.out.println("xpathClassName = " + xpathClassName);
        WebElement dateToSelect = driver.findElement(By.xpath(String.format("//td[@class='%s' and text()='%s']", xpathClassName, newDate.getDayOfMonth())));
        dateToSelect.click();
        assertEquals(newDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")), datePickerInput.getDomProperty("value"));
        Thread.sleep(1000);
        datePickerInput.clear();

        //1й вариант с динамически введенной датой
        String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        System.out.println("currentDate = " + currentDate);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.querySelector('input[name=\"my-date\"]').value = '" + currentDate + "';");
        Thread.sleep(1000);
        assertEquals(currentDate, datePickerInput.getDomProperty("value"));
        datePickerInput.clear();

        //2й вариант с захардкоженой датой
        datePickerInput.sendKeys("01 18 1989");
        datePickerInput.sendKeys(Keys.ENTER);
        Thread.sleep(1000);
        assertEquals("01/18/1989", datePickerInput.getDomProperty("value"));


    }

    @DisplayName("Тест Example Range")
    @Test
    void ExampleRangeTest () throws InterruptedException {
        driver.findElement(By.xpath("//a[@class = 'btn btn-outline-primary mb-2' and text() = 'Web form']")).click();
        WebElement exampleRange = driver.findElement(By.xpath("//label[normalize-space(.)='Example range']/input"));
        Thread.sleep(1000);
        assertEquals("5", exampleRange.getDomProperty("value"));

        //работа через клавиатуру
        exampleRange.sendKeys(Keys.ARROW_RIGHT);
        Thread.sleep(500);
        assertEquals("6", exampleRange.getDomProperty("value"));
        actions
                .sendKeys(Keys.ARROW_LEFT)
                .pause(500)
                .sendKeys(Keys.ARROW_LEFT)
                .perform();
        Thread.sleep(500);
        assertEquals("4", exampleRange.getDomProperty("value"));

        //работа через мышь
        int width = exampleRange.getSize().getWidth();
        int x = exampleRange.getLocation().getX();
        int y = exampleRange.getLocation().getY();
        int i;
        for (i = 5; i <= 10; i++) {
            new Actions(driver)
                    .moveToElement(exampleRange)
                    .clickAndHold()
                    .moveToLocation(x + width / 10 * i, y)
                    .release()
                    .perform();
            Thread.sleep(500);
            assertEquals(String.valueOf(i), exampleRange.getDomProperty("value"));
        }

        for (i = 9; i >= 0; i--) {
            new Actions(driver)
                    .moveToElement(exampleRange)
                    .clickAndHold()
                    .moveToLocation(x + width / 10 * i, y)
                    .release()
                    .perform();
            Thread.sleep(500);
            assertEquals(String.valueOf(i), exampleRange.getDomProperty("value"));
        }

    }


}
