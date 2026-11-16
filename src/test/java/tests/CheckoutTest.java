package tests;

import org.junit.jupiter.api.Test;
import pages.CartPage;
import pages.CheckoutPage;
import pages.CheckoutOverviewPage;
import pages.CheckoutCompletePage;
import pages.LoginPage;
import pages.ProductsPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckoutTest extends BaseTest {

    @Test
    public void missingFirstNameDisplaysError() {
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = new ProductsPage(driver);
        CartPage cartPage = new CartPage(driver);
        CheckoutPage checkoutPage = new CheckoutPage(driver);

        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addBackpackToCart();
        productsPage.openCart();
        cartPage.proceedToCheckout();

        checkoutPage.enterCustomerInformation("", "Moore", "50309");
        checkoutPage.continueCheckout();

        assertEquals(
                "Error: First Name is required",
                checkoutPage.getErrorMessage()
        );
    }

    @Test
    public void validInformationAdvancesToCheckoutOverview() {
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = new ProductsPage(driver);
        CartPage cartPage = new CartPage(driver);
        CheckoutPage checkoutPage = new CheckoutPage(driver);

        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addBackpackToCart();
        productsPage.openCart();
        cartPage.proceedToCheckout();

        checkoutPage.enterCustomerInformation(
                "DeMarko", "Moore", "50309"
        );
        checkoutPage.continueCheckout();

        assertEquals("Checkout: Overview", checkoutPage.getPageTitle());
    }

    @Test
    public void orderOverviewDisplaysCorrectProductAndTotals() {
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = new ProductsPage(driver);
        CartPage cartPage = new CartPage(driver);
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        CheckoutOverviewPage overviewPage =
                new CheckoutOverviewPage(driver);

        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addBackpackToCart();
        productsPage.openCart();
        cartPage.proceedToCheckout();

        checkoutPage.enterCustomerInformation(
                "DeMarko", "Moore", "50309"
        );
        checkoutPage.continueCheckout();

        assertEquals(
                "Sauce Labs Backpack",
                overviewPage.getProductName()
        );
        assertEquals("Item total: $29.99", overviewPage.getSubtotal());
        assertEquals("Tax: $2.40", overviewPage.getTax());
        assertEquals("Total: $32.39", overviewPage.getTotal());
    }

    @Test
    public void finishingCheckoutDisplaysOrderConfirmation() {
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = new ProductsPage(driver);
        CartPage cartPage = new CartPage(driver);
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        CheckoutOverviewPage overviewPage =
                new CheckoutOverviewPage(driver);
        CheckoutCompletePage completePage =
                new CheckoutCompletePage(driver);

        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addBackpackToCart();
        productsPage.openCart();
        cartPage.proceedToCheckout();

        checkoutPage.enterCustomerInformation(
                "DeMarko", "Moore", "50309"
        );
        checkoutPage.continueCheckout();
        overviewPage.finishOrder();

        assertEquals("Checkout: Complete!", completePage.getPageTitle());
        assertEquals(
                "Thank you for your order!",
                completePage.getConfirmationMessage()
        );
    }
}
