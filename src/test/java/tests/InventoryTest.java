package tests;

import org.junit.jupiter.api.Test;
import pages.LoginPage;
import pages.ProductsPage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InventoryTest extends BaseTest {

    @Test
    public void allSixProductsAreDisplayed() {
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = new ProductsPage(driver);

        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        assertEquals(6, productsPage.getProductCount());
    }

    @Test
    public void productsCanBeSortedByNameAscending() {
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = new ProductsPage(driver);

        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        List<String> actualNames = productsPage.getProductNames();
        List<String> expectedNames = new ArrayList<>(actualNames);
        Collections.sort(expectedNames);

        productsPage.sortByNameAscending();

        assertEquals(expectedNames, productsPage.getProductNames());
    }

    @Test
    public void productsCanBeSortedByPriceAscending() {
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = new ProductsPage(driver);

        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        List<Double> actualPrices = productsPage.getProductPrices();
        List<Double> expectedPrices = new ArrayList<>(actualPrices);
        Collections.sort(expectedPrices);

        productsPage.sortByPriceAscending();

        assertEquals(expectedPrices, productsPage.getProductPrices());
    }
}
