package com.gl.training.pages;


import com.gl.training.entities.User;
import com.gl.training.utils.DataProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UserProfileDeletePage extends Page {
    private User user;


    @FindBy(id = "yui-gen0-button")
    private WebElement yesButton;

    @FindBy(name = "delete")
    private WebElement formDelete;

    public UserProfileDeletePage(WebDriver wd, User user) {
        super(wd);
        this.user=user;
    }

    public HomePage submitDeletion(){
        yesButton.click();
        return new HomePage(wd);
    }

    public String getConformationRequestMessage(){
        return (formDelete.getText().split("\n"))[0];
    }

    @Override
    public String getPageURL() {
        return DataProvider.getBaseUrl()+"/user/"+user.getName()+"/delete";
    }

    @Override
    protected void checkUniqueElements() throws Error {

    }
}
