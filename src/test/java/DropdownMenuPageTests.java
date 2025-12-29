import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class DropdownMenuPageTests {

    WebDriver driver;
    Actions actions;
    private static final String BASE_URL = "https://bonigarcia.dev/selenium-webdriver-java/";

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

    @DisplayName("Тест Открытие меню кликом выши")
    @Test
    void DropdownMenuTest () throws InterruptedException {
        driver.findElement(By.xpath("//a[@class = 'btn btn-outline-primary mb-2' and text() = 'Dropdown menu']")).click();
        Thread.sleep(1000);

        WebElement dropdown1 = driver.findElement(By.id("my-dropdown-1"));
        WebElement dropdownMenu1 = dropdown1.findElement(By.xpath("../ul"));
        WebElement dropdown2 = driver.findElement(By.id("my-dropdown-2"));
        WebElement dropdownMenu2 = dropdown2.findElement(By.xpath("../ul"));
        WebElement dropdown3 = driver.findElement(By.id("my-dropdown-3"));
        WebElement dropdownMenu3 = dropdown3.findElement(By.xpath("../ul"));

        assertEquals("Use left-click here", dropdown1.getText());
        assertEquals("dropdown-menu", dropdownMenu1.getDomAttribute("class"));
        actions.click(dropdown1).perform();
        assertEquals("dropdown-menu show", dropdownMenu1.getDomAttribute("class"));

        assertEquals("Action", dropdownMenu1.findElement(By.xpath("(//a[@class='dropdown-item'])[1]")).getText());
        assertEquals("Another action", dropdownMenu1.findElement(By.xpath("(//a[@class='dropdown-item'])[2]")).getText());
        assertEquals("Something else here", dropdownMenu1.findElement(By.xpath("(//a[@class='dropdown-item'])[3]")).getText());
        assertEquals("Separated link", dropdownMenu1.findElement(By.xpath("(//a[@class='dropdown-item'])[4]")).getText());
        Thread.sleep(2000);



        assertEquals("Use right-click here", dropdown2.getText());
        assertEquals(null, dropdownMenu2.getDomAttribute("style"));
        actions.contextClick(dropdown2).perform();
        assertEquals("display: block;", dropdownMenu2.getDomAttribute("style"));

        assertEquals("Action", dropdown2.findElement(By.xpath("(//a[@class='dropdown-item'])[1]")).getText());
        assertEquals("Another action", dropdown2.findElement(By.xpath("(//a[@class='dropdown-item'])[2]")).getText());
        assertEquals("Something else here", dropdown2.findElement(By.xpath("(//a[@class='dropdown-item'])[3]")).getText());
        assertEquals("Separated link", dropdown2.findElement(By.xpath("(//a[@class='dropdown-item'])[4]")).getText());
        Thread.sleep(2000);




        assertEquals("Use double-click here", dropdown3.getText());
        assertEquals(null, dropdownMenu3.getDomAttribute("style"));
        actions.doubleClick(dropdown3).perform();
        assertEquals("display: block;", dropdownMenu3.getDomAttribute("style"));
        Thread.sleep(2000);

        assertEquals("Action", dropdown3.findElement(By.xpath("(//a[@class='dropdown-item'])[1]")).getDomProperty("innerText"));
        assertEquals("Another action", dropdown3.findElement(By.xpath("(//a[@class='dropdown-item'])[2]")).getDomProperty("innerText"));
        assertEquals("Something else here", dropdown3.findElement(By.xpath("(//a[@class='dropdown-item'])[3]")).getDomProperty("innerText"));
        assertEquals("Separated link", dropdown3.findElement(By.xpath("(//a[@class='dropdown-item'])[4]")).getDomProperty("innerText"));
        Thread.sleep(2000);
    }
}
