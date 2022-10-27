package com.mystore.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.mystore.base.Baseclass;
import com.mystore.pageobjects.IndexPage;
import com.mystore.pageobjects.LoginPage;
import com.mystore.utility.Log;

public class IndexPageTest extends Baseclass{
	
	IndexPage indexpage;
	
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
	public void verifyLogo() throws Throwable
	{
		Log.startTestCase("verifyLogo");
		indexpage=new IndexPage();
		boolean result=indexpage.validateLogo();
		Log.info("Verifying the user is able to see the LOGO");
		Assert.assertTrue(result);
		Log.endTestCase("verifyLogo");
		
	}
	
	@Test(groups="Smoke")
	public void loginpage()
	{
		Log.startTestCase("loginpage");
		indexpage=new IndexPage();
		String acttitle=indexpage.getMyStoreTitle();
		Log.info("------------User is able to see the MyStoreTitle--------");
		Assert.assertEquals(acttitle, "My Store");
		Log.endTestCase("loginpage");
	}
}
