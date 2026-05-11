package com.krce.pages;

import com.krce.base.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class ProductDetailPage extends BasePage {
    public ProductDetailPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
}

