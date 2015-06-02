package com.gl.training;

import com.gl.training.utils.web.WebDriverController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

import java.util.Date;

public class BaseTestNG {

    protected final Logger log = LogManager.getLogger(this);
    protected WebDriver driver = null;

    protected String text = "asdfga";
    protected String alphaNum = "2TEst42";
    protected String specChar = "!@#$%^&*()_+";
    protected String anyCharSet = " Aa2!@#$%^&*()_+-={}[]:;<>,./?\"|\\";
    protected String lessThreeChar = "as";
    protected String longText50 = "asdfghjkloasdfghjkloasdfghjkloasdfghjkloasdfghjkloasdfghjklo";
    protected String emptyText = "";
    protected String startsFromSpace = "  11Dd";
    protected String localizationText = "АукцЫон";
    private String validEmail = "a@b.com";

    private String uniqueName;

    @BeforeClass
    public void startDriver(){
        driver = WebDriverController.getDriver();
        setUniqueName();
    }

    @AfterClass
    public void shutDown(){
        driver.quit();
    }

    public String setUniqueName(){
        String name = alphaNum+new Date().getTime();
        log.info("Unique name is: "+ name);
        return uniqueName = name;
    }

    public String getUniqueName() {
        return uniqueName;
    }

    private String getUniqueUsername(){
        Date date = new Date();
        return "User#"+date;
    }

    protected void deleteAllCookies() {
        driver.manage().deleteAllCookies();
    }

    @DataProvider
    public Object[][] positiveLoginData(){
        return new Object[][]{
                {getUniqueName(), anyCharSet, "All allowed characters validation"}
        };
    }

    @DataProvider
    public Object[][] negativeLoginData(){
        return new Object[][]{
                {lessThreeChar, lessThreeChar, "Failed sign up user login"},
                {lessThreeChar, anyCharSet, "Login with wrong name"},
                {alphaNum, lessThreeChar, "Login with wrong password"},
                {emptyText, anyCharSet, "Login with empty name"},
                {alphaNum, emptyText, "Login with empty password"}
        };
    }

    @DataProvider
    public Object[][] positiveSignUpData(){
        String uniqueName = getUniqueUsername();
        return new Object[][]{
                {uniqueName, specChar, specChar, uniqueName, validEmail, "Valid sign up"}
        };
    }

    @DataProvider
    public Object[][] negativeSignUpData(){
        String uniqueName = getUniqueUsername();
        return new Object[][]{
                {uniqueName, specChar, "", uniqueName, validEmail, "Invalid sign up. Empty confirm password field"}
        };
    }
}
