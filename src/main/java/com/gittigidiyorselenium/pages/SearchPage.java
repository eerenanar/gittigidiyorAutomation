package com.gittigidiyorselenium.pages;

import com.gittigidiyorselenium.base.TestBase;
import com.gittigidiyorselenium.util.UtilLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Random;

public class SearchPage extends TestBase {

    @FindBy(how = How.XPATH,using = "//a[contains(text(),'2')]")
    @CacheLookup
    public WebElement secondPageIndex;
    @FindBy(how = How.XPATH,using = "//div[@class='clearfix']/ul[@class='catalog-view clearfix products-container']/li/a")
    @CacheLookup
    public List<WebElement> products;

    public SearchPage(){
        PageFactory.initElements(driver,this);
    }

    public ProductPage selectRandomItem(){
        UtilLogger.infoLog("Sayfadaki urunler arasindan rasgele bir element seciyor...");
        int size = products.size();
        Random rn = new Random();
        int answer = rn.nextInt(size) + 1;
        String locator = "(//div[@class='clearfix']/ul[@class='catalog-view clearfix products-container']/li/a)["+answer+"]";
        driver.findElement(By.xpath(locator)).click();
        return new ProductPage();
    }

    public void navigateSecondPage(){
        UtilLogger.infoLog("Ikinci sayfaya yonlendiriliyor...");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        secondPageIndex.click();
    }

}
