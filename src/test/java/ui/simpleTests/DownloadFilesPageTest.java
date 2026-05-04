package ui.simpleTests;

import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import steps.AllureSteps;

import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@Story("Download")
public class DownloadFilesPageTest extends BaseTest {

    AllureSteps allureSteps = new AllureSteps();

    @Test
    void testDownloadHttpClient() throws IOException {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/download.html");

//      можно всё сделать через pngLink.click(), но нужно будет тогда перенастраивать браузер, что бы он сохранял файл в дирректорию проекта
        WebElement pngLink = driver.findElement(By.xpath("//a[@download = 'webdrivermanager.png']"));
        File pngFile = new File(".", "webdrivermanager.png");
        allureSteps.download2(pngLink.getAttribute("href"), pngFile);
        assertThat(pngFile).exists();

        WebElement pdfLink = driver.findElement(By.xpath("//a[@download = 'webdrivermanager.pdf']"));
        File pdfFile = new File(".", "webdrivermanager.pdf");
        allureSteps.download2(pdfLink.getAttribute("href"), pdfFile);
        assertThat(pdfFile).exists();
    }
}
