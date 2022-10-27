/**
 * 
 */
package com.mystore.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mystore.actiondriver.Action;
import com.mystore.base.Baseclass;

/**
 * @author RajashekarGogulakond
 *
 */
public class MyAccountPage extends Baseclass{
	Action a=new Action();
	
	@FindBy(xpath="//span[text()='My wishlists']")
	private WebElement mywishlistlink;
	
	@FindBy(xpath="//h1[text()='My account']")
	private WebElement verifymyaccount;
	
	public MyAccountPage()
	{
		PageFactory.initElements(getDriver(), this);
	}
	
	public MywishlistPage clickonMyWishlistLink()
	{
		a.click(getDriver(), mywishlistlink);
		return new MywishlistPage();
	}
	
	public boolean verifyMyAccountPage()
	{
		return verifymyaccount.isDisplayed();
	}
	
}
