/**
 * 
 */
package com.mystore.utility;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.testng.ITestContext;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.mystore.actiondriver.Action;
import com.mystore.base.Baseclass;

/**
 * @author RajashekarGogulakond
 *
 */
public class ListenerClass extends ExtentManager implements ITestListener
{
	
	public void onTestStart(ITestResult result) 
	{
		test = extent.createTest(result.getName());
	}

	public void onTestSuccess(ITestResult result)
	{
		if (result.getStatus() == ITestResult.SUCCESS)
		{
			test.log(Status.PASS, "Pass Test case is: " + result.getName());
		}
	}

	public void onTestFailure(ITestResult result)
	{
		if (result.getStatus() == ITestResult.FAILURE) 
		{
			try 
			{
				test.log(Status.FAIL,
						MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
				test.log(Status.FAIL,
						MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));
				String imgPath = Action.screenShot(Baseclass.getDriver(), result.getName());
			
				test.fail("ScreenShot is Attached", MediaEntityBuilder.createScreenCaptureFromPath(imgPath).build());
				
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	public void onTestSkipped(ITestResult result) 
	{
		if (result.getStatus() == ITestResult.SKIP) 
		{
			test.log(Status.SKIP, "Skipped Test case is: " + result.getName());
		}
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) 
	{
		
	}

	public void onStart(ITestContext context)
	{
		

	}

	public void onFinish(ITestContext context) 
	{
		
	}
	
}
