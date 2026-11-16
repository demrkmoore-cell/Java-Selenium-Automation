package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutCompletePage {

    private final WebDriverWait wait;

    private final By pageTitle = By.className("title");
    private final By confirmationMessage =
            By.className("complete-header");

    public CheckoutCompletePage(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public String getPageTitle() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(pageTitle)
        ).getText();
    }

    public String getConfirmationMessage() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        confirmationMessage
                )
        ).getText();
    }
}
