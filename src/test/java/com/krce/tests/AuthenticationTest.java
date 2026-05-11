package com.krce.tests;

import com.krce.base.BaseTest;
import com.krce.pages.HomePage;
import com.krce.pages.LoginPage;
import com.krce.pages.SignupPage;
import org.testng.Assert;
import org.testng.annotations.Test;



    public class AuthenticationTest extends BaseTest {

        String username = "user" + System.currentTimeMillis();
        String password = "12345";

        @Test(priority = 1)
        public void signupTest() {

            HomePage homePage = new HomePage(driver);
            homePage.clickSignup();

            SignupPage signupPage = new SignupPage(driver);

            String alert = signupPage.signup(username, password);

            Assert.assertTrue(alert.contains("Sign up successful"));
        }

        @Test(priority = 2)
        public void loginTest() {

            HomePage homePage = new HomePage(driver);
            homePage.clickLogin();

            LoginPage loginPage = new LoginPage(driver);

            loginPage.login(username, password);

            Assert.assertTrue(homePage.isUserLoggedIn());
        }

        @Test(priority = 3)
        public void invalidLoginTest() {

            HomePage homePage = new HomePage(driver);
            homePage.clickLogin();

            LoginPage loginPage = new LoginPage(driver);

            String alert =
                    loginPage.invalidLogin(username, "wrong");

            Assert.assertTrue(alert.contains("Wrong password"));
        }

        @Test(priority = 4)
        public void logoutTest() {

            HomePage homePage = new HomePage(driver);

            homePage.clickLogin();

            LoginPage loginPage = new LoginPage(driver);

            loginPage.login(username, password);

            homePage.clickLogout();

            Assert.assertTrue(homePage.isLogoutSuccessful());
        }
    }