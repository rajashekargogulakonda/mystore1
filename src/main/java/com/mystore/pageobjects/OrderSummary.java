package com.mystore.pageobjects;

import java.security.PublicKey;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.mystore.actiondriver.Action;
import com.mystore.base.Baseclass;

public class OrderSummary extends Baseclass{
	
	Action action= new Action();
	
//	Click on Confirmation order button
	@FindBy(xpath="//span[contains(text(),'I confirm my order')]") private WebElement confirmOrderBtn;
	
	public OrderSummary()
	{
		PageFactory.initElements(getDriver(), this);
	}

	public OrderConfirmationPage clickOnconfirmOrderBtn() throws Throwable {
		action.click(getDriver(), confirmOrderBtn);
		return new OrderConfirmationPage();
	}
	
}
