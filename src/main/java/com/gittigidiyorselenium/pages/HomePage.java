package com.gittigidiyorselenium.pages;

import com.gittigidiyorselenium.base.TestBase;
import com.gittigidiyorselenium.util.UtilLogger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import javax.swing.*;
import java.text.MessageFormat;

public class HomePage extends TestBase {

    @FindBy(how = How.XPATH, using = "//img[@class='sc-1o6eogh-0 i3yadh-0 joKcFF']")
    @CacheLookup
    public WebElement logoMainPage;
    @FindBy(how = How.XPATH, using = "//div[contains(text(),'Giriş Yap')]")
    @CacheLookup
    public WebElement loginButton;
    @FindBy(how = How.XPATH, using = "//div[@class='sc-12t95ss-3 fDarBX']//a[@type='button']")
    @CacheLookup
    public WebElement insideLoginButton;
    @FindBy(how = How.XPATH, using = "//div[contains(text(),'Hesabım')]//span")
    @CacheLookup
    public WebElement loggedUser;
    @FindBy(how = How.CSS, using = "input[data-cy='header-search-input']")
    @CacheLookup
    public WebElement searchBox;
    @FindBy(how = How.CSS, using = "button[data-cy='search-find-button']")
    @CacheLookup
    public WebElement searchButton;

    public HomePage(){
        PageFactory.initElements(driver,this);
    }

    //dummynamedummylastname584240
    public void validateMainPageLogo(){
        boolean flag = logoMainPage.isDisplayed();
        UtilLogger.infoLog("Ana sayfanin geldigi logodan kontrol ediliyor...");
        Assert.assertTrue(flag);


    }
    public SearchPage searchElement(String elementName) throws InterruptedException {
        UtilLogger.infoLog("Aramaya bilgisayar kelimesi yapiliyor...");
        searchBox.sendKeys(elementName);
        Thread.sleep(1000);
        searchButton.click();
        return new SearchPage();
    }

    public void navigateLoginPage(){
        UtilLogger.infoLog("Navigating+ "+prop.getProperty("url"));
        Actions action = new Actions(driver);
        action.moveToElement(loginButton).build().perform();
        //loginButton.click();
        insideLoginButton.click();

    }
    public void validateLoggedUser(){
        String name = loggedUser.getText();
        Assert.assertEquals(name,prop.getProperty("username"));
        String infoMessage = MessageFormat.format("(Giris yapan kullanici adet: {0}",name);
        UtilLogger.infoLog(infoMessage);

    }


}
