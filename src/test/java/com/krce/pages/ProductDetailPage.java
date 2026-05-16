package com.krce.pages;

import com.krce.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class ProductDetailPage extends BasePage {

    public ProductDetailPage(WebDriver driver) {
        super(driver);
    }

    private By productName = By.cssSelector(".name");
    private By productPrice = By.cssSelector(".price-container");
    private By productDescription = By.cssSelector("#more-information p");
    private By addToCartBtn = By.xpath("//a[text()='Add to cart']");

    public String getProductName() {
        return driver.findElement(productName).getText().trim();
    }

    public String getProductPrice() {
        return driver.findElement(productPrice).getText().trim();
    }

    public String getProductDescription() {
        return driver.findElement(productDescription).getText();
    }

    public void clickAddToCart() {
        driver.findElement(addToCartBtn).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.alertIsPresent());

        driver.switchTo().alert().accept();
    }
}