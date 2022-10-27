package com.mystore.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.mystore.base.Baseclass;
import com.mystore.dataprovider.DataProviders;
import com.mystore.pageobjects.AddToCartPage;
import com.mystore.pageobjects.IndexPage;
import com.mystore.pageobjects.OrderPage;
import com.mystore.pageobjects.SearchResultPage;
import com.mystore.utility.Log;

public class OrderPageTest extends Baseclass{
	
	private IndexPage index;
	private SearchResultPage searchResultPage;
	private AddToCartPage addToCartPage;
	private OrderPage orderPage;
	
	@Parameters("browser")
	@BeforeMethod(groups= {"Smoke","Sanity","Regression"})
	public void setup(@Optional("Chrome")String browser) {
		launchApp(browser); 
	}
	
	@AfterMethod(groups= {"Smoke","Sanity","Regression"})
	public void tearDown() {
		getDriver().quit();
	}
	
	@Test(groups="Regression",dataProvider = "getProduct", dataProviderClass = DataProviders.class)
	public void addToCartTest(String productName, String qty, String size) throws Throwable {
		Log.startTestCase("addToCartTest");
		index= new IndexPage();
		searchResultPage=index.searchProduct(productName);
		Log.info("User is able to search for the product and going to click on the product");
		addToCartPage=searchResultPage.clickOnProduct();
		Log.info("User is going to enter the quantity of the product");
		addToCartPage.enterQuantity(qty);
		Log.info("User is going to enter the size of the product");
		addToCartPage.selectSize(size);
		Log.info("User is going to click on Add To Cart button");
		addToCartPage.clickOnAddToCart();
		Log.info("User is going to click on check out button");
		orderPage=addToCartPage.clickOnCheckOut();
		double unitPrice=orderPage.getUnitPrice();
		double totalPrice=orderPage.getTotalPrice();
		double totalExpectedPrice=(unitPrice*2)+2;
		Log.info("Verify user is able to see the Total Price and Total Expected Price");
		Assert.assertEquals(totalPrice, totalExpectedPrice);
		Log.info("-------------Successfully user is able to see the Total price and Total Expected Price got matched--------");
		
		Log.endTestCase("addToCartTest");
		
	}

}
