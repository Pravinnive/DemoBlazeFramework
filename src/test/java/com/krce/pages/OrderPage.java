package com.krce.pages;

import com.krce.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderPage extends BasePage {

    private WebDriverWait wait;

    public OrderPage(WebDriver driver) {
        super(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(25));
    }

    private By placeOrderBtn = By.xpath("//button[text()='Place Order']");
    private By name = By.id("name");
    private By country = By.id("country");
    private By city = By.id("city");
    private By card = By.id("card");
    private By month = By.id("month");
    private By year = By.id("year");
    private By purchaseBtn = By.xpath("//button[text()='Purchase']");

    private By confirmationTitle = By.cssSelector(".sweet-alert h2");
    private By confirmationModal = By.cssSelector(".sweet-alert.showSweetAlert.visible");

    public void openPlaceOrder() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));


        By placeOrderBtn = By.xpath("//button[contains(text(),'Place Order')]");

        wait.until(ExpectedConditions.elementToBeClickable(placeOrderBtn));
        driver.findElement(placeOrderBtn).click();
    }

    public void enterName(String v) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(name)).sendKeys(v);
    }

    public void enterCountry(String v) {
        driver.findElement(country).sendKeys(v);
    }

    public void enterCity(String v) {
        driver.findElement(city).sendKeys(v);
    }

    public void enterCard(String v) {
        driver.findElement(card).sendKeys(v);
    }

    public void enterMonth(String v) {
        driver.findElement(month).sendKeys(v);
    }

    public void enterYear(String v) {
        driver.findElement(year).sendKeys(v);
    }

    public void clickPurchase() {
        wait.until(ExpectedConditions.elementToBeClickable(purchaseBtn)).click();
    }


    public String getConfirmationText() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        By confirmationBox = By.cssSelector(".sweet-alert h2");

        return wait.until(ExpectedConditions.visibilityOfElementLocated(confirmationBox)).getText();
    }

    public String getOrderDetails() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        By details = By.cssSelector(".sweet-alert p");

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(details)
        ).getText();
    }
}