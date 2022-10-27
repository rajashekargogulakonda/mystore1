package com.mystore.base;

import org.testng.annotations.BeforeTest;
import java.io.FileInputStream;    
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.xml.DOMConfigurator;
import org.ietf.jgss.Oid;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import com.beust.jcommander.Parameter;
import com.mystore.actiondriver.Action;
//import com.mystore.utility.ExtentManager;
import io.github.bonigarcia.wdm.WebDriverManager;
public class BaseClassnoPramtr {
	public static WebDriver driver;
	public static Properties prop;
	
	@BeforeTest
	public void loadConfig() 
	{
		
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir") + "\\Configuration\\Config.properties");
			prop.load(ip);
			
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void launchApp(String browser)
	{
		
		WebDriverManager.chromedriver().setup();
	
		 String browserName = prop.getProperty("browser");
		if (browserName.equalsIgnoreCase("Chrome")) 
		{
			WebDriverManager.chromedriver().setup();
			
		 	driver=new ChromeDriver();
			
			
		}
		else if (browserName.equalsIgnoreCase("FireFox")) 
		{
			WebDriverManager.firefoxdriver().setup();
			
			driver=new FirefoxDriver();
			
			
		}
		else if(browserName.contains("edge"))
		{
			WebDriverManager.edgedriver().setup();
			
			driver=new EdgeDriver();
			
			
		}
		else
		{
				System.out.println("The browser is not there");
		}
		
		//Launching the URL
		
		driver.get(prop.getProperty("url"));		
		
		
		driver.manage().window().maximize();
		
		
		
		driver.manage().deleteAllCookies();
		
		
		
		driver.manage().timeouts().implicitlyWait
		(Integer.parseInt(prop.getProperty("implicitWait")),TimeUnit.SECONDS);
		
		//PageLoad TimeOuts
	 	
		driver.manage().timeouts().pageLoadTimeout
		(Integer.parseInt(prop.getProperty("pageLoadTimeOut")),TimeUnit.SECONDS);
		
		
				
	}
}
