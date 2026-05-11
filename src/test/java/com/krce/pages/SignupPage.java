package com.krce.pages;

import com.krce.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignupPage extends BasePage {

    public SignupPage(WebDriver driver) {
        super(driver);
    }

    private By username = By.id("sign-username");
    private By password = By.id("sign-password");
    private By signupBtn = By.xpath("//button[text()='Sign up']");

    public String signup(String user, String pass) {

        type(username, user);
        type(password, pass);

        click(signupBtn);

        return getAlertText();
    }
}