package com.krce.pages;

import com.krce.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;



public class ProductDetailPage extends BasePage {

    public ProductDetailPage(WebDriver driver) {
        super(driver);

    }

    private By productName = By.cssSelector(".name");
    private By productPrice = By.cssSelector(".price-container");

    private By productDescription = By.cssSelector("#more-information p");

    public String getProductName() {
        return driver.findElement(productName).getText();
    }

    public String getProductPrice() {
        return driver.findElement(productPrice).getText();
    }

    public String getProductDescription() {
        return driver.findElement(productDescription).getText();
    }
}