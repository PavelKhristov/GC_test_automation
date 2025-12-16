import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        driver.findElement(By.xpath("//label[normalize-space(text())='Disabled input']/input")).isDisplayed();
        driver.findElement(By.xpath("//label[normalize-space(text())='Readonly input']/input"));
        driver.findElement(By.xpath("//label[normalize-space(text())='Dropdown (select)']/select"));
        driver.findElement(By.xpath("//label[normalize-space(text())='Dropdown (datalist)']/input"));
        driver.findElement(By.xpath("//input[@name='my-file']"));
        driver.findElement(By.xpath("//label[normalize-space(.)='Checked checkbox']/input")).click();
        driver.findElement(By.xpath("//label[normalize-space(.)='Default checkbox']/input")).click();
        driver.findElement(By.xpath("//label[normalize-space(.)='Checked radio']/input")).click();
        driver.findElement(By.xpath("//label[normalize-space(.)='Default radio']/input")).click();
        driver.findElement(By.xpath("//a[contains(text(), 'Return to index')]")).click();
        driver.findElement(By.xpath("//a[@class = 'btn btn-outline-primary mb-2' and text() = 'Web form']")).click();
        driver.findElement(By.xpath("//label[normalize-space(.)='Color picker']/input"));
        driver.findElement(By.xpath("//label[normalize-space(.)='Date picker']/input"));
        driver.findElement(By.xpath("//label[normalize-space(.)='Example range']/input"));
        driver.findElement(By.xpath("//button[text()='Submit']")).click();
        driver.findElement(By.xpath("//h1[text()='Form submitted']"));
        driver.findElement(By.xpath("//p[text()='Received!']"));
        driver.navigate().back();
        Thread.sleep(2000);

    }


}
