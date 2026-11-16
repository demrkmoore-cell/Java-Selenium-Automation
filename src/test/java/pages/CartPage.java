package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CartPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By pageTitle = By.className("title");
    private final By cartItems = By.className("cart_item");
    private final By itemNames = By.className("inventory_item_name");
    private final By removeBackpackButton =
            By.id("remove-sauce-labs-backpack");
    private final By checkoutButton = By.id("checkout");

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public String getPageTitle() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(pageTitle)
        ).getText();
    }

    public int getCartItemCount() {
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(pageTitle)
        );
        return driver.findElements(cartItems).size();
    }

    public List<String> getItemNames() {
        return driver.findElements(itemNames)
                .stream()
                .map(element -> element.getText())
                .toList();
    }

    public void removeBackpack() {
        wait.until(
                ExpectedConditions.elementToBeClickable(removeBackpackButton)
        ).click();
    }

    public void proceedToCheckout() {
        wait.until(
                ExpectedConditions.elementToBeClickable(checkoutButton)
        ).click();
    }
}
