package com.gl.training.pages;

import com.gl.training.pages.pageparts.Header;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import static com.gl.training.utils.CommonOperations.sendKeys;
import static com.gl.training.utils.DataProvider.getBaseUrl;

public class LoginPage extends Page<LoginPage> {

    public Header getHeader() {
        return header;
    }

    private Header header;

    @FindBy(name = "login")
    private WebElement loginForm;

    @FindBy(name = "j_username")
    private WebElement userField;

    @FindBy(name = "j_password")
    private WebElement passwordField;

    private static String loginUrlPart = "/login";

    @NotNull
    public static String getExpectedUrl() {
        return getBaseUrl() + loginUrlPart;
    }

    public LoginPage(WebDriver wd) {
        super(wd);
        this.header = new Header(wd);
    }

    public static String getLoginUrlPart() {
        return loginUrlPart;
    }

    @Override
    public String getPageURL() {
        return getBaseUrl() + getLoginUrlPart();
    }

    @Override
    protected void checkUniqueElements() throws Error {
        //TODO add
    }

    public void submitLogin(String name, String password) {
        log.info("Try to login with name: '" + name + "', password: '" + password + "'");
        sendKeys(userField, name);
        sendKeys(passwordField, password);
        loginForm.submit();
    }

    @Override
    protected void load() {
        wd.get(
                getExpectedUrl());
    }

    @Override
    protected void isLoaded() throws Error {
        try{
//            URL actualUrl = new URL(wd.getCurrentUrl());
            String url = wd.getCurrentUrl();
//            actualUrl.
        Assert.assertTrue(url.equals(getExpectedUrl()), "Expected URL: "+getExpectedUrl()+" actual URL: " + url);
        }catch(Exception e){throw new Error(this.getClass()+" is not loaded");}
    }


    public LoginPage waitForPageLoaded() {
        waitForElementClickable(userField);
        return this;
    }
}