package com.gittigidiyorselenium.pages;

import com.gittigidiyorselenium.base.TestBase;
import com.gittigidiyorselenium.util.UtilLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.Locale;

public class BasketPage extends TestBase {
    @FindBy(how = How.XPATH,using = "//a[@class='gg-ui-btn-default padding-none']")
    @CacheLookup
    public WebElement buttonGoBasket;
    @FindBy(how = How.XPATH,using = "//h2[contains(text(),'Sepetinizde urun bulunmamaktadir.')]")
    @CacheLookup
    public WebElement emptyMessage;

    public BasketPage(){
        PageFactory.initElements(driver,this);

    }
    public void comparePrice() throws InterruptedException {

        String locator = "div[class='total-price'] strong";
        UtilLogger.infoLog("Ürün fiyatlari karsilastiriliyor...");

        String basketPrice = driver.findElement(By.cssSelector(locator)).getText();
        Assert.assertEquals(ProductPage.price,basketPrice);

    }
    public void increaseQuantity() throws InterruptedException {

        UtilLogger.infoLog("Ürün adeti 1 arttiriliyor");
        String locator = "span[class='plus icon-plus gg-icon gg-icon-plus']";
        driver.findElement(By.cssSelector(locator)).click();

    }
    public void checkQuantity() throws InterruptedException {

        String locator = "input[class='amount']";
        UtilLogger.infoLog("Aranilan adet miktari kontrol ediliyor");
        String value = driver.findElement(By.cssSelector(locator)).getAttribute("value");
        Assert.assertEquals(value,"2","Adet sayisi ile eslesmiyor");

    }
    public void DeleteItems() throws InterruptedException {

        String locator ="span[class='hidden-m']";
        UtilLogger.infoLog("Sepettten ürün siliniyor...");
        driver.findElement(By.cssSelector(locator)).click();

    }
    public void CheckBasketIsEmpty(){
        UtilLogger.infoLog("Sepetin bos oldugu kontrol ediliyor...");
        WebDriverWait wait = new WebDriverWait(driver,15);
        try{
            wait.until(ExpectedConditions.visibilityOf(emptyMessage));

        }catch (TimeoutException e) {
            throw new IllegalStateException("there are product on my basket");
        }
    }


}
