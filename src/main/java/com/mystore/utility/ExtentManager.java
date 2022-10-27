/**
 * 
 */
package com.mystore.utility;

import java.lang.reflect.Method;
import java.util.Properties;

import org.testng.annotations.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
/**
 * @author RajashekarGogulakond
 *
 */
public class ExtentManager {
	
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest test;
	public static ExtentTest parentTest;
	public static ExtentTest childTest;
	
	
	public static void setExtent() {
		//htmlReporter= new ExtentHtmlReporter(System.getProperty("user.dir")+"/test-output/ExtentReport/"+"MyReport_"+BaseClass.getCurrentTime()+".html");
		htmlReporter= new ExtentHtmlReporter(System.getProperty("user.dir")+"\\test-output\\ExtentReport\\MyReport.html");
		htmlReporter.loadXMLConfig(System.getProperty("user.dir")+"/extent-config.xml");
		
		
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		
		
		extent.setSystemInfo("HostName", "MyHost");
		extent.setSystemInfo("ProjectName", "Mystore");
		extent.setSystemInfo("Tester", "RajashekarGogulakond");
		extent.setSystemInfo("OS", "Win11");
		extent.setSystemInfo("Browser", "Chrome");
	}
	
	public static void endReport() {
		extent.flush();
	}
	
	
}
