package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By pageTitle = By.className("title");
    private final By firstNameField = By.id("first-name");
    private final By lastNameField = By.id("last-name");
    private final By postalCodeField = By.id("postal-code");
    private final By continueButton = By.id("continue");
    private final By errorMessage = By.cssSelector("[data-test='error']");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public String getPageTitle() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(pageTitle)
        ).getText();
    }

    public void enterCustomerInformation(
            String firstName,
            String lastName,
            String postalCode
    ) {
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(firstNameField)
        ).sendKeys(firstName);

        driver.findElement(lastNameField).sendKeys(lastName);
        driver.findElement(postalCodeField).sendKeys(postalCode);
    }

    public void continueCheckout() {
        wait.until(
                ExpectedConditions.elementToBeClickable(continueButton)
        ).click();
    }

    public String getErrorMessage() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(errorMessage)
        ).getText();
    }
}
