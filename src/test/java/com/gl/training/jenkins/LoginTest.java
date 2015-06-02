package com.gl.training.jenkins;

import com.gl.training.BaseTestNG;
import com.gl.training.pages.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.gl.training.utils.CommonOperations.verifyCurrentUrl;
import static com.gl.training.utils.DataProvider.getBaseUrl;
import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTestNG {

    private LoginPage loginPage;
    private String loginErrorUrlPart = "/loginError";
    private String loginSuccessUrlPart = "/securityRealm/createAccount";
    private String loggedInFullNameLocatorXpath = "//a[@class='model-link inside inverse']/b";
    String expectedErrorMessage = "Invalid login information. Please try again.";


    @BeforeMethod
    public void setUp(){
        deleteAllCookies();
        loginPage = new LoginPage(driver).get();
        loginPage.waitForPageLoaded();
    }

    @Test(dataProvider = "negativeLoginData")
    public void negativeTest(String name, String password, String logMessage){
        log.info("Test: " + logMessage);
        loginPage.submitLogin(name, password);
        verifyCurrentUrl(driver, getBaseUrl() + loginErrorUrlPart);
        WebElement txtErrorMessage = driver.findElement(By.xpath("//div[@id='main-panel-content']/div"));
        String actualErrorMessage = (txtErrorMessage.getText().split("\n"))[0];

        assertEquals(actualErrorMessage, expectedErrorMessage,
                "Error message is: '" + actualErrorMessage + "', but expected: '" + expectedErrorMessage + "'!");
    }

    @Test(dataProvider = "positiveLoginData")
    public void positiveTest(String name, String password, String logMessage){
        log.info(logMessage);
        loginPage.submitLogin(name, password);
        verifyCurrentUrl(driver, getBaseUrl() + loginSuccessUrlPart);
    }

}
