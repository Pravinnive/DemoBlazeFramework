package com.krce.tests;

import com.krce.base.BaseTest;
import com.krce.pages.HomePage;
import com.krce.pages.ProductDetailPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductBrowseTest extends BaseTest {
    @Test
    public void verifyProductBrowseByCategory() {

        HomePage homePage = new HomePage(driver);

        homePage.clickPhonesCategory();
        Assert.assertTrue(homePage.areProductsDisplayed(), "Phone products are not displayed");
        Assert.assertTrue(homePage.isProductPresent("Samsung galaxy s6"), "Phones category not loaded properly");
        System.out.println("Phone products displayed successfully");

        homePage.clickLaptopsCategory();
        Assert.assertTrue(homePage.areProductsDisplayed(), "Laptop products are not displayed");
        Assert.assertTrue(homePage.isProductPresent("Sony vaio i5"), "Laptops category not loaded properly");
        System.out.println("Laptop products displayed successfully");

        homePage.clickProduct("Sony vaio i5");
        ProductDetailPage productDetailPage = new ProductDetailPage(driver);
        Assert.assertEquals(productDetailPage.getProductName(), "Sony vaio i5", "Product name mismatch");
        Assert.assertTrue(productDetailPage.getProductPrice().contains("$"), "Price not displayed properly");
        Assert.assertTrue(productDetailPage.getProductDescription().trim().length() > 0, "Product description is empty");
        System.out.println("Product details verified successfully");

        homePage.clickHome();
        Assert.assertTrue(homePage.areProductsDisplayed(), "Home page did not reload products");
        System.out.println("Home page loaded successfully");
    }
}