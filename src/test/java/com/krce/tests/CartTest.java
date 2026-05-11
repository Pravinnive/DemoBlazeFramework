package com.krce.tests;


import com.krce.base.BaseTest;
import com.krce.pages.CartPage;
import com.krce.pages.HomePage;
import com.krce.pages.ProductDetailPage;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class CartTest extends BaseTest {
    @Test(priority = 1)
    public void addSingleProductToCart() {

        HomePage homePage = new HomePage(driver);
        CartPage cartPage = new CartPage(driver);

        homePage.clickProduct("Samsung galaxy s6");

        ProductDetailPage productDetailPage = new ProductDetailPage(driver);

        String productName = productDetailPage.getProductName();
        String productPrice = productDetailPage.getProductPrice();

        productDetailPage.clickAddToCart();

        cartPage.openCart();

        Assert.assertTrue(cartPage.isProductInCart(productName),
                "Product not found in cart");

        Assert.assertTrue(cartPage.isPriceInCart(productPrice),
                "Price mismatch in cart");
    }

    @Test(priority = 2)
    public void addTwoProductsToCart() {

        HomePage homePage = new HomePage(driver);
        CartPage cartPage = new CartPage(driver);

        // First product
        homePage.clickHome();
        homePage.clickProduct("Samsung galaxy s6");

        ProductDetailPage pd1 = new ProductDetailPage(driver);
        pd1.clickAddToCart();

        // Second product
        homePage.clickHome();
        homePage.clickProduct("Nokia lumia 1520");

        ProductDetailPage pd2 = new ProductDetailPage(driver);
        pd2.clickAddToCart();

        cartPage.openCart();

        Assert.assertTrue(cartPage.getCartItemCount() >= 2, "Both products not added");
    }
    @Test(priority = 3)
    public void deleteProductFromCart() {

        HomePage homePage = new HomePage(driver);
        CartPage cartPage = new CartPage(driver);

        homePage.clickProduct("Samsung galaxy s6");

        ProductDetailPage pd = new ProductDetailPage(driver);
        String productName = pd.getProductName();

        pd.clickAddToCart();
        cartPage.openCart();

        Assert.assertTrue(cartPage.isProductInCart(productName), "Product not in cart before delete");

        cartPage.deleteProduct(productName);

        Assert.assertFalse(cartPage.isProductInCart(productName), "Product still exists after deletion");
    }



}

