package tests;

import org.junit.jupiter.api.Test;
import pages.LoginPage;
import pages.ProductsPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest extends BaseTest {

    @Test
    public void validLoginOpensProductsPage() {
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = new ProductsPage(driver);

        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        assertEquals("Products", productsPage.getPageTitle());
        assertEquals(6, productsPage.getProductCount());
    }

    @Test
    public void invalidLoginDisplaysErrorMessage() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.login("wrong_user", "wrong_password");

        assertTrue(loginPage.getErrorMessage()
                .contains("Username and password do not match"));
    }

    @Test
    public void emptyUsernameDisplaysErrorMessage() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.login("", "secret_sauce");

        assertTrue(loginPage.getErrorMessage()
                .contains("Username is required"));
    }

    @Test
    public void lockedOutUserCannotLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.login("locked_out_user", "secret_sauce");

        assertTrue(loginPage.getErrorMessage()
                .contains("locked out"));
    }
}
