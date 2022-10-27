	package com.mystore.testcases;

import org.testng.Assert; 
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.mystore.base.Baseclass;
import com.mystore.pageobjects.HomePage;
import com.mystore.pageobjects.IndexPage;
import com.mystore.pageobjects.LoginPage;
import com.mystore.utility.Log;

public class HomePageTest extends Baseclass{
	
	IndexPage indexPage;
	LoginPage loginPage;
	HomePage homePage;
	
	@Parameters("browser")
	@BeforeMethod(groups= {"Smoke","Sanity","Regression"})
	public void setup(@Optional("Chrome")String browser)
	{
		launchApp(browser);
	}
	
	@AfterMethod(groups= {"Smoke","Sanity","Regression"})
	public void teardown()
	{
		getDriver().quit();
	}
	
	@Test(groups="Smoke")
	public void WishListTest() throws Throwable
	{
		Log.startTestCase("WishListTest");
		
		indexPage=new IndexPage();
		Log.info("User is going to click on Signin button");
		loginPage=indexPage.clickOnSignIn();
		Log.info("User enters the Username and Password coming from the config properties");
		homePage=loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
		boolean result=homePage.validateMyWishList();
		Log.info("Verify if user is able to see the WishList page");
		Assert.assertTrue(result);
		
		Log.endTestCase("WishListTest");
		
	}
	
	@Test(groups="Smoke")
	public void orderHistoryandDetailsTest() throws Throwable
	{
		Log.startTestCase("orderHistoryandDetailsTest");
		
		indexPage=new IndexPage();
		Log.info("User is going to click on Signin button");
		loginPage=indexPage.clickOnSignIn();
		Log.info("User enters the Username and Password coming from the config properties");
		homePage=loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
		boolean result=homePage.validateOrderHistory();
		Log.info("Verify if user is able to see order History and Details Page");
		Assert.assertTrue(result);
		
		Log.endTestCase("orderHistoryandDetailsTest");
	}
}
