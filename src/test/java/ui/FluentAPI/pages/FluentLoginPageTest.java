package ui.FluentAPI.pages;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ui.FluentAPI.FluentTests.FluentLoginPage;

public class FluentLoginPageTest extends FluentBaseTest {


    FluentLoginPage loginPage;

    @BeforeEach
    void initPages(){loginPage = new FluentLoginPage(driver);}

    @DisplayName("Проверка успешного логина")
    @Test
    void testLoginSuccess (){
        loginPage
                .login("user","user")
                .checksSccessBoxPresent()
                .checkInvalidCredentialsBoxIsNotPresent();
    }

    @DisplayName("Проверка неуспешного логина")
    @Test
    void testLoginFailure (){
        loginPage
                .login("test","test")
                .checksSccessBoxIsNotPresent()
                .checkInvalidCredentialsBoxPresent();
    }
}
