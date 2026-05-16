package com.krce.pages;

import com.krce.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;


public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    private By loginLink = By.id("login2");
    private By signupLink = By.id("signin2");
    private By logoutLink = By.id("logout2");

    private By welcomeUser = By.id("nameofuser");

    private By phonesCategory = By.linkText("Phones");
    private By laptopsCategory = By.linkText("Laptops");
    private By homeLink = By.id("nava");
    private By productCards = By.cssSelector(".card-title");


    public void clickLogin() {
        click(loginLink);
    }
    public void clickSignup() {
        click(signupLink);
    }
    public void clickLogout() {
        click(logoutLink);
    }

    public boolean isUserLoggedIn() {
        return new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(welcomeUser)).isDisplayed();
    }

    public boolean isLogoutSuccessful() {
        return new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(loginLink)).isDisplayed();
    }


    public void clickPhonesCategory() {

        driver.findElement(phonesCategory).click();

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//a[text()='Samsung galaxy s6']")
        ));
    }
    public void clickLaptopsCategory() {

        driver.findElement(laptopsCategory).click();

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//a[text()='Sony vaio i5']")
        ));
    }
    public boolean areProductsDisplayed() {

        return driver.findElements(productCards).size() > 0;
    }

    public void clickProduct(String productName) {

        By product =
                By.xpath("//a[text()='" + productName + "']");

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));

        int attempts = 0;

        while (attempts < 3) {

            try {

                wait.until(ExpectedConditions.presenceOfElementLocated(product));

                wait.until(ExpectedConditions.elementToBeClickable(product));

                driver.findElement(product).click();

                break;

            } catch (org.openqa.selenium.StaleElementReferenceException e) {

                attempts++;
            }
        }
    }
    public boolean isCategoryLoaded(String categoryName) {

        wait.until(ExpectedConditions.visibilityOfElementLocated(productCards));

        List<WebElement> products = driver.findElements(productCards);

        return products.size() > 0;
    }

    public void clickHome() {
        driver.findElement(homeLink).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(productCards));
    }
    public boolean isProductPresent(String productName) {
        return driver.findElements(By.xpath("//a[text()='" + productName + "']")).size() > 0;
    }
}