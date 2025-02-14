package lection;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LifeCycleJUnitTest {

    @BeforeEach
    void setUp() {
        // Здесь размещаем код подготовки перед каждым тестом
        System.out.println("Перед каждым тестом");
    }

    @AfterEach
    void tearDown() {
        // Здесь размещаем код очистки после каждого теста
        System.out.println("После каждого теста");
    }

    @BeforeAll
    void setUpAll() {
        // Здесь размещаем код подготовки перед всеми тестами
        System.out.println("Перед всеми тестами");
    }

    @AfterAll
    void tearDownAll() {
        // Здесь размещаем код очистки после всех тестов
        System.out.println("После всех тестов");
    }

    @Test
    @DisplayName("Первый")
    @Order(1)
    void firts() {
        int actualSum = 2 + 2;
        int expectedSum = 4;
        assertEquals(expectedSum, actualSum);
        System.out.println("Первый тест прошел");
    }

    @Test
    @DisplayName("Второй")
    void second() {
        int actualSum = 2 + 2;
        int expectedSum = 4;
        assertEquals(expectedSum, actualSum);
        System.out.println("Второй тест прошел");
    }
}
