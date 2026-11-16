package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutOverviewPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By pageTitle = By.className("title");
    private final By itemNames = By.className("inventory_item_name");
    private final By subtotal = By.className("summary_subtotal_label");
    private final By tax = By.className("summary_tax_label");
    private final By total = By.className("summary_total_label");
    private final By finishButton = By.id("finish");

    public CheckoutOverviewPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public String getPageTitle() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(pageTitle)
        ).getText();
    }

    public String getProductName() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(itemNames)
        ).getText();
    }

    public String getSubtotal() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(subtotal)
        ).getText();
    }

    public String getTax() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(tax)
        ).getText();
    }

    public String getTotal() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(total)
        ).getText();
    }

    public void finishOrder() {
        wait.until(
                ExpectedConditions.elementToBeClickable(finishButton)
        ).click();
    }
}
