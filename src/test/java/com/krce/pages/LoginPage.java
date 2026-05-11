package com.krce.pages;

import com.krce.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    private By username = By.id("loginusername");
    private By password = By.id("loginpassword");
    private By loginBtn = By.xpath("//button[text()='Log in']");

    public void login(String user, String pass) {

        type(username, user);
        type(password, pass);

        click(loginBtn);
    }

    public String invalidLogin(String user, String pass) {

        login(user, pass);

        return getAlertText();
    }
}