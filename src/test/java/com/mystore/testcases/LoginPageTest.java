package com.mystore.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.mystore.base.Baseclass;
import com.mystore.dataprovider.DataProviders;
import com.mystore.pageobjects.HomePage;
import com.mystore.pageobjects.IndexPage;
import com.mystore.pageobjects.LoginPage;
import com.mystore.utility.Log;

public class LoginPageTest extends Baseclass{
	
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
									 // dataproviderClass is attribute
	@Test(groups={"Smoke","Sanity"},dataProvider="credentials", dataProviderClass = DataProviders.class)
	public void loginTest(String uname,String pswd) throws Throwable
	{
		Log.startTestCase("loginTest");
		indexPage=new IndexPage();
		loginPage=indexPage.clickOnSignIn();		
	//	homePage=loginPage.login(prop.getProperty("username"),prop.getProperty("password"));
		homePage=loginPage.login(uname,pswd);
		String actUrl =homePage.getCurrURL();
		String expUrl="http://automationpractice.com/index.php?controller=my-account";
		Assert.assertEquals(actUrl, expUrl);
		Log.info("Login is successfull");
		Log.endTestCase("loginTest");
	}
		
	
}
