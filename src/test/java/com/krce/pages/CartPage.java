package com.krce.pages;

import com.krce.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CartPage extends BasePage {

    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    private By cartLink = By.id("cartur");

    private By productNames = By.xpath("//tr/td[2]");
    private By productPrices = By.xpath("//tr/td[3]");
    private By deleteButtons = By.xpath("//a[text()='Delete']");
    private By totalPrice = By.id("totalp");

    public void openCart() {
        click(cartLink);
        try {
            Thread.sleep(2000);
        } catch (Exception ignored) {}
    }

    public boolean isProductInCart(String productName) {
        List<WebElement> names = driver.findElements(productNames);
        return names.stream()
                .map(e -> e.getText().trim())
                .anyMatch(text -> text.equals(productName));
    }

    public boolean isPriceInCart(String price) {
        List<WebElement> prices = driver.findElements(productPrices);

        String expected = price.replaceAll("[^0-9]", "").trim();

        return prices.stream()
                .map(e -> e.getText().replaceAll("[^0-9]", "").trim())
                .anyMatch(text -> text.equals(expected));
    }


    public int getCartItemCount() {
        return driver.findElements(productNames).size();
    }

    public void deleteFirstProduct() {
        List<WebElement> deleteBtns = driver.findElements(deleteButtons);

        if (!deleteBtns.isEmpty()) {
            WebElement firstBtn = deleteBtns.get(0);

            firstBtn.click();

            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.stalenessOf(firstBtn));
        }
    }


    public void deleteProduct(String productName) {
        List<WebElement> rows = driver.findElements(By.xpath("//tr"));

        for (WebElement row : rows) {
            if (row.getText().contains(productName)) {
                row.findElement(By.xpath(".//a[text()='Delete']")).click();

                new WebDriverWait(driver, Duration.ofSeconds(10))
                        .until(ExpectedConditions.stalenessOf(row));
                break;
            }
        }
    }

    public int getTotalPrice() {
        try {
            String total = driver.findElement(totalPrice).getText().trim();
            return Integer.parseInt(total);
        } catch (Exception e) {
            return 0;
        }
    }


    public void waitForCartToUpdate(int expectedCount) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(d -> getCartItemCount() == expectedCount);
    }

    
    public void waitForCartPageLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(productNames));
    }
}