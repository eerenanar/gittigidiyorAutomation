package com.gittigidiyorselenium.pages;

import com.gittigidiyorselenium.base.TestBase;
import com.gittigidiyorselenium.util.UtilLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileWriter;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.logging.Logger;

public class ProductPage extends TestBase {


    @FindBy(how = How.XPATH,using = "//h1[@id='sp-title']")
    @CacheLookup
    public  WebElement productInfo;
    @FindBy(how = How.XPATH,using = "//span[@id='sp-price-highPrice']")
    @CacheLookup
    public  WebElement productPrice;
    @FindBy(how = How.CSS,using = "#add-to-basket")
    @CacheLookup
    public WebElement buttonAddBasket;
    @FindBy(how = How.CLASS_NAME,using = "header-cart-hidden-link")
    @CacheLookup
    public WebElement buttonMyBasket;
    @FindBy(how = How.XPATH,using = "//a[@class='gg-ui-btn-default padding-none']")
    @CacheLookup
    public WebElement buttonGoBasket;
    @FindBy(how = How.XPATH,using = "//span[@id='sp-price-discountPercentage']")
    @CacheLookup
    public static WebElement isDiscountAvailable;
    @FindBy(how = How.XPATH,using = "//div[@id='sp-price-lowPrice']")
    @CacheLookup
    public static WebElement withDiscountPrice;
    By basket = By.cssSelector("#add-to-basket");
    public static String info;
    public static String price;

    public ProductPage(){
        PageFactory.initElements(driver,this);

    }
    public void writeProductToTextFile(){


        if(isDiscountAvailable.isDisplayed()){
            price=withDiscountPrice.getText();
        }
        else{
            price= productPrice.getText();
            }

        info = productInfo.getText();

        WriteTextToFile(MessageFormat.format("Urun Bilgisi:\t{0}\n urun Fiyati:\t{1}",info,price),"C:\\filename.txt");
        UtilLogger.infoLog("Urun bilgisi ve urun fiyati C:\\filename.txt dosyaya yazildi");
    }

    public void AddToBasket() throws InterruptedException {
        UtilLogger.infoLog("Sepete urun ekleniyor..");
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0,230)");
        Actions actions = new Actions(driver);
        actions.moveToElement(buttonAddBasket).click().perform();
    }
    public BasketPage navigateBasketPage(){
        UtilLogger.infoLog("Sepet sayfasina yonlendiriliyor...");
        buttonGoBasket.click();
        return new BasketPage();
    }

}
