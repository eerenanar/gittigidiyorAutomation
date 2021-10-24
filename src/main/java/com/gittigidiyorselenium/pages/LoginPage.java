package com.gittigidiyorselenium.pages;

import com.gittigidiyorselenium.base.TestBase;
import com.gittigidiyorselenium.util.UtilLogger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class LoginPage extends TestBase {

    //My Page Factory fields

    @FindBy(how = How.CSS, using = "#L-UserNameField")
    @CacheLookup
    public WebElement fieldUsername;

    @FindBy(how = How.CSS, using = "#L-PasswordField")
    @CacheLookup
    public WebElement fieldPassword;

    @FindBy(how = How.CSS, using = "#gg-login-enter")
    @CacheLookup
    public WebElement btnLogin;

    @FindBy(how = How.XPATH, using = "//a[@class='logo_gg imglink logo-small']")
    @CacheLookup
    public WebElement logoLoginPage;

    public LoginPage(){
        PageFactory.initElements(driver,this);
    }

    public void validateLoginPageTittle(){
        String title = driver.getTitle();
        UtilLogger.infoLog("Login sayfasinin geldigi kontrol ediliyor...");
        Assert.assertEquals(title,"Uye Girisi - GittiGidiyor");
    }
    public boolean validateLogoMainPage(){
        return logoLoginPage.isDisplayed();
    }


    public HomePage Login(String username, String password){
        UtilLogger.infoLog("Login ,islemi yapiliyor kullanici adi "+username+"Sifre: "+password);
        fieldUsername.sendKeys(username);
        fieldPassword.sendKeys(password);
        btnLogin.click();
        return new HomePage();
    }


}
