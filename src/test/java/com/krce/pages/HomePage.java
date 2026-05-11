package com.krce.pages;

import com.krce.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    private By loginLink = By.id("login2");
    private By signupLink = By.id("signin2");
    private By logoutLink = By.id("logout2");
    private By welcomeUser = By.id("nameofuser");

    public void clickLogin() {
        click(loginLink);
    }

    public void clickSignup() {
        click(signupLink);
    }

    public boolean isUserLoggedIn() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            return wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.id("nameofuser"))
            ).isDisplayed();

        } catch (Exception e) {
            return false;
        }
    }

    public void clickLogout() {
        click(logoutLink);
    }
    public boolean isLogoutSuccessful() {

        return driver.findElement(By.id("login2")).isDisplayed();
    }

}