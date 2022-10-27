package com.mystore.base;

import java.io.FileInputStream;      
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;
import org.testng.annotations.Parameters;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.mystore.actiondriver.Action;
import com.mystore.utility.ExtentManager;

//import com.mystore.utility.ExtentManager;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Baseclass {

	public static Properties prop;
	// 	public static WebDriver driver;
	
	
	// Declare ThreadLocal Driver
	public static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();

	//	When ever we want to use the getdriver() we use this 33 to 39 lines , if we dont want to use then we can comment and we can use 29 line
	public static WebDriver getDriver() 
	{
		// Get Driver from threadLocalmap
		return driver.get();
	}

	//loadConfig method is to load the configuration
	@BeforeSuite(groups= {"Smoke","Sanity","Regression"})
	public void loadConfig()
	{
		ExtentManager.setExtent();
		DOMConfigurator.configure("log4j.xml");

		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir") + "\\Configuration\\Config.properties");
			prop.load(ip);
			//	System.out.println("driver:" + driver);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//Here we using polymorphism concept in java 
	
	public static void launchApp(String browser)
	{		
		//	WebDriverManager.chromedriver().setup();
		String browserName = prop.getProperty("browser");
		
		if (browserName.equalsIgnoreCase("Chrome")) 
		{
			WebDriverManager.chromedriver().setup();

			// 	driver=new ChromeDriver();

			// Set Browser to ThreadLocalMap
			driver.set(new ChromeDriver());
		}
		else if (browserName.equalsIgnoreCase("FireFox")) 
		{
			WebDriverManager.firefoxdriver().setup();

			//	driver=new FirefoxDriver();

			// Set Browser to ThreadLocalMap
			driver.set(new FirefoxDriver());
		}
		else if(browserName.contains("edge"))
		{
			WebDriverManager.edgedriver().setup();

			//	driver=new EdgeDriver();

			// Set Browser to ThreadLocalMap
			driver.set(new EdgeDriver());
		}
		else
		{
			System.out.println("The browser is not there");
		}

		//Launching the URL
		getDriver().get(prop.getProperty("url"));

		//Maximize the screen
		getDriver().manage().window().maximize();

		//	driver.manage().window().maximize();

		//Delete all the cookies
		getDriver().manage().deleteAllCookies();

		//	driver.manage().deleteAllCookies();

		//Implicit TimeOuts
		getDriver().manage().timeouts().implicitlyWait
		(Integer.parseInt(prop.getProperty("implicitWait")),TimeUnit.SECONDS);

		//		driver.manage().timeouts().implicitlyWait
		//		(Integer.parseInt(prop.getProperty("implicitWait")),TimeUnit.SECONDS);

		//PageLoad TimeOuts
		getDriver().manage().timeouts().pageLoadTimeout
		(Integer.parseInt(prop.getProperty("pageLoadTimeOut")),TimeUnit.SECONDS);

		//	driver.manage().timeouts().pageLoadTimeout
		//	(Integer.parseInt(prop.getProperty("pageLoadTimeOut")),TimeUnit.SECONDS);

	}

	@AfterSuite(groups = { "Smoke", "Regression","Sanity" })
	public void afterSuite()
	{
		ExtentManager.endReport();
	}

}
