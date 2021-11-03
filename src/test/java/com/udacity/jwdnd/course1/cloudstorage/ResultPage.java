package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ResultPage {
    @FindBy(id = "success-continue-link")
    private WebElement redirectToHomepage;

    @FindBy(id= "success-alert")
    private WebElement successAlert;

    @FindBy(id = "error-alert")
    private WebElement errorAlert;

    private JavascriptExecutor js;

    public ResultPage(WebDriver driver, JavascriptExecutor js) {
        PageFactory.initElements(driver, this);
        this.js = js;
    }

    void redirectToHome() {
        js.executeScript("arguments[0].click();", redirectToHomepage);
    }
}
