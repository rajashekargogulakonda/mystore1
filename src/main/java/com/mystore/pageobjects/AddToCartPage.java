package com.mystore.pageobjects;
import org.openqa.selenium.WebElement; 
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mystore.actiondriver.Action;
import com.mystore.base.Baseclass;

public class AddToCartPage extends Baseclass {
	
Action action= new Action();
	
//Select the Qauntity 
	@FindBy(id="quantity_wanted") private WebElement quantity;
	
//	Select the size
	@FindBy(name="group_1")	private WebElement size;
	
//	Click on Add to Cart button
	@FindBy(xpath="//span[text()='Add to cart']") private WebElement addToCartBtn;
	
//	Verifying User is able to successfully add the product to cart page
	@FindBy(xpath="//*[@id=\"layer_cart\"]//h2/i") private WebElement addToCartMessag;
	
//	Verify user is able to click on proceed to checkout button
	@FindBy(xpath="//span[contains(text(),'Proceed to checkout')]")	private WebElement proceedToCheckOutBtn;
	
	@FindBy(xpath="//span[@class='cross']")
	private WebElement clickoncrossbtn;
	
	@FindBy(xpath="//a[@title='View my shopping cart']")
	private WebElement cart;
	
	@FindBy(xpath="//a[@id='button_order_cart']")
	private WebElement checkoutbtn;
	
	public AddToCartPage() {
		PageFactory.initElements(getDriver(), this);
	}

	public void enterQuantity(String quantity1) throws Throwable {
		action.type(quantity, quantity1);
	}
	
	public void selectSize(String size1) throws Throwable {
		action.selectByVisibleText(size1, size);
	}
	
	public void clickOnAddToCart() throws Throwable {
		action.click(getDriver(), addToCartBtn);
		action.fluentWait(getDriver(), proceedToCheckOutBtn, 10);
		action.JSClick(getDriver(), proceedToCheckOutBtn);
	}
	
	
	
	public void clickoncrossbtn()
	{
		action.click(getDriver(), clickoncrossbtn);
	}
	
	public boolean validateAddtoCart() throws Throwable {
		action.fluentWait(getDriver(), addToCartMessag, 10);
		return action.isDisplayed(getDriver(), addToCartMessag);
	}
	
	public OrderPage clickOnCheckOut() throws Throwable {
		action.fluentWait(getDriver(), proceedToCheckOutBtn, 10);
		action.JSClick(getDriver(), proceedToCheckOutBtn);
		return new OrderPage();
	}
	
	public void mousehoveroncart()
	{
		action.mouseOverElement(getDriver(), cart);
	}
	public AddressPage clickoncheckoutbtn()
	{
		action.click(getDriver(), checkoutbtn);
		return new AddressPage();
	}
	
}
