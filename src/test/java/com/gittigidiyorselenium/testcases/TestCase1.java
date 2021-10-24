package com.gittigidiyorselenium.testcases;

import com.gittigidiyorselenium.base.TestBase;
import com.gittigidiyorselenium.pages.*;
import com.gittigidiyorselenium.util.TestUtil;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.annotation.Repeatable;
import java.text.MessageFormat;

public class TestCase1 extends TestBase {

    LoginPage loginPage;
    HomePage homePage;
    SearchPage searchPage;
    ProductPage productPage;
    BasketPage basketPage;
    String sheetName= "Sayfa1";
    public TestCase1(){
        super();
    }

    @BeforeMethod
    public void setUp(){
        initialization();
        loginPage = new LoginPage();
        homePage = new HomePage();
        searchPage = new SearchPage();
        productPage = new ProductPage();
        basketPage = new BasketPage();
    }
    @DataProvider(name = "provider")
    public Object[][] getData(){
        Object data[][] = TestUtil.getTestData(sheetName);
        return data;
    }
//invocationCount = 1
    @Test(priority = 4,invocationCount = 1,dataProvider = "provider")
    public void TestCase1(String username,String password) throws InterruptedException {

        homePage.validateMainPageLogo();                 // Ana sayfanin geldigi logodan kontrol edilir.
        homePage.navigateLoginPage();                    // Login sayfasina yonlendirilir.
        loginPage.validateLoginPageTittle();             // Login sayfasina geldigimiz kontrol edilir
        homePage = loginPage.Login(username,password);   // Id sifre atanir
        homePage.validateLoggedUser();                   // Dogru kullanici adi ile mi girildi kontrolu yapilir
        homePage.searchElement("bilgisayar");			 // Arama kutusunda bilgisayar kelimesini arar
        searchPage.navigateSecondPage();                 // Ikinci sayfaya geçilir
        searchPage.selectRandomItem();                   // Rasgele bir urun secilir
        productPage.writeProductToTextFile();  			 // Text file'a urunun fiyatini ve bilgisini yazar
        productPage.AddToBasket();                       // Secilen urun sepete eklenir.
        productPage.navigateBasketPage();                // Sepet sayfasina gidilir.
        basketPage.comparePrice();                       // Urun sayfasindaki fiyat ile sepette yer alan urun kiyaslanir.
        basketPage.increaseQuantity();                   // Adet arttirilir
        basketPage.checkQuantity();                      // Urun adedinin 2 oldugu dogrulanir.
        basketPage.DeleteItems();                        // Urun sepetten silinir
        basketPage.CheckBasketIsEmpty();                 // Sepetin bos oldugu kontrol edilir


    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
