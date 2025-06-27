package ui;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class OpenFakeStoreHomePageTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        // Solo si no estás usando WebDriverManager
        System.setProperty("webdriver.chrome.driver", "ruta/a/tu/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void userCanOpenFakeStoreHomePage() {
        driver.get("https://fakestoreapi.com/");
        String title = driver.getTitle();
        System.out.println("El título de la página es: " + title);

        // Validar que el título contenga al menos "Fake"
        assertTrue(title.toLowerCase().contains("fake"), "El título no contiene la palabra esperada.");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
