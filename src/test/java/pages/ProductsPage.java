package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class ProductsPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By pageTitle = By.className("title");
    private final By inventoryItems = By.className("inventory_item");
    private final By productNames = By.className("inventory_item_name");
    private final By productPrices = By.className("inventory_item_price");
    private final By sortDropdown = By.className("product_sort_container");
    private final By backpackAddButton = By.id("add-to-cart-sauce-labs-backpack");
    private final By cartBadge = By.className("shopping_cart_badge");
    private final By cartLink = By.className("shopping_cart_link");

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public String getPageTitle() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(pageTitle)
        ).getText();
    }

    public int getProductCount() {
        wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(inventoryItems)
        );
        return driver.findElements(inventoryItems).size();
    }

    public List<String> getProductNames() {
        wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(productNames)
        );
        return driver.findElements(productNames)
                .stream()
                .map(element -> element.getText())
                .toList();
    }

    public List<Double> getProductPrices() {
        wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(productPrices)
        );
        return driver.findElements(productPrices)
                .stream()
                .map(element -> Double.parseDouble(
                        element.getText().replace("$", "")
                ))
                .toList();
    }

    public void sortByNameAscending() {
        new Select(driver.findElement(sortDropdown))
                .selectByValue("az");
    }

    public void sortByPriceAscending() {
        new Select(driver.findElement(sortDropdown))
                .selectByValue("lohi");
    }

    public void addBackpackToCart() {
        wait.until(
                ExpectedConditions.elementToBeClickable(backpackAddButton)
        ).click();
    }

    public String getCartBadgeCount() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(cartBadge)
        ).getText();
    }

    public void openCart() {
        driver.findElement(cartLink).click();
    }
}
