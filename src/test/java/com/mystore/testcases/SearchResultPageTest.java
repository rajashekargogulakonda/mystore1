package com.mystore.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.mystore.base.Baseclass;
import com.mystore.dataprovider.DataProviders;
import com.mystore.pageobjects.IndexPage;
import com.mystore.pageobjects.SearchResultPage;
import com.mystore.utility.ExtentManager;
import com.mystore.utility.Log;

public class SearchResultPageTest extends Baseclass{
	
	IndexPage indexPage;
	SearchResultPage searchResultPage;
	
	@Parameters("browser")
	@BeforeMethod(groups= {"Smoke","Sanity","Regression"})
	public void setup(@Optional("Chrome")String browser) {
		launchApp(browser); 
	}
	
	//@AfterMethod(groups= {"Smoke","Sanity","Regression"})
	public void tearDown() {
		getDriver().quit();
	}
	@Test(groups="Smoke",dataProvider="searchProduct",dataProviderClass=DataProviders.class)
	public void productAvailabilityTest(String productName) throws Throwable
	{
		
		indexPage=new IndexPage();
		Log.info("Verify user is able to search for the product");
		searchResultPage=indexPage.searchProduct(productName);
		Log.info("---------Successfully user is able to search for the product------");
		boolean result=searchResultPage.isProductAvailable();
		Log.info("Verify user is able to see the product is availabile or not");
		Assert.assertTrue(result);
		Log.info("---------Successfully User is able to see the product-------");
		Log.endTestCase("productAvailabilityTest");
	}
	
}
