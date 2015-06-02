package com.gl.training.pages;


import org.openqa.selenium.WebDriver;

import static com.gl.training.utils.DataProvider.getBaseUrl;

public class HomePage extends Page{

//    @FindAll()

    public HomePage(WebDriver wd) {
        super(wd);
    }

    @Override
    public String getPageURL() {
        return getBaseUrl();
    }

    @Override
    protected void checkUniqueElements() throws Error {

    }
}
