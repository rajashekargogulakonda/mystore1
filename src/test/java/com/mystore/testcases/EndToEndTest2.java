/**
 * 
 */
package com.mystore.testcases;

import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.annotations.Parameters;

import com.mystore.base.Baseclass;
import com.mystore.dataprovider.DataProviders;
import com.mystore.pageobjects.AddToCartPage;
import com.mystore.pageobjects.AddressPage;
import com.mystore.pageobjects.IndexPage;
import com.mystore.pageobjects.LoginPage;
import com.mystore.pageobjects.MyAccountPage;
import com.mystore.pageobjects.MywishlistPage;
import com.mystore.pageobjects.OrderConfirmationPage;
import com.mystore.pageobjects.OrderPage;
import com.mystore.pageobjects.OrderSummary;
import com.mystore.pageobjects.PaymentPage;
import com.mystore.pageobjects.SearchResultPage;
import com.mystore.pageobjects.ShippingPage;

/**
 * @author RajashekarGogulakond
 *
 */
public class EndToEndTest2 extends Baseclass{
	IndexPage indexpage;
	LoginPage loginpage;
	SearchResultPage searchproduct;
	MyAccountPage myaccountpage;
	MywishlistPage mywishlistpage;
    AddToCartPage addtocartpage;
    OrderPage orderpage;
    AddressPage addresspage;
    ShippingPage shippingpage;
    PaymentPage paymentpage;
	OrderSummary ordersummary;
	OrderConfirmationPage orderconfirmationpage;
    
	@Parameters("browser")
	@BeforeMethod
	public void setup(@Optional("chrome")String browserName)
	{
		launchApp(browserName);
	}
	
	//@AfterMethod
	public void teardown()
	{
		getDriver().quit();
	}
	
	@Test(dataProvider = "searchProduct",dataProviderClass = DataProviders.class)
	public void endToEndTest2(String product) throws Throwable
	{
		indexpage=new IndexPage();
		boolean result=indexpage.validateLogo();
		Assert.assertTrue(result);
		loginpage=indexpage.clickOnSignIn();
		indexpage=loginpage.login2(prop.getProperty("username"), prop.getProperty("password"));
		searchproduct=indexpage.searchProduct(product);
		searchproduct.clickonproduct();
		boolean result1=searchproduct.isProductAvailable();
		Assert.assertTrue(result1);
		searchproduct.clickonaddtowishlistlnk();
		searchproduct.clickonclosebtn();
		myaccountpage=searchproduct.clickonmyAccount();
		boolean result2=myaccountpage.verifyMyAccountPage();
		Assert.assertTrue(result2);
		mywishlistpage=myaccountpage.clickonMyWishlistLink();
		boolean result3=mywishlistpage.verifyQuantity();
		Assert.assertTrue(result3);
		mywishlistpage.clickonviewlink();
		mywishlistpage.enterqnty(prop.getProperty("productqntity"));
		mywishlistpage.cliconsavebtn();
		addtocartpage = mywishlistpage.clickonproduct();
	//	addtocartpage.clickOnAddToCart();
		addresspage=addtocartpage.clickoncheckoutbtn();
		shippingpage=addresspage.clickOnCheckOut();
		shippingpage.checkTheTerms();
		paymentpage=shippingpage.clickOnProceedToCheckOut();
		ordersummary=paymentpage.clickOnPaymentMethod();
		orderconfirmationpage=ordersummary.clickOnconfirmOrderBtn();
		String actualMessage=orderconfirmationpage.validateConfirmMessage();
		String expectedMsg="Your order on My Store is complete.";
		Assert.assertEquals(actualMessage, expectedMsg);
	}
	
}
