import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BingSearchTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void testBingSearch() {
        driver.get("https://www.bing.com");

        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("Telerik Academy Alpha");
        searchBox.submit();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement firstResult = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li.b_algo h2 a")));

        String expectedTitle = "IT Career Start in 6 Months - Telerik Academy Alpha";
        String actualTitle = firstResult.getText();

        assertEquals(expectedTitle, actualTitle, "Test Failed: The title does not match the expected result");

        System.out.println("Test Passed: Title matches the expected result.");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}