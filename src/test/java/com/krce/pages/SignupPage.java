package com.krce.pages;

import com.krce.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SignupPage extends BasePage {

    public SignupPage(WebDriver driver) {
        super(driver);
    }


    private By usernameField = By.id("sign-username");
    private By passwordField = By.id("sign-password");
    private By signupButton = By.xpath("//button[text()='Sign up']");


    public void enterUsername(String username) {
        type(usernameField, username);
    }
    public void enterPassword(String password) {
        type(passwordField, password);
    }
    public void clickSignupButton() {
        click(signupButton);
    }

    public String signup(String username, String password) {

        enterUsername(username);
        enterPassword(password);
        clickSignupButton();

        return getAlertText();
    }



    public String getAlertText() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        return wait.until(ExpectedConditions.alertIsPresent())
                .getText();
    }

    public void acceptAlert() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.alertIsPresent())
                .accept();
    }
}