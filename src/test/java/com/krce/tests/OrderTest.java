package com.krce.tests;

import com.krce.base.BaseTest;
import com.krce.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class OrderTest extends BaseTest {

    @Test(priority = 1)
    public void placeOrderSuccessfully() {

        HomePage homePage = new HomePage(driver);
        CartPage cartPage = new CartPage(driver);
        ProductDetailPage pd = new ProductDetailPage(driver);
        OrderPage orderPage = new OrderPage(driver);

        homePage.clickProduct("Samsung galaxy s6");
        pd.clickAddToCart();

        cartPage.openCart();

        orderPage.openPlaceOrder();

        orderPage.enterName("Nivethani");
        orderPage.enterCountry("India");
        orderPage.enterCity("Salem");
        orderPage.enterCard("1234567890123456");
        orderPage.enterMonth("12");
        orderPage.enterYear("2026");

        orderPage.clickPurchase();

        String confirmation = orderPage.getConfirmationText();

        Assert.assertTrue(
                confirmation.toLowerCase().contains("thank you"),
                "Order not successful: " + confirmation
        );
    }

    @Test(priority = 2)
    public void verifyOrderIdExists() {

        HomePage homePage = new HomePage(driver);
        CartPage cartPage = new CartPage(driver);

        homePage.clickProduct("Samsung galaxy s6");

        ProductDetailPage pd = new ProductDetailPage(driver);
        pd.clickAddToCart();

        cartPage.openCart();

        OrderPage orderPage = new OrderPage(driver);

        orderPage.openPlaceOrder();

        orderPage.enterName("Test User");
        orderPage.enterCountry("India");
        orderPage.enterCity("Salem");
        orderPage.enterCard("1234567890123456");
        orderPage.enterMonth("12");
        orderPage.enterYear("2026");

        orderPage.clickPurchase();

        String title = orderPage.getConfirmationText();
        String details = orderPage.getOrderDetails();

        System.out.println("CONFIRMATION: " + title);
        System.out.println("DETAILS: " + details);

        Assert.assertTrue(title.contains("Thank you"), "Order failed");

        Assert.assertTrue(
                details.toLowerCase().contains("id"),
                "Order ID not generated properly: " + details
        );
    }

    @Test(priority = 3)
    public void placeOrderWithEmptyFields() {

        CartPage cartPage = new CartPage(driver);
        OrderPage orderPage = new OrderPage(driver);
        cartPage.openCart();
        orderPage.openPlaceOrder();
        orderPage.clickPurchase();
        Assert.assertTrue(isAlertPresent(), "Expected alert NOT shown for empty fields");

        driver.switchTo().alert().accept();
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}