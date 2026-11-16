package tests;

import org.junit.jupiter.api.Test;
import pages.CartPage;
import pages.LoginPage;
import pages.ProductsPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CartTest extends BaseTest {

    @Test
    public void addingProductUpdatesCartBadge() {
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = new ProductsPage(driver);

        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addBackpackToCart();

        assertEquals("1", productsPage.getCartBadgeCount());
    }

    @Test
    public void addedProductAppearsInCart() {
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = new ProductsPage(driver);
        CartPage cartPage = new CartPage(driver);

        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addBackpackToCart();
        productsPage.openCart();

        assertEquals(1, cartPage.getCartItemCount());
    }

    @Test
    public void cartDisplaysCorrectProductName() {
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = new ProductsPage(driver);
        CartPage cartPage = new CartPage(driver);

        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addBackpackToCart();
        productsPage.openCart();

        assertEquals(
                "Sauce Labs Backpack",
                cartPage.getItemNames().get(0)
        );
    }

    @Test
    public void removingProductLeavesCartEmpty() {
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = new ProductsPage(driver);
        CartPage cartPage = new CartPage(driver);

        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addBackpackToCart();
        productsPage.openCart();
        cartPage.removeBackpack();

        assertEquals(0, cartPage.getCartItemCount());
    }
}
