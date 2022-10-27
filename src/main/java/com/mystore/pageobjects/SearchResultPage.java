package com.mystore.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.mystore.actiondriver.Action;
import com.mystore.base.Baseclass;

public class SearchResultPage extends Baseclass 
{
	
	Action action= new Action();
	
//	Click on the product
	@FindBy(xpath="//*[@id=\"center_column\"]//img") 
	private WebElement productResult;
	
	@FindBy(xpath="//a[contains(text(),'Add to wishlist')]")
	private WebElement addtowishlistlnk;
	
	@FindBy(xpath="//a[@title='Close']")
	private WebElement closebtn;
	
	@FindBy(xpath="//a[@href='http://automationpractice.com/index.php?controller=my-account' and @class='account']")
	private WebElement myaccountlnk;
	
	public SearchResultPage()
	{
		PageFactory.initElements(getDriver(), this);
	}
	
	public boolean isProductAvailable() throws Throwable
	{
		return action.isDisplayed(getDriver(), productResult);
	}	
	
	public void clickonproduct()
	{
		action.click(getDriver(), productResult);
	}
	
	public void clickonaddtowishlistlnk()
	{
		action.click(getDriver(), addtowishlistlnk);
	}
	
	public void clickonclosebtn()
	{
		action.click(getDriver(), closebtn);
	}
	
	public MyAccountPage clickonmyAccount()
	{
		action.JSClick(getDriver(), myaccountlnk);
		return new MyAccountPage();
	}
	
	public AddToCartPage clickOnProduct() throws Throwable 
	{
		action.click(getDriver(), productResult);
		return new AddToCartPage();
	}
	
}
