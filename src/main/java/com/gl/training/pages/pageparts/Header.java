package com.gl.training.pages.pageparts;


import com.gl.training.utils.CommonOperations;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Header {

    WebDriver driver;

    @FindBy(name = "search")
    private WebElement searchForm;

    @FindBy(id = "search-box")
    private WebElement searchField;

    public Header(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    public Header enterTextToSearch(String text){
        CommonOperations.sendKeys(searchField, text);
        return this;
    }

    public Header submitTextToSearch(String text){
        enterTextToSearch(text);
        searchForm.submit();
        return this;
    }

//    @FindBys(xpath = "//div[@id='search-box-completion']/id")
//    private


}
