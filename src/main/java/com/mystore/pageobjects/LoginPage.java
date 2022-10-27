package com.mystore.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mystore.actiondriver.Action;
import com.mystore.base.Baseclass;

public class LoginPage extends Baseclass{
	
	Action a= new Action();
		
	//Enter valid username in the username text box 	
	@FindBy(id="email")	private WebElement userName;
	
	//Enter valid Passward in the Passward text box
	@FindBy(id="passwd") private WebElement password;
	
	//Click on sign in button	
	@FindBy(id="SubmitLogin") private WebElement signInBtn;
	
	//Enter the valid Email Address in the Email address text box for creating new Account	
	@FindBy(name="email_create") private WebElement emailForNewAccount;
	
	//click on Create An Account button	
	@FindBy(name="SubmitCreate") private WebElement createNewAccountBtn;
	
	public LoginPage() {
		PageFactory.initElements(getDriver(), this);
	}
	
	
	
	public HomePage login(String uname, String pswd) throws Throwable {
		a.scrollByVisibilityOfElement(getDriver(), userName);
		a.type(userName, uname);
		a.type(password, pswd);
		a.click(getDriver(),signInBtn);
		Thread.sleep(2000);		
		return new HomePage();
	}
	
	public AddressPage login1(String uname, String pswd) throws Throwable {
		a.scrollByVisibilityOfElement(getDriver(), userName);
		a.type(userName, uname);
		a.type(password, pswd);
		a.click(getDriver(),signInBtn);
		Thread.sleep(2000);
		
		return new AddressPage();
	}
	
	public IndexPage login2(String uname,String pswd) throws Throwable
	{
		a.scrollByVisibilityOfElement(getDriver(), userName);
		a.type(userName, uname);
		a.type(password, pswd);
		a.click(getDriver(),signInBtn);
		Thread.sleep(2000);	
		return new IndexPage();
	}
	
	public AccountCreationPage createNewAccount(String newEmail) throws Throwable {
		a.type(emailForNewAccount, newEmail);
		a.click(getDriver(),createNewAccountBtn);
		return new AccountCreationPage();
	}
	
}