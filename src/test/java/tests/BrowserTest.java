package tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BrowserTest {

    @Test
    void browserOpensSuccessfully() {
        ChromeOptions options = new ChromeOptions();
        options.setBinary("/usr/bin/chromium");
        options.addArguments("--headless", "--no-sandbox", "--disable-dev-shm-usage");

        WebDriver driver = new ChromeDriver(options);

        try {
            driver.get("https://example.com");
            assertTrue(driver.getTitle().contains("Example Domain"));
        } finally {
            driver.quit();
        }
    }
}
