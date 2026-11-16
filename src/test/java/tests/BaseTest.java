package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class BaseTest {

    protected WebDriver driver;

    @RegisterExtension
    protected TestExecutionExceptionHandler failureScreenshot =
            new TestExecutionExceptionHandler() {
                @Override
                public void handleTestExecutionException(
                        ExtensionContext context,
                        Throwable cause
                ) throws Throwable {
                    saveFailureScreenshot(context);
                    throw cause;
                }
            };

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();

        if (System.getenv("CI") == null) {
            options.setBinary("/usr/bin/chromium");
        }

        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        driver = new ChromeDriver(options);
    }

    private void saveFailureScreenshot(ExtensionContext context) {
        if (driver == null) {
            return;
        }

        try {
            Path directory = Path.of("target", "screenshots");
            Files.createDirectories(directory);

            String fileName =
                    context.getRequiredTestMethod().getName() + ".png";

            File screenshot =
                    ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            Path destination = directory.resolve(fileName);

            Files.copy(
                    screenshot.toPath(),
                    destination,
                    StandardCopyOption.REPLACE_EXISTING
            );

            System.out.println(
                    "Failure screenshot saved: " + destination
            );

        } catch (IOException e) {
            System.err.println(
                    "Could not save failure screenshot: " + e.getMessage()
            );
        }
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
