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
public class MywishlistPage extends Baseclass{
	
	Action a=new Action();
	
	@FindBy(xpath="//a[@href='javascript:;' and contains(text(),'My wishlist')]")
	private WebElement mywishlist;
	
	@FindBy(xpath="//td[@class='bold align_center']")
	private WebElement verifyqntity;
	
	@FindBy(xpath="//a[contains(text(),'View')]")
	private WebElement viewlink;
	
	@FindBy(xpath="//input[@type='text' and @class='form-control grey']")
	private WebElement qntytb;
	
	@FindBy(xpath="//a[@title='Save']//span[text()='Save']")
	private WebElement savebtn;
	
	@FindBy(xpath="//img[@alt='Faded Short Sleeve T-shirts']")
	private WebElement product;
	public MywishlistPage()
	{
		PageFactory.initElements(getDriver(), this);
	}
	
	public boolean verifyQuantity()
	{
		return a.isDisplayed(getDriver(), verifyqntity);
	}
	
	public void clickonviewlink()
	{
		a.click(getDriver(), viewlink);
	}
	
	public void enterqnty(String qnt)
	{
		a.type(qntytb, qnt);
	}
	
	public void cliconsavebtn()
	{
		a.click(getDriver(), savebtn);
	}
	
	public AddToCartPage clickonproduct()
	{
		a.click(getDriver(), product);
		return new AddToCartPage();
	}
	
	public void clickonmywishlistlink()
	{
		a.click(getDriver(), mywishlist);
	}
	
	
}
